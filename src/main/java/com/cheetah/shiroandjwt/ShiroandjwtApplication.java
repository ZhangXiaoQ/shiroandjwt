package com.cheetah.shiroandjwt;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.cheetah.shiroandjwt.mapper"})
public class ShiroandjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroandjwtApplication.class, args);
    }

}
