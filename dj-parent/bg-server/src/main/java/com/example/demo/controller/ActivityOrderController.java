package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.domain.*;
import com.example.demo.service.*;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 运动项目 前端控制器
 * 订单创建（预约）
 * 订单查询
 * </p>
 *
 * @author
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/activityOrder")
@Api(tags = "ActivityOrderController")
public class ActivityOrderController {
    @Resource
    private ActivityOrderService activityOrderService;
    @Resource
    private UserService userService;
    @Resource
    private FacilityService facilityService;
    @Resource
    private ActivityService activityService;
    @Resource
    private MembershipOrderService membershipOrderService;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private TimetableService timetableService;
    @Resource
    public class Res {
        public ArrayList arr1 = new ArrayList<>();
        public ArrayList arr2 = new ArrayList<>();
        public Res(ArrayList arr1, ArrayList arr2) {
            this.arr1 = arr1;
            this.arr2 = arr2;
        }
    }


    /**
     * 查看所有订单
     */
    @GetMapping("/allOrder")
    @ApiOperation(value = "return all orders")
    public Result allOrder(){
        List<ActivityOrder> allList = activityOrderService.list();
        if(allList == null){
            return Result.error("-1", "返回订单信息失败！");
        }

//        System.out.println(allList);
//        System.out.println(allList.get(0));
//        System.out.println(allList.get(0).getTime().toString());
//        for (ActivityOrder activityOrder : allList) {
//            String g = activityOrder.getTime().toString();
//            System.out.println("g:" + g);
//            String p[] = g.split("T");
//            System.out.println(p[0]);
//        }


        return Result.success(allList, "返回所有订单信息成功！");
    }

    @GetMapping("/getWeeklyIncome")
    @ApiOperation(value = "get weekly income")
    public Result getWeeklyIncome(){
//        public class Res {
//            public final ArrayList arr1 = new ArrayList<>();
//            public final ArrayList arr2 = new ArrayList<>();
//            public Res(ArrayList arr1, ArrayList arr2) {
//                this.arr1 = arr1;
//                this.arr2 = arr2;
//            }
//        }

        List<ActivityOrder> allList = activityOrderService.list();
        if(allList == null){
            return Result.error("-1", "返回订单信息失败！");
        }



        ArrayList arr1 = new ArrayList<>();
        ArrayList arr2 = new ArrayList<>();

        Res res = new Res(arr1, arr2);

        for (int i = 6; i >= 0; i--)
        {
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            calendar1.add(Calendar.DATE, -i);

            String ss = sdf1.format(calendar1.getTime());

            Date date = calendar1.getTime();

            res.arr1.add(ss);
            Float dailyIncome = 0.0F;

            List<ActivityOrder> orderList = activityOrderService.list(new QueryWrapper<ActivityOrder>().eq("paymentDate", date));
            if (orderList != null){
                for (ActivityOrder aa : orderList) {
                    dailyIncome += aa.getPaymentmoney();
                }
            }

            List<MembershipOrder> membershiporderList = membershipOrderService.list(new QueryWrapper<MembershipOrder>().eq("paymentDate", date));
            if (membershiporderList != null){
                for (MembershipOrder mm : membershiporderList) {
                    dailyIncome += mm.getPaymentMoney();
                }
            }

            res.arr2.add(dailyIncome);
        }


        return Result.success(res, "return weekly income successfully！");
    }


    /**
     * 查看用户订单
     */
    @GetMapping("/viewOrder")
    @ApiOperation(value = "view user's orders")
    public Result viewOrder(@RequestParam int userId) {
        User userView = userService.getOne(new QueryWrapper<User>().eq("id", userId));
        if(userView == null){
            return Result.error("-1", "没有找到该用户！");
        }

        List<ActivityOrder> orderList = activityOrderService.list(new QueryWrapper<ActivityOrder>().eq("userId", userView.getId()));
        if (orderList == null){
            return Result.error("-1", "没有返回该用户订单！");
        }
        return Result.success(orderList, "返回订单成功！");
    }

