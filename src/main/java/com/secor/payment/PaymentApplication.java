package com.secor.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class PaymentApplication {

    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(PaymentApplication.class);

        // Set the active profile programmatically
        application.setAdditionalProfiles("dev");

        application.run(args);    }

}
