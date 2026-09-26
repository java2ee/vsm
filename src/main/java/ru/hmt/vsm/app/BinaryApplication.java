/**
 * 
 */
package ru.hmt.vsm.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
	

	@Override
	public void init() {
		prefixScenarioIcon = getParameterValue("prefixScenarioIcon", prefixScenarioIcon);
		updateScenarioIcon = getParameterValue("updateScenarioIcon", updateScenarioIcon);
		selectScenarioIcon = getParameterValue("selectScenarioIcon", selectScenarioIcon);

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
}
