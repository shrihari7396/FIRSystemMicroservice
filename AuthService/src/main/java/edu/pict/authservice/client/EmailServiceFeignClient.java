package edu.pict.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient( name = "EmailService")
public interface EmailServiceFeignClient {

}
