package ir.aamnapm.druid;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class DruidMain {
    public static void main(String[] args) {
        SpringApplication.run(DruidMain.class, args);
    }
}