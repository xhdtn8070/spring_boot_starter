package org.tikim.boot.controller.sample;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController("SampleSlackNotiController")
@RequestMapping("/sample/exception")
public class SampleSlackNotiController {

    @PostMapping(value = "/arithmetic")
    public ResponseEntity ArithmeticException(@RequestParam String param, @RequestBody String param2) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(IOUtils.toString(request.getReader()));
        int a = 1/0;
        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }

}
