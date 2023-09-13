package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.domain.Card;
import com.example.demo.domain.User;
import com.example.demo.service.CardService;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 会员卡 前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-16
 */

@RestController
@RequestMapping("/card")
@Api(tags = "CardController")
public class CardController {
    @Resource
    private CardService cardService;

    @GetMapping("/showAllCards")
    @ApiOperation(value = "返回该用户所有的card")
    public Result showAllCard(@RequestParam(value = "id") Integer id) {
        List<Card> card_list = cardService.list();
        List<Card> res = new ArrayList<>();

        for (Card c : card_list) {
            if (Objects.equals(c.getUserid(), id)) {
                res.add(c);
            }
        }

//        if (res == null) {
//            return Result.success(null, " This user hasn't added card.");
//        }
        return Result.success(res, "Find Success");
    }


    @ApiOperation(value = "添加新的银行卡")
    @PostMapping("/addCard")
    public Result addCard(@RequestBody Card card){
        Card c=cardService.getOne(new QueryWrapper<Card>().eq("number", card.getNumber()));
        if(c!=null){
            return  Result.error("-1","This card exists!");
        }

        boolean u=cardService.save(card);

        if(u){
            return Result.success();
        }else{
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }

    @ApiOperation(value = "删除银行卡")
    @PostMapping("/deleteCard")
    public Result deleteCard(@RequestBody Card card){
        System.out.println(card);
        QueryWrapper<Card> c= new QueryWrapper<Card>().eq("number", card.getNumber());
        if(c==null){
            return  Result.error("-1","This card does not exist!");
        }

        boolean u=cardService.remove(c);

        if(u){
            return Result.success();
        }else{
            System.out.println(c);
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }
}

