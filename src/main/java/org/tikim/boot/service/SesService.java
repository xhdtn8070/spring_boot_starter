package org.tikim.boot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.tikim.boot.domain.test.SampleMail;

public interface SesService {

    ResponseEntity send(SampleMail sampleMail) throws JsonProcessingException;
}
