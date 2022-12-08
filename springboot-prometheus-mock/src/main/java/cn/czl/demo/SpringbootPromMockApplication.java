package cn.czl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zackchen
 */ //jar包
@SpringBootApplication
public class SpringbootPromMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPromMockApplication.class, args);
    }

}

//war包
//@SpringBootApplication
//public class SpringbootJarWarApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SpringbootJarWarApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootJarWarApplication.class, args);
//    }
//}



