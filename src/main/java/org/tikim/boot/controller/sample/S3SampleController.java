package org.tikim.boot.controller.sample;

import com.google.common.net.HttpHeaders;
import io.swagger.annotations.*;
import org.tikim.boot.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController("S3SampleController")
@RequestMapping(value = "/sample/s3")
public class S3SampleController {
    @Resource
    private S3Service s3Service;

    @PostMapping(value = "/file")
    public @ResponseBody
    ResponseEntity uploadFile(@ApiParam(required = true) @RequestBody MultipartFile file) throws Exception {

        return new ResponseEntity<String>(s3Service.uploadObject(file), HttpStatus.CREATED);
    }


    @GetMapping(value = "/file")
    public @ResponseBody
    ResponseEntity getFile(@ApiParam(required = true) @RequestParam String path
            ,@ApiParam(required = true) @RequestParam String savedName ) throws Exception {
        org.springframework.core.io.Resource resource = s3Service.getObject(path,savedName);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String contentType=null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException e){
            System.out.println("default type null");
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + savedName)
                .body(resource);
    }

    @DeleteMapping(value = "/file")
    public @ResponseBody
    ResponseEntity deleteFile(@ApiParam(required = true) @RequestParam String path
            ,@ApiParam(required = true) @RequestParam String savedName
            ,@ApiParam(required = true) @RequestParam boolean isHard) throws Exception {
        s3Service.deleteObject(path,savedName,isHard);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    /**
        Swagger 버그로
        Swagger에서는 여러 개의 파일을 받을 수 없음.
        그러나 Postman에서 작동하는 것을 확인함
        Body - form-data - file형태 지정 - key : files
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = "files", required = true, dataType = "__file", paramType = "form")
    )
    @PostMapping(value = "/files")
    public ResponseEntity uploadFiles(@ApiParam(name = "files") @RequestParam(value = "files",required = true) MultipartFile[] files) throws Exception {
        List<String> result = new ArrayList<>();

//        System.out.println(new ObjectMapper().writeValueAsString(files));
        for(MultipartFile file : files){
            result.add(s3Service.uploadObject(file));
        }
        return new ResponseEntity<List<String>>(result, HttpStatus.CREATED);
    }
}
