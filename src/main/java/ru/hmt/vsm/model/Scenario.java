/**
 * 
 */
package ru.hmt.vsm.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.funsys.avalanche.rs.databind.TimestampDeserializer;
import ru.funsys.avalanche.rs.databind.TimestampSerializer;

/**
 * 
 */
@Schema(description = "${scenario.description}")
public class Scenario {

	@Schema(description = "Мнемокод (краткое обозначение) сценария")
	private String mnemo;
	
	@Schema(description = "Название сценария")
	private String title;
	
	@Schema(description = "Относительный URI загруженной иконки")
	private String path;
    
	@Schema(description = "Дата загрузки иконки")
	@JsonDeserialize(using = TimestampDeserializer.class)
	@JsonSerialize(using = TimestampSerializer.class)
	private Timestamp upload_time;
	
	@Schema(description = "Описание сценария")
	private String summary;
    
	@Schema(description = "Дата создания записи")
	@JsonDeserialize(using = TimestampDeserializer.class)
	@JsonSerialize(using = TimestampSerializer.class)
	private Timestamp created;
	
	@Schema(description = "Дата модификации записи")
	private Timestamp updated;

	@Schema(description = "Статус сценария E - enable, D - disable")
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

	public Timestamp getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
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
