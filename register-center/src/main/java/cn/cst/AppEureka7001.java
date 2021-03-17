package cn.cst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class AppEureka7001 {
    public static void main(String[] args) {
        SpringApplication.run(AppEureka7001.class, args);
    }
}

