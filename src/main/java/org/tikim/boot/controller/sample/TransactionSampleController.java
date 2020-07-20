package org.tikim.boot.controller.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tikim.boot.domain.test.Test;
import org.tikim.boot.service.TestService;

import javax.annotation.Resource;
import java.util.List;

@RestController("TransactionSampleController")
@RequestMapping(value = "/sample/transaction")
public class TransactionSampleController
{
    @Resource
    TestService TestService;

    /**
     *
     * 해당 컨트롤러는 의도적으로 에러를 발생시킴.
     *
     * @Transactional 으로 트랜잭션을 묶어줌
     * 테스트 정보 저장 -> 의도적인 에러 생성
     *      => 롤백
     * */
    @GetMapping(value = "/rollback")
    public ResponseEntity rollback() throws JsonProcessingException {
        TestService.rollback();
        return null;
    }

    /**
     *
     * 해당 컨트롤러는 TEST TABLE의 값을 임의로 생성
     *
     * */
    @GetMapping(value = "/create")
    public ResponseEntity<Test> create() throws JsonProcessingException {

        return new ResponseEntity<Test>(TestService.create(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/xml")
    public ResponseEntity getXml(@RequestParam Long cursor, @RequestParam Long limit) {
        return new ResponseEntity<List<Test>>( TestService.getListByXML(cursor, limit), HttpStatus.OK);
    }

    @GetMapping(value = "/annotation")
    public ResponseEntity getAnnotation(@RequestParam Long cursor, @RequestParam Long limit) {
        return new ResponseEntity<List<Test>>( TestService.getListByXML(cursor, limit), HttpStatus.OK);
    }
}
