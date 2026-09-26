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

	private Adapter database;

	private String selectScenario = "SELECT mnemo, title, path, upload_time, summary, created updated, status WHERE mnemo = ?";

	@Override
	public void init() {
		// Возможеое переопределение параметров в конфигурационном файле
		selectScenario = getParameterValue("selectScenario", selectScenario);
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
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
}
