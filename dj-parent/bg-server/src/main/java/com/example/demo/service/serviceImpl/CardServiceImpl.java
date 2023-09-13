package com.example.demo.service.serviceImpl;

import com.example.demo.domain.Card;
import com.example.demo.dao.CardMapper;
import com.example.demo.service.CardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员卡 服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-16
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

}
