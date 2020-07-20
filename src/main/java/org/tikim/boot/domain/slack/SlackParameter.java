package org.tikim.boot.domain.slack;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.Format.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlackParameter  {
	private String channel;
	private String username;
	private String text;
	
	@JsonProperty("attachments") private List<SlackAttachment> slackAttachments;
	public SlackParameter() {
		slackAttachments = new ArrayList<SlackAttachment>();
	}

	public List<SlackAttachment> getSlackAttachments() {
		return slackAttachments;
	}

	public void setSlackAttachments(List<SlackAttachment> slackAttachments) {
		this.slackAttachments = slackAttachments;
	}

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


}