    /**
     * 按照支付状态查询
     * 所有匹配的订单
     * paymentstatus = 0, usagestatus = 0 表示未支付未使用
     * paymentstatus = 1, usagestatus = 0 表示已支付未使用
     * paymentstatus = 1, usagestatus = 1 表示已支付已使用
     */
    @GetMapping("/findByStatus")
    @ApiOperation(value = "find orders by status")
    public Result findByStatus(@RequestParam int paymentStatus, int usageStatus) {

        List<ActivityOrder> orderList = activityOrderService.list(new QueryWrapper<ActivityOrder>().eq("paymentstatus", paymentStatus).eq("usagestatus", usageStatus));

        if(orderList == null){
            return Result.error("-1", "返回订单信息失败");
        }

        return Result.success(orderList, "返回订单成功！");
    }


    /**
     * 按照支付状态查询
     * 指定用户的订单
     * paymentstatus = 0, usagestatus = 0 表示未支付未使用
     * paymentstatus = 1, usagestatus = 0 表示已支付未使用
     * paymentstatus = 1, usagestatus = 1 表示已支付已使用
     */
    @GetMapping("/findByUserStatus")
    @ApiOperation(value = "find orders by user status")
    public Result findByUserStatus(@RequestParam int userid,int paymentStatus, int usageStatus) {
        List<ActivityOrder> orderList = activityOrderService.list(new QueryWrapper<ActivityOrder>().eq("userid", userid).eq("usagestatus", paymentStatus).eq("usagestatus", usageStatus));

        if(orderList == null){
            return Result.error("-1", "返回订单信息失败");
        }

        return Result.success(orderList, "返回订单成功！");
    }




    /**
     * 修改用户使用状态，用于核销
     * 当用户使用：usageStatus由 0 变为 1
     */
    @GetMapping("/modifyUsageStatus")
    @ApiOperation(value = "modify Usage Status")
    public Result modifyUsageStatus(@RequestParam int orderid){
        ActivityOrder order = activityOrderService.getById(orderid);

        if (order == null){
            return Result.error("-1", "没有找到该订单！");
        }
        if(order.getPaymentstatus()==0){
            return Result.error("-1", "该用户还尚未支付！");
        }
        if (order.getUsagestatus()==1){
            return Result.error("-1", "该用户已使用！");
        }

        LocalDateTime payTime = LocalDateTime.now();
        UpdateWrapper<ActivityOrder> updateOrderPayment = new UpdateWrapper<>();
        updateOrderPayment.eq("id", orderid);
        updateOrderPayment.set("usageStatus", 1);


        boolean flag = activityOrderService.update(order, updateOrderPayment);
        if(!flag){
            return Result.error("-1", "修改失败！");
        }else{
            System.out.println(order.getPaymentstatus());
            return Result.success();
        }
    }


    /**
     * 修改用户支付状态
     * 当用户支付：paymentStatus由 0 变为 1
     */
    @GetMapping("/modifyPaymentStatus")
    @ApiOperation(value = "modify payment Status")
    public Result modifyPaymentStatus(@RequestParam int orderid){
        ActivityOrder order = activityOrderService.getById(orderid);

        if (order == null){
            return Result.error("-1", "没有找到该订单！");
        }
        if (order.getPaymentstatus()==1){
            return Result.error("-1", "该用户已支付！");
        }

        LocalDateTime payTime = LocalDateTime.now();
        UpdateWrapper<ActivityOrder> updateOrderPayment = new UpdateWrapper<>();
        updateOrderPayment.eq("id", orderid);
        updateOrderPayment.set("paymentStatus", 1);
        updateOrderPayment.set("paymentDate", payTime);

        boolean flag = activityOrderService.update(order, updateOrderPayment);
        if(!flag){
            return Result.error("-1", "修改失败！");
        }else{
            System.out.println(order.getPaymentstatus());
            return Result.success();
        }
    }

