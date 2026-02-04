package com.fresh.xy.sample2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.fresh.xy.sample2", "com.fresh.xy.common", /*"com.fresh.xy.redis",*/ "com.fresh.xy.sample.api"})
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients({"com.fresh.xy.sample.api"})
@EnableRetry
public class Sample2Application {

    public static void main(String[] argv) {
        SpringApplication.run(Sample2Application.class, argv);
    }
}
