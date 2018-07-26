package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
//Scan the package, where the restcontrolls lay
@ComponentScan({"restcontrol"})
public class Activator {

    public static void main(String[] args){
        SpringApplication.run(Activator.class,args);
    }

    /** Start initiation can be used to create an default network, to load a fast version of the network
    @Bean
    ApplicationRunner run(){
        return args -> Stream.of("Hello");
    }
    */
}