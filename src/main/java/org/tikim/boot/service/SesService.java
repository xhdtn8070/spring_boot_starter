package org.tikim.boot.service;

import org.springframework.http.ResponseEntity;
import org.tikim.boot.domain.test.SampleMail;

public interface SesService {

    ResponseEntity send(SampleMail sampleMail);
}
