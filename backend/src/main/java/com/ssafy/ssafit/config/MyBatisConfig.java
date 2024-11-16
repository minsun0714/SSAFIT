package com.ssafy.ssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.ssafy.ssafit.dao")
@Configuration
public class MyBatisConfig {

}
