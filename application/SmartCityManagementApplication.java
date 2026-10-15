package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller", "service", "repository"})
public class SmartCityManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartCityManagementApplication.class, args);
    }
}