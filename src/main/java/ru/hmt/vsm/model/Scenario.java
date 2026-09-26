/**
 * 
 */
package ru.hmt.vsm.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 */
@Schema(description = "${scenario.description}")
public class Scenario {

	private String mnemo;
	
	private String title;
	
	private String path;
    
	private Timestamp timestamp;
	
	private String summary;
    
	private Timestamp created;
	
	private Timestamp updated;

	private String status;

	public String getMnemo() {
		return mnemo;
	}

	public void setMnemo(String mnemo) {
		this.mnemo = mnemo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
