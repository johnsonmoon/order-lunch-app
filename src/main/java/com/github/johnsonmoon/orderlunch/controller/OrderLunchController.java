package com.github.johnsonmoon.orderlunch.controller;

import com.github.johnsonmoon.orderlunch.Util.OrderUtil;
import com.github.johnsonmoon.orderlunch.action.OrderCache;
import com.github.johnsonmoon.orderlunch.common.RestContext;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.entity.param.OrderParam;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderVO;
import com.github.johnsonmoon.orderlunch.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2019/7/25 11:00.
 */
@RestController
@RequestMapping("/order")
public class OrderLunchController {
    private static Logger logger = LoggerFactory.getLogger(OrderLunchController.class);
    private static AtomicBoolean locked = new AtomicBoolean(false);

    private static void acquireLock() {
        for (int i = 0; i < 100 && locked.get(); i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
        locked.set(true);
    }

    private static void releaseLock() {
        locked.set(false);
    }

    @GetMapping(path = "/increase", produces = "application/json")
    @ResponseBody
    public HttpResponse increaseOrder(OrderParam orderParam) {
        if(!OrderUtil.validateMember(orderParam.getName())){
            return new HttpResponse(201,"非法名称无法识别");
        }
        try {
            acquireLock();

            Order order = new Order();
            order.setName((orderParam == null || orderParam.getName() == null) ? "UNKNOWN" : orderParam.getName());
            order.setRemark(orderParam == null || orderParam.getRemark() == null ? "" : orderParam.getRemark());
            order.setAppendNum(1);
            order.setIpAddress(getRequestIp(RestContext.getHttpServletRequest()));
            order.setOrderTime(System.currentTimeMillis());

            OrderCache.append(order);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return new HttpResponse(201,"点餐失败");
        } finally {
            releaseLock();
        }
        return new HttpResponse(200,"点餐成功");
    }

    @GetMapping(path = "/decrease", produces = "application/json")
    @ResponseBody
    public HttpResponse decreaseOrder(OrderParam orderParam) {
        if(!OrderUtil.validateMember(orderParam.getName())){
            return new HttpResponse(201,"非法名称无法识别");
        }
        try {
            acquireLock();

            if (OrderCache.getSum() <= 0) {
                logger.warn("Not able to decrease.");
                return new HttpResponse(201,"撤销点餐失败");
            }

            Order order = new Order();
            order.setName((orderParam == null || orderParam.getName() == null) ? "UNKNOWN" : orderParam.getName());
            order.setRemark(orderParam == null || orderParam.getRemark() == null ? "" : orderParam.getRemark());
            order.setAppendNum(-1);
            order.setIpAddress(getRequestIp(RestContext.getHttpServletRequest()));
            order.setOrderTime(System.currentTimeMillis());

            OrderCache.append(order);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return new HttpResponse(201,"撤销点餐失败");
        } finally {
            releaseLock();
        }
        return new HttpResponse(200,"撤销点餐成功");
    }

    @GetMapping(path = "/clear", produces = "application/json")
    public Boolean clearOrders() {
        try {
            acquireLock();
            OrderCache.clear();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return false;
        } finally {
            releaseLock();
        }
        return true;
    }

    @GetMapping(path = "/details", produces = "application/json")
    public OrderDetailsVO getOrdersDetail() {
        OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
        List<Order> orders = new ArrayList<>(OrderCache.getOrders());
        List<OrderVO> orderVOS = new ArrayList<>();
        for (int index = 0; index < orders.size(); index++) {
            Order order = orders.get(index);
            OrderVO orderVO = new OrderVO();
            orderVO.setName(order.getName());
            orderVO.setNumber(index + 1);
            orderVO.setAppendNum(order.getAppendNum());
            orderVO.setIpAddress(order.getIpAddress());
            orderVO.setOrderTime(order.getOrderTime());
            orderVO.setRemark(order.getRemark());
            orderVOS.add(orderVO);
        }
        orderDetailsVO.setOrders(orderVOS);
        Integer sum = 0;
        for (Order order : orders) {
            sum += order.getAppendNum();
        }
        Integer left = MemberConstant.memberSet.size() - sum;
        orderDetailsVO.setSum(sum);
        orderDetailsVO.setLeft(left);
        return orderDetailsVO;
    }

    private static String getRequestIp(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("Proxy-Client-IP");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (ip != null && ip.length() != 0) {
                ip = ip.split(",")[0];
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = "127.0.0.1";
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return ip;
    }
}