    @GetMapping("/deleteOrder")
    @ApiOperation(value = "delete order")
    public Result deleteOrder(@RequestParam int orderId){
        System.out.println(orderId);
        QueryWrapper<ActivityOrder> o= new QueryWrapper<ActivityOrder>().eq("id", orderId);
        if(o==null){
            return  Result.error("-1","This order does not exist!");
        }

        boolean u=activityOrderService.remove(o);

        if(u){
            return Result.success();
        }else{
            System.out.println(o);
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }


    /**
     * 创建订单
     */
    @PostMapping("/setUpOrder")
    @ApiOperation(value = "setup order")
    public Result setUpOrder(@RequestParam int userId, String facilityType, String activityType, List<String> daterange, String period, int number) throws Exception{
        System.out.println(userId);
        User u = userService.getOne(new QueryWrapper<User>().eq("id", userId));
        System.out.println(facilityType);
        Facility facility1 = facilityService.getOne(new QueryWrapper<Facility>().eq("facility_type", facilityType));
        Activity activity1 = activityService.getOne(new QueryWrapper<Activity>().eq("activity_to_which_facility", facility1.getId()).eq("activity_name", activityType));

        Integer uId = u.getId();
        if(u == null){
            return Result.error("-1", "没有找到该用户！");
        }
        if(facility1 == null){
            return Result.error("-1", "没有找到该器械！");
        }
        if(activity1 == null){
            return Result.error("-1", "没有找到该活动！");
        }



        Date date = new Date();

        ActivityOrder order = new ActivityOrder();

        rabbitTemplate.convertAndSend("mail.welcome", order);
        System.out.println("mail success");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startdate =  sdf.parse(daterange.get(0));
        Date enddate = sdf.parse(daterange.get(1));



        order.setUserid(uId);
        order.setFacilityId(facility1.getId());
        order.setActivityid(activity1.getId());
        order.setStarttime(startdate);
        order.setEndtime(enddate);
        // order.setPaymentmoney(paymentMoney);
        order.setPaymentdate(date);
        order.setPaymentstatus(1);
        order.setUsagestatus(0);
        order.setUsername(u.getUsername());
        order.setFacilitytype(facility1.getFacilityType());
        order.setActivitytype(activity1.getActivityName());
        rabbitTemplate.convertAndSend("mail.welcome", order);
        System.out.println("mail success");

        boolean flag = activityOrderService.save(order);

        if(flag){
            // ActivityOrder order1 = employeeMapper.getEmployee(employee.getId()).get(0);
            // rabbitTemplate.convertAndSend("mail.welcome", order);
            YMd ymdStart = getYMD(startdate);
            YMd ymdEnd = getYMD(enddate);
            // 查询记录
            List<Timetable> startToEndList = timetableService.getStartToEnd(ymdStart.getDay(), ymdEnd.getDay());

//            for (int i = 0; i < startToEndList.size(); i++) {
//
//            }

            for(Timetable tt:startToEndList){
                if (period.substring(0, 2) == "8:")
                {
                    int cap = tt.getEight();
                    tt.setEight(cap - number);
                }
                if (period.substring(0, 2) == "10")
                {
                    int cap = tt.getTen();
                    tt.setTen(cap - number);
                }
                if (period.substring(0, 2) == "14")
                {
                    int cap = tt.getFourteen();
                    tt.setEight(cap - number);
                }
                if (period.substring(0, 2) == "16")
                {
                    int cap = tt.getSixteen();
                    tt.setEight(cap - number);
                }
                if (period.substring(0, 2) == "20")
                {
                    int cap = tt.getTwenty();
                    tt.setEight(cap - number);
                }
            }

            Float paymentMoney = startToEndList.size() * activity1.getPrice();
            if (startToEndList.size() >= 7) paymentMoney = paymentMoney * 0.9F;
            order.setPaymentmoney(paymentMoney);

            return Result.success(order, "成功添加订单！");

        }else{
            return Result.error("-1", "添加订单失败。");
        }
    }


    /**
     * 获取年月日
     * @param date
     * @return
     */
    public static  YMd getYMD(Date date){
        String string = new SimpleDateFormat("yyyy-MM-dd").format(date).toString();
        System.out.println(string);
        // 2、日历类的时间操作
        Calendar calendar = Calendar.getInstance();
        YMd yMd = new YMd();
        yMd.setYear(calendar.get(Calendar.YEAR));
        yMd.setMonth(calendar.get(Calendar.MONTH) + 1);
        yMd.setDay(calendar.get(Calendar.DATE));
        return yMd;
    }
}


@Data
class YMd{
    private int year;
    private int month;
    private int day;
}