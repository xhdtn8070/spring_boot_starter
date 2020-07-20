package org.tikim.boot.domain.test;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SampleMail {
    @Email
    @NotNull
    @ApiModelProperty(notes = "to", example = "tikim1475@gmail.com")
    protected String to;

    @ApiModelProperty(notes = "subject", example = "메일 제목")
    @NotNull
    protected String subject;

    @ApiModelProperty(notes = "content", example = "메일 내용")
    @NotNull
    protected String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
