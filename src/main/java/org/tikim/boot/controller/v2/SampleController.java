package org.tikim.boot.controller.v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("SampleControllerV2")
@RequestMapping("/v2/sample")
public class SampleController {
    @GetMapping(value = "")
    public ResponseEntity get(@RequestParam String param)
    {
        return new ResponseEntity(param, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity post(@RequestParam String param, @RequestBody String param2)
    {

        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity put(@RequestParam String param, @RequestBody String param2)
    {

        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }

    @PatchMapping(value = "")
    public ResponseEntity patch(@RequestParam String param, @RequestBody String param2)
    {

        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }

    @DeleteMapping(value = "")
    public ResponseEntity delete(@RequestParam String param, @RequestBody String param2)
    {
        return new ResponseEntity(param + "\n" + param2, HttpStatus.OK);
    }
}
