/**
 * 
 */
package ru.hmt.vsm.http;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.hmt.vsm.app.BinaryApplication;
import ru.hmt.vsm.model.BinaryData;



/**
 * Сервлет получения ресурса из таблиц модели данных
 * 
 */
public class BinaryResource extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496769408635906578L;
	
	private BinaryApplication app;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String name = config.getInitParameter("binaryAppication");
		if (name == null) name = "binary";
		app = (BinaryApplication) config.getServletContext().getAttribute(name);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info = request.getPathInfo();
		if (info != null) {
			BinaryData binaryData = null;
        	try {
        		binaryData = app.find(info);
        	} catch (Exception e) {
				throw new ServletException(e);
			}
        	if (binaryData == null) {
        		response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	} else {
        		String mimeType = getServletContext().getMimeType(info);
        		response.setContentType(mimeType == null ? "application/octet-stream" : mimeType);
        		//Last-Modified: Mon, 28 Nov 2022 14:09:51 GMT
        		response.setDateHeader("Last-Modified", binaryData.getCreated().getTime());
        		OutputStream out = response.getOutputStream();
        		out.write(binaryData.getData());
        	}
		} else {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}


}
