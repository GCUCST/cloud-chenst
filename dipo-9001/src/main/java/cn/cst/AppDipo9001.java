package cn.cst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
//@EnableEurekaClient
public class AppDipo9001 {
    public static void main(String[] args) {
        SpringApplication.run(AppDipo9001.class, args);
    }
}

