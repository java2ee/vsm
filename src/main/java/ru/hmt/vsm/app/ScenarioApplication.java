/**
 * 
 */
package ru.hmt.vsm.app;

import ru.funsys.avalanche.Application;
import ru.funsys.avalanche.sql.Adapter;

/**
 * 
 */
public class ScenarioApplication extends Application {

	private Adapter database;

	private String selectScenario = "SELECT mnemo, title, path, upload_time, summary, created AS created FROM vsm.scenario WHERE path = ?";
	
    mnemo varchar(16) NOT NULL,
    title varchar(128) NOT NULL,
    icon bytea,
    path varchar(256),
    upload_time timestamp without time zone,
    summary varchar(4096) NOT NULL,
    created time without time zone DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone,
    status varchar(1) DEFAULT 'D',

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

}
