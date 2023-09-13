package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.DemoApplicationTest;
import com.example.demo.domain.Facility;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class FacilityServiceTest extends DemoApplicationTest {
    @Autowired
    private FacilityService facilityService;

    @Test
    public void testFindAll() {
        List<Facility> f_List = facilityService.list();

        Assert.assertNotNull(f_List);
    }

    @Test
    public void testDetail() {
        Facility fs = facilityService.getOne(new QueryWrapper<Facility>().eq("facility_type", "swimming pool"));

        System.out.println(fs);
    }
}