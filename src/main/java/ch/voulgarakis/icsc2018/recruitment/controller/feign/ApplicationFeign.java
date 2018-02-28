package ch.voulgarakis.icsc2018.recruitment.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.ApplicationController;

@FeignClient("application-service")
public interface ApplicationFeign extends ApplicationController {
}
