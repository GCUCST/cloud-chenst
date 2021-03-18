package cn.cst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OAuthSourceApplication9002 {
  public static void main(String[] args) {
    SpringApplication.run(OAuthSourceApplication9002.class, args);
  }
}
