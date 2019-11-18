package com.frank.order_service.controller;

import com.frank.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product_order")
public class ProductOrderCotroller {
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id") Integer userId,@RequestParam("product_id") Integer productId , HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("data",productOrderService.save(userId,productId));
        return map;
    }

    /**
     * version1：系统出现异常之后熔断保护
     * version2：异常报警通知
     * @param userId
     * @param productId
     * @return
     */
    public Object saveOrderFail(Integer userId, Integer productId, HttpServletRequest request){
        //设置监控报警
        String saveOrderKey = "save-order";
        String sendValue = (String) redisTemplate.opsForValue().get(saveOrderKey);
        //获取出问题机器的ip地址
        String ipAddr = request.getRemoteAddr();
        //Lambda表达式开启多线程
        new Thread( () -> {
            if(StringUtils.isBlank(sendValue)){
                //模拟短信通知接口
                System.out.println("紧急短信，用户下单失败，请尽快查找原因！！！+ ip : "+ipAddr);
                //将saveOrderKey的值设置20秒的生命周期
                redisTemplate.opsForValue().set(saveOrderKey,"save-order-failed",20, TimeUnit.SECONDS);
            }else{
                System.out.println("短信已发送,20秒内不需要再次发送");
            }
        }).start();

        Map<String,Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("msg","商品过于火爆,您被挤出来了,请稍后重试");
        return map;
    }

}

