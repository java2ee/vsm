/**
 * 
 */
package ru.hmt.vsm.model;

import java.sql.Timestamp;

/**
 * Класс получения бинарных данных из модели БД
 */
public class BinaryData {

    private String path;
    
    private Timestamp created;

    private byte[] data;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
