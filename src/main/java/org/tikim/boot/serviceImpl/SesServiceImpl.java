package org.tikim.boot.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.tikim.boot.service.SesService;
import org.tikim.boot.domain.test.SampleMail;
import org.tikim.boot.util.SesSender;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class SesServiceImpl implements SesService {

    @Resource
    private SesSender sesSender;

    @Autowired
    private Configuration configuration;

    @Value("${ses.domain}")
    private String domain;

    @Override
    public ResponseEntity send(SampleMail sampleMail) throws JsonProcessingException {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        try{
            Template template = configuration.getTemplate("mail-sample.ftl");
            Map<String, Object> dataModel = new HashMap();
            dataModel.put("message", "Hello");
            StringWriter templateStringWriter = new StringWriter();
            template.process(dataModel, templateStringWriter);
            String result = templateStringWriter.toString();

            return new ResponseEntity<String>(result,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(new ObjectMapper().writeValueAsString(e.getMessage()));
            return new ResponseEntity<String>("ERROR",HttpStatus.OK);
        }

    }
}
