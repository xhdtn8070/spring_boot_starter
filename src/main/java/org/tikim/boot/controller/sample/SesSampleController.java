package org.tikim.boot.controller.sample;

import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tikim.boot.domain.test.SampleMail;
import org.tikim.boot.service.SesService;

import javax.annotation.Resource;

@RestController("SesSampleController")
@RequestMapping(value = "/sample/ses")
public class SesSampleController {
    @Resource
    private SesService sesService;

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity uploadFile(@ApiParam(required = true) @RequestBody SampleMail sampleMail) throws Exception {

        return sesService.send(sampleMail);
    }

}
