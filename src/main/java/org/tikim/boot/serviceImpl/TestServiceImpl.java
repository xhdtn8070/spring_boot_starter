package org.tikim.boot.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tikim.boot.mapper.TestMapper;
import org.tikim.boot.domain.test.Test;
import org.tikim.boot.service.TestService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Transactional
@Service
public class TestServiceImpl implements TestService {
    @Resource
    TestMapper testMapper;

    @Override
    public List<Test> getListByAnnotation(Long cursor, Long limit) {
        List<Test> testList =testMapper.getTestListAnnotation(cursor,limit);
       return testList;
    }

    @Override
    public List<Test> getListByXML(Long cursor, Long limit) {
        List<Test> testList =testMapper.getTestListByXML(cursor,limit);
        return testList;
    }

    @Override
    public Test create() {
        Test test = new Test();
        test.setNum(new Random().nextInt());
        test.setText(UUID.randomUUID().toString());
        testMapper.testCreate(test);
        return test;
    }

    @Override
    public void rollback() throws JsonProcessingException {
        List<Test> testList =testMapper.getTestListAnnotation(1L,10L);
        System.out.println(new ObjectMapper().writeValueAsString(testList));

        Test test = create();
        System.out.println(new ObjectMapper().writeValueAsString(test));

        /**
         * 의도적인 에러 발생
         */
        int a = 1/0;
    }
}
