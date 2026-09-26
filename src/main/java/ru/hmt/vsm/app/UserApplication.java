/**
 * 
 */
package ru.hmt.vsm.app;

import java.util.Set;

import ru.funsys.avalanche.Application;
import ru.funsys.avalanche.rs.Resources;
import ru.funsys.avalanche.sql.Adapter;
import ru.hmt.vsm.model.Employee;

/**
 * 
 */
public class UserApplication extends Application implements Resources {

	private Adapter database;
	
	private String sqlLoginUser = "SELECT * FROM vsm.employee WHERE tnumber = ? OR telephone = ? OR email = ?";
	
	private String sqlUser = "SELECT * FROM vsm.employee WHERE tnumber = ?";

	@Override
	public void init() {
		// Переопределить параметры из конфигурации приложения
		sqlLoginUser = getParameterValue("sqlLoginUser", sqlLoginUser);
		sqlUser = getParameterValue("sqlUser", sqlUser);
	}

	@Override
	public void done() {
	}

	public String getLogin(String name) {
		String login = null;
		try {
			Employee[] employees = database.select(sqlLoginUser, Employee.class, name, name, name);
			if (employees.length == 1)  {
				if (employees.length == 1) login = employees[0].getTnumber();
			} else {
				error("VSM0001E", name);
			}
		} catch (Exception e) {
			error(e, "VSM0002E", name);
		}
		return login;
	}

	public Employee getEmployee(String name) {
		Employee employee = null;
		try {
			Employee[] employees = database.select(sqlUser, Employee.class, name);
			if (employees.length == 1)  {
				if (employees.length == 1) employee = employees[0];
			} else {
				error("VSM0001E", name);
			}
		} catch (Exception e) {
			error(e, "VSM0002E", name);
		}
		return employee;
	}
	
	@Override
	public boolean isServiceUsed() {
		return true;
	}


	@Override
	public String resourcePackage() {
		return "ru.hmt.vsm";
	}

}
