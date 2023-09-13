package com.example.demo.service.serviceImpl;

import com.example.demo.domain.Activity;
import com.example.demo.domain.Timetable;
import com.example.demo.dao.TimetableMapper;
import com.example.demo.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-17
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Override
    public List<Timetable> getStartToEnd(int start, int end) {
        return this.baseMapper.getStartToEnd(start,end);
    }
}
