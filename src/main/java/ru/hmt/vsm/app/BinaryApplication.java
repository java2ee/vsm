/**
 * 
 */
package ru.hmt.vsm.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import ru.funsys.avalanche.Application;
import ru.funsys.avalanche.sql.Adapter;
import ru.hmt.vsm.model.BinaryData;
import ru.hmt.vsm.model.UploadFile;

/**
 * 
 */
public class BinaryApplication extends Application {

	
	private Adapter database;

	private String prefixScenarioIcon = "scenario";

	private String updateScenarioIcon = "UPDATE vsm.scenario SET path = ?, icon = ?, upload_time = CURRENT_TIMESTAMP WHERE mnemo = ?";

	private String selectScenarioIcon = "SELECT path, icon AS data, upload_time AS created FROM vsm.scenario WHERE path = ?";
	
	private String createBlock = "CREATE TABLE vsm.block (\r\n"
			+ "    id character varying(32) NOT NULL,\r\n"
			+ "    title character varying(32),\r\n"
			+ "    type character varying(32),\r\n"
			+ "    timelimitsec integer,\r\n"
			+ "    text character varying(4094),\r\n"
			+ "    timetext character varying(4094),\r\n"
			+ "    dloyalty integer,\r\n"
			+ "    dsafety integer,\r\n"
			+ "    icon bytea,\r\n"
			+ "    path varchar(256),\r\n"
			+ "    upload_time timestamp without time zone,\r\n"
			+ "    CONSTRAINT block_pk PRIMARY KEY (id))";
	private String insertBlock = "INSERT INTO vsm.block (id, title, type, timelimitsec, text, timetext, dloyalty, dsafety) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private String createBlSetflag = "CREATE TABLE vsm.bl_setflag (\r\n"
			+ "    bl_id character varying(32) NOT NULL,\r\n"
			+ "    flag character varying(32) NOT NULL,\r\n"
			+ "    CONSTRAINT bl_setflag_pk PRIMARY KEY (bl_id, flag),\r\n"
			+ "    CONSTRAINT bl_setflag_block_fk FOREIGN KEY (bl_id)\r\n"
			+ "            REFERENCES vsm.block (id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
	private String insertBlSetflag = "INSERT INTO vsm.bl_setflag VALUES (?, ?)";

	
	private String createAudioTimeLine = "CREATE TABLE vsm.audiotimeline (\r\n"
			+ "    bl_id character varying(32) NOT NULL,\r\n"
			+ "    assetid character varying(32) NOT NULL,\r\n"
			+ "    offsetms integer,\r\n"
			+ "    volume number(4,2),\r\n"
			+ "    loop boolean DEFAULT false,\r\n"
			+ "    stop character varying(32),\r\n"
			+ "    CONSTRAINT audiotimeline_pk PRIMARY KEY (bl_id, assetid),\r\n"
			+ "    CONSTRAINT audiotimeline_block_fk FOREIGN KEY (bl_id)\r\n"
			+ "            REFERENCES vsm.block (id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
	private String insertAudioTimeLine = "INSERT INTO vsm.audiotimeline VALUES (?, ?, ?, ?, ?, ?)";
	

	private String createChoice = "CREATE TABLE vsm.choice (\r\n"
			+ "    bl_id character varying(32) NOT NULL,\r\n"
			+ "    id character varying(32) NOT NULL,\r\n"
			+ "    text character varying(1024),\r\n"
			+ "    dloyalty integer,\r\n"
			+ "    dsafery integer,\r\n"
			+ "    CONSTRAINT choice_pk PRIMARY KEY (bl_id, id),\r\n"
			+ "    CONSTRAINT choice_block_fk FOREIGN KEY (bl_id)\r\n"
			+ "            REFERENCES vsm.block (id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
			
	private String insertChoice = "INSERT INTO vsm.choice VALUES (?, ?, ?, ?, ?)";
	
	private String createSetflag = "CREATE TABLE vsm.setflag (\r\n"
			+ "    bl_id character varying(32) NOT NULL,\r\n"
			+ "    ch_id character varying(32) NOT NULL,\r\n"
			+ "    flag character varying(32) NOT NULL,\r\n"
			+ "    CONSTRAINT setflag_pk PRIMARY KEY (bl_id, ch_id, flag),\r\n"
			+ "    CONSTRAINT setflag_choice_fk FOREIGN KEY (bl_id, ch_id)\r\n"
			+ "            REFERENCES vsm.choice (bl_id, id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
	private String insertSetflag = "INSERT INTO vsm.setflag VALUES (?, ?, ?)";

	private String createRun = "CREATE TABLE vsm.run (\r\n"
			+ "    id character varying(32) NOT NULL,\r\n"
			+ "    title character varying(32) NOT NULL,\r\n"
			+ "    submission boolean DEFAULT false,\r\n"
			+ "    jurypool boolean DEFAULT false,\r\n"
			+ "    startnode character varying(32),\r\n"
			+ "    icon bytea,\r\n"
			+ "    path varchar(256),\r\n"
			+ "    upload_time timestamp without time zone,\r\n"
			+ "    CONSTRAINT run_pk PRIMARY KEY (id))";
	private String insertRun = "INSERT INTO vsm.run (id, title, submission, jurypool, startnode) VALUES (?, ?, ?, ?, ?)";

	private String createNode = "CREATE TABLE vsm.node (\r\n"
			+ "    rn_id character varying(32) NOT NULL,\r\n"
			+ "    id character varying(32) NOT NULL,\r\n"
			+ "    template character varying(32) NOT NULL,\r\n"
			+ "    CONSTRAINT node_pk PRIMARY KEY (rn_id, id),"
			+ "    CONSTRAINT node_run_fk FOREIGN KEY (rn_id)\r\n"
			+ "            REFERENCES vsm.run (id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
	private String insertNode = "INSERT INTO vsm.node VALUES (?, ?, ?)";
	
	private String createNext = "CREATE TABLE vsm.next (\r\n"
			+ "    rn_id character varying(32) NOT NULL,\r\n"
			+ "    nd_id character varying(32) NOT NULL,\r\n"
			+ "    choice character varying(32) NOT NULL,\r\n"
			+ "    next character varying(32),\r\n"
			+ "    CONSTRAINT next_pk PRIMARY KEY (rn_id, nd_id, choice),"
			+ "    CONSTRAINT next_node_fk FOREIGN KEY (rn_id, nd_id)\r\n"
			+ "            REFERENCES vsm.node (rn_id, id)\r\n"
			+ "            ON UPDATE NO ACTION\r\n"
			+ "            ON DELETE NO ACTION)";
	private String insertNext = "INSERT INTO vsm.next VALUES (?, ?, ?, ?)";

	@Override
	public void init() {
		// При необходимости можно переопределить в конфигурации
		prefixScenarioIcon = getParameterValue("prefixScenarioIcon", prefixScenarioIcon);
		updateScenarioIcon = getParameterValue("updateScenarioIcon", updateScenarioIcon);
		selectScenarioIcon = getParameterValue("selectScenarioIcon", selectScenarioIcon);
		createBlock = getParameterValue("createBlock", createBlock);
		insertBlock = getParameterValue("insertBlock", insertBlock);
		createBlSetflag = getParameterValue("createBlSetflag", createBlSetflag);
		insertBlSetflag = getParameterValue("insertBlSetflag", insertBlSetflag);
		createAudioTimeLine = getParameterValue("createAudioTimeLine", createAudioTimeLine);
		insertAudioTimeLine = getParameterValue("insertAudioTimeLine", insertAudioTimeLine);
		createChoice = getParameterValue("createChoice", createChoice);
		insertChoice = getParameterValue("insertChoice", insertChoice);
		createRun = getParameterValue("createChoice", createChoice);
		insertRun = getParameterValue("insertChoice", insertChoice);
		createNode = getParameterValue("createNode", createNode);
		insertNode = getParameterValue("insertNode", insertNode);
		createNext = getParameterValue("createNext", createNext);
		insertNext = getParameterValue("insertNext", insertNext);
	}

	@Override
	public void done() {
	}

	public String saveScenarioIcon(String mnemo, UploadFile file) throws Exception {
		String path = '/' + prefixScenarioIcon + '/' + mnemo + '/' + file.getDisposition().getFileName(isFunction());
		database.execute(updateScenarioIcon, path, readAllBytes(file.getUpload()), mnemo);
		return path;
	}

	public void deleteScenarioIcon(String mnemo) throws Exception {
		database.execute(updateScenarioIcon, null, null);
	}

	public BinaryData find(String path) throws Exception {
		BinaryData binaryData = null; 
		String[] parameters = path.split("/");
		if (parameters.length > 2) {
			BinaryData[] datas = null;
			String table = parameters[1]; // 0 элемент массива имеет нулевую длину
			if (table.equals(prefixScenarioIcon)) {
				datas = database.select(selectScenarioIcon, BinaryData.class, path);
			}
			if (datas != null && datas.length == 1) binaryData = datas[0];
		}
		return binaryData;
	}
	
    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
    	final int bufLen = 4096;
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                outputStream.write(buf, 0, readLen);
            return outputStream.toByteArray();
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }    	
    }

    public List<Map<String, Object>> select(String table) throws Exception {
    	ArrayList<Map<String, Object>> list = new ArrayList<>();
   		ResultSet resultset = database.select("SELECT * FROM vsm." + table);
   		ResultSetMetaData metaData = resultset.getMetaData();
   		int columnCount = metaData.getColumnCount();
   		while (resultset.next()) {
   			Map<String, Object> map = new HashMap<>();
   			list.add(map);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i); 
                Object columnValue = resultset.getObject(i);    
                map.put(columnName, columnValue);
            }
    	}
    	return list;
    }
    
    /**
     * Загрузка описания игровых сценариев в БД
     * 
     * @param map структура данных, сформированная на основании полученного JSON  
     * @return список таблиц.
     * 
     * @throws Exception 
     */
    public List<String> loadData(Map<String, Object> map) throws Exception {
    	List<String> tables = new ArrayList<>(); // список таблиц должен быть в обратном порядке их создания
    	tables.add("next");
    	tables.add("node");
    	tables.add("run");
    	tables.add("setflag");
    	tables.add("choice");
    	tables.add("audiotimeline");
    	tables.add("bl_setflag");
    	tables.add("block");

		AtomicReference<Exception> exception = new AtomicReference();

		// Удаление таблиц
    	tables.forEach(table -> {
    		try {
    			database.execute("DROP TABLE IF EXISTS vsm." + table);
    		}catch (Exception e) {
				exception.set(e);
				logger.error(e);
			}
    	});
		if (exception.get() != null) throw exception.get(); 
    	
    	
    	Map<String, Object> timer = (Map) map.get("timer");
		List<Object> audioAssets = (List) map.get("audioAssets");
		Map<String, Object> audioCueContract = (Map) map.get("audioCueContract");
		String schemaVersion = (String) map.get("schemaVersion");
		List<Map> blockTemplates = (List) map.get("blockTemplates");
		Map<String, Object> scale = (Map) map.get("scale");
		String source = (String) map.get("source");
		Map<String, Object> selectionPolicies = (Map) map.get("selectionPolicies");
		List<Map<String, Object>> runs = (List) map.get("runs");

		database.execute(createBlock);
		database.execute(createBlSetflag);
		database.execute(createAudioTimeLine);
		database.execute(createChoice);
		database.execute(createSetflag);
		database.execute(createRun);
		database.execute(createNode);
		database.execute(createNext);

		blockTemplates.forEach(block -> {
			try {
				Map<String, Object> timeout = (Map<String, Object>) block.get("timeout");
				database.execute(insertBlock, block.get("id"), block.get("title"), block.get("type"), block.get("timeLimitSec"), block.get("text"), timeout.get("text"), timeout.get("dLoyalty"), timeout.get("dSafety"));

				List<String> setFlags = (List<String>) timeout.get("setFlags");
				
				setFlags.forEach(flag -> {
					if (exception.get() == null) {
						try {
							database.execute(insertBlSetflag, block.get("id"), flag);
						} catch (Exception e) {
							exception.set(e);
							logger.error(e);
						}
					}
				});
				if (exception.get() != null) throw exception.get(); 
				
				List<Map<String, Object>> audioTimelines = (List<Map<String, Object>>) block.get("audioTimeline");
				audioTimelines.forEach(audioTimeline -> {
					if (exception.get() == null) {
						try {
							database.execute(insertAudioTimeLine, block.get("id"), audioTimeline.get("assetId"), audioTimeline.get("offsetMs"), audioTimeline.get("volume"), audioTimeline.get("loop"), audioTimeline.get("stop"));
						} catch (Exception e) {
							exception.set(e);
							logger.error(e);
						}
					}
				});
				if (exception.get() != null) throw exception.get(); 
				
				List<Map> choices = (List<Map>) block.get("choices");
				choices.forEach(choice -> {
					try {
						if (exception.get() == null) {
							database.execute(insertChoice, block.get("id"), choice.get("id"), choice.get("text"), choice.get("dLoyalty"), choice.get("dSafety"));

							List<String> flags = (List<String>) choice.get("setFlags");
							flags.forEach(flag -> {
								if (exception.get() == null) {
									try {
										database.execute(insertSetflag, block.get("id"), choice.get("id"), flag);
									} catch (Exception e) {
										exception.set(e);
										logger.error(e);
									}
								}
							});
						}
					} catch (Exception e) {
						exception.set(e);
						logger.error(e);
					}
				});

			
			} catch (Exception e) {
				exception.set(e);
				logger.error(e);
			}

		});
		if (exception.get() == null) {
			runs.forEach(run -> {
				try {
					if (exception.get() == null) {
						database.execute(insertRun, run.get("id"), run.get("title"), run.get("submission"), run.get("juryPool"), run.get("startNode"));
						
						List<Map<String, Object>> nodes = (List<Map<String, Object>>) run.get("nodes");
						nodes.forEach(node -> {
							if (exception.get() == null) {
								try {
									database.execute(insertNode, run.get("id"), node.get("id"), node.get("template"));
									
									Map<String, String> nexts = (Map<String, String>) node.get("next");
									nexts.forEach((key, value) -> {
										if (exception.get() == null) {
											try {
												database.execute(insertNext, run.get("id"), node.get("id"), key, value);
											} catch (Exception e) {
												exception.set(e);
												logger.error(e);
											}
										}
									});
								} catch (Exception e) {
									exception.set(e);
									logger.error(e);
								}
							}
						});
					}
				} catch (Exception e) {
					exception.set(e);
					logger.error(e);
				}
				
			});
		}
		if (exception.get() != null) throw exception.get();
		
		return tables;
    }
}
