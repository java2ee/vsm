/**
 * 
 */
package ru.hmt.vsm.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 */
@Schema(description = "Сотрудник")
public class Employee {

	@Schema(description = "Табельный номер сотрудника", example = "24003423", requiredMode = Schema.RequiredMode.REQUIRED)
	private String tnumber;

	@Schema(description = "Имя сотрудника", example = "Татьяна", requiredMode = Schema.RequiredMode.REQUIRED)
	private String fname;

	@Schema(description = "Фамилия сотрудника", example = "Иванова", requiredMode = Schema.RequiredMode.REQUIRED)
	private String lname;

	@Schema(description = "Отчество сотрудника", example = "Ивановна", requiredMode = Schema.RequiredMode.REQUIRED)
	private String pname;

	@Schema(description = "Телефон сотрудника", example = "+79998887766", requiredMode = Schema.RequiredMode.REQUIRED)
	private String telephone;

	@Schema(description = "Адрес электронной почты", example = "user@domain.ru", requiredMode = Schema.RequiredMode.REQUIRED)
	private String email;

	public String getTnumber() {
		return tnumber;
	}

	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
