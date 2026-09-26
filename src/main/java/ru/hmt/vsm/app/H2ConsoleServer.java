/**
 * 
 */
package ru.hmt.vsm.app;

import java.sql.SQLException;

import org.h2.tools.Server;

import ru.funsys.avalanche.Application;
import ru.funsys.avalanche.annotation.CfgAttribute;

/**
 * 
 */
public class H2ConsoleServer extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3165057680893129975L;
	
	@CfgAttribute("Порт WEB консоли управления СУБД H2")
	private String port = "8082";
	
	@CfgAttribute("Включает режим совместимости с PostgeSQL")
	private boolean postgres;
	
	@CfgAttribute("Имя переменная домашнего каталога WEB сервера")
	private String base = "catalina.base";
	
	@CfgAttribute("Каталог конфигурационных файлов WEB сервера")
	private String conf = "/conf";

	private Server webServer;
	
	@Override
	public void init() {
        try {
        	String baseDir = System.getProperty(base);
        	System.setProperty("h2.baseDir", baseDir);
        	String userHome = System.getProperty("user.home");
        	System.setProperty("user.home", baseDir + conf);
            // Создаем и запускаем веб-сервер H2
            webServer = Server.createWebServer("-web", "-webPort", port).start();
            System.out.println("Консоль H2 запущена: " + webServer.getURL());
        	System.setProperty("user.home", userHome);
        } catch (SQLException e) {
            System.err.println("Не удалось запустить сервер H2: " + e.getMessage());
        }
	}

	@Override
	public void done() {
        // Проверяем, что сервер существует и запущен перед остановкой
        if (webServer != null && webServer.isRunning(false)) {
            webServer.stop();
            System.out.println("Консоль H2 успешно остановлена.");
        }
	}

}
