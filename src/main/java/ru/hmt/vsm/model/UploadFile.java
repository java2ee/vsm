/**
 * 
 */
package ru.hmt.vsm.model;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс структуры загрузки файлов
 * 
 */
@Schema(name = "UploadFile", title="${binary.uploadFile}")
public class UploadFile {

	@Schema(name="upload", type="string", format="binary", description = "${binary.uploadFile}")
	@FormDataParam("upload")
	private InputStream upload;

	@Hidden
	@FormDataParam("upload")
	private FormDataContentDisposition disposition;
	
	public InputStream getUpload() {
		return upload;
	}

	public void setUpload(InputStream upload) {
		this.upload = upload;
	}

	public FormDataContentDisposition getDisposition() {
		return disposition;
	}

	public void setDisposition(FormDataContentDisposition disposition) {
		this.disposition = disposition;
	}

}

