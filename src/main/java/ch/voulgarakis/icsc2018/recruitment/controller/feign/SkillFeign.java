package ch.voulgarakis.icsc2018.recruitment.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.SkillController;

@FeignClient("skill-service")
public interface SkillFeign extends SkillController {
}
