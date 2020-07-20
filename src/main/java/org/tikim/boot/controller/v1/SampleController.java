package org.tikim.boot.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tikim.boot.annotation.Xss;
import org.tikim.boot.domain.test.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController("SampleControllerV1")
@RequestMapping("/v1/sample")
public class SampleController {
    @GetMapping(value = "")
    public ResponseEntity get(@RequestParam String param)
    {
        return new ResponseEntity(param, HttpStatus.OK);
    }

    @Xss
    @PostMapping(value = "")
    public ResponseEntity post(@RequestParam String param, @RequestBody String param2) throws IOException {
        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }
    @Xss
    @PutMapping(value = "")
    public ResponseEntity put(@RequestParam String param, @RequestBody String param2)
    {

        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }
    @Xss
    @PatchMapping(value = "")
    public ResponseEntity patch(@RequestParam String param, @RequestBody String param2)
    {

        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }
    @Xss
    @DeleteMapping(value = "")
    public ResponseEntity delete(@RequestParam String param, @RequestBody String param2)
    {
        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }

    @Xss
    @PostMapping(value = "/test")
    public ResponseEntity postTest(@RequestParam String param, @RequestBody Test param2) throws IOException {
        int a = 1/0;
        return new ResponseEntity(param + "\n" + new ObjectMapper().writeValueAsString(param2), HttpStatus.OK);
    }
}
