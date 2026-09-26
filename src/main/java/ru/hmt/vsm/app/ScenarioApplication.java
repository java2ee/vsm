/**
 * 
 */
package ru.hmt.vsm.app;

import ru.funsys.avalanche.Application;
import ru.funsys.avalanche.sql.Adapter;
import ru.hmt.vsm.model.Scenario;

/**
 * 
 */
public class ScenarioApplication extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3861588466140446483L;

	private Adapter database;

	private String selectScenario = "SELECT mnemo, title, path, upload_time, summary, created updated, status FROM vsm.scenario WHERE mnemo = ?";
	
	private String selectAllScenario = "SELECT mnemo, title, path, upload_time, summary, created, updated, status FROM vsm.scenario";
	
	private String insertScenario = "INSERT INTO vsm.scenario (mnemo, title, summary) VALUES ?, ?, ?";

	@Override
	public void init() {
		// Возможеое переопределение параметров в конфигурационном файле
		selectScenario = getParameterValue("selectScenario", selectScenario);
		selectAllScenario = getParameterValue("selectAllScenario", selectAllScenario);
		insertScenario = getParameterValue("insertScenario", insertScenario);
		
	}

	@Override
	public void done() {
	}

	/**
	 * Получить описание сценария игры
	 * 
	 * @param mnemo мнемокод сценария
	 * 
	 * @return описание сценария или null, если сценарий не найден
	 * 
	 * @throws Exception
	 */
	public Scenario getScenario(String mnemo) throws Exception {
		Scenario scenario = null;
		Scenario[] scenarios = database.select(selectScenario, Scenario.class, mnemo);
		if (scenarios.length == 1) {
			scenario = scenarios[0]; 
		}
		return scenario;
	}

	/**
	 * Добавить игровой сценарий
	 * 
	 * @throws Exception
	 */
	public void addScenario(String mnemo, String title, String symmary) throws Exception {
		database.execute(insertScenario, mnemo, title, symmary);
	}

	/**
	 * Получить описание всех сценариев игр
	 * 
	 * @return массив записей сценариев
	 * 
	 * @throws Exception
	 */
	public Scenario[] getScenarios() throws Exception {
		return database.select(selectAllScenario, Scenario.class);
	}

}
