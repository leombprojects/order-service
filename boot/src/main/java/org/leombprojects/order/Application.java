package org.leombprojects.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackageClasses = Application.class)
@ComponentScan(basePackages = "org.leombprojects")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
