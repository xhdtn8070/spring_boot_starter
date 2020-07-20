package org.tikim.boot.domain.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.tikim.boot.annotation.Xss;
import org.tikim.boot.annotation.XssExclude;

import java.sql.Date;

@Xss
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Test2 {
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
