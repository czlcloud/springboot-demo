package cn.czl.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zackchen
 */
@Slf4j
@SpringBootApplication
public class SpringbootGSDApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGSDApplication.class, args);
    }

    @Value("${user.home}")
    private String userName;

    @Value("${server.port}")
    private String port;
    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                InetAddress ia = InetAddress.getLocalHost();
                //获取本机内网IP
                log.info("启动成功：" + "http://" + ia.getHostAddress() + ":" + port + "/");
                logger.info("${user.home} ：" + userName);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        };
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



