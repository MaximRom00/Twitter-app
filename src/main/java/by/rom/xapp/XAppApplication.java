package by.rom.xapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class XAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(XAppApplication.class, args);
    }

}
