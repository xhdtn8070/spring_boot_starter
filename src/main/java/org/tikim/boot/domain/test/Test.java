package org.tikim.boot.domain.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import org.tikim.boot.annotation.Xss;
import org.tikim.boot.annotation.XssExclude;

import java.sql.Date;
import java.util.List;

@Xss
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Test {
	@ApiModelProperty(notes = "id", example = "1", hidden = true)
	private Long id;

	@XssExclude
	@ApiModelProperty(notes = "text", example = "text")
	private String text;

	@ApiModelProperty(notes = "text2", example = "text2")
	private String text2;

	@ApiModelProperty(notes = "num", example = "1")
	private Integer num;

	@ApiModelProperty(notes = "is_deleted", example = "false")
	private Boolean is_deleted;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@ApiModelProperty(hidden = true)
	private Date created_at;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@ApiModelProperty(hidden = true)
	private Date updated_at;
	private List<String> testStringList;
	private String[] testStringArrays;
	private List<Test2> test2List;
	private Test2[] test2Arrays;
	private Test2 test2;

	public List<String> getTestStringList() {
		return testStringList;
	}

	public void setTestStringList(List<String> testStringList) {
		this.testStringList = testStringList;
	}

	public String[] getTestStringArrays() {
		return testStringArrays;
	}

	public void setTestStringArrays(String[] testStringArrays) {
		this.testStringArrays = testStringArrays;
	}

	public Test2[] getTest2Arrays() {
		return test2Arrays;
	}

	public void setTest2Arrays(Test2[] test2Arrays) {
		this.test2Arrays = test2Arrays;
	}

	public List<Test2> getTest2List() {
		return test2List;
	}

	public void setTest2List(List<Test2> test2List) {
		this.test2List = test2List;
	}

	public Test2 getTest2() {
		return test2;
	}

	public void setTest2(Test2 test2) {
		this.test2 = test2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
