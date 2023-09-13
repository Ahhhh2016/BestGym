package com.example.demo.config;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


public class 代码生成器 {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        com.baomidou.mybatisplus.generator.config.GlobalConfig gc = new GlobalConfig();
        gc.setActiveRecord(true);// 是否支持ar模式
        gc.setOutputDir("F:\\bg\\login-regis-demo1\\login-regis-demo1\\login-regis-demo1\\demo\\src\\main\\java");
        gc.setFileOverride(true);  //文件覆盖
        gc.setOpen(false);      //生成代码后是否打开文件夹
        gc.setIdType(IdType.AUTO); // 主键策略
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setAuthor("");
        mpg.setGlobalConfig(gc);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db?useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.demo");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.serviceImpl");
        pc.setMapper("dao");
        pc.setEntity("domain");
        pc.setXml("dao");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(new String[]{"Timetable"}); // 表名

        mpg.setStrategy(strategy);
        mpg.execute();
    }

}

