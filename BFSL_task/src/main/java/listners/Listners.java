package listners;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

import com.msf.log.Logger;
public class Listners {
	private static Logger log;
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitialized");
		ServletContext ctx = event.getServletContext();
		String classFolder = ctx.getRealPath("/") + "WEB-INF" + System.getProperty("file.separator") + "classes"
				+ System.getProperty("file.separator");
		String logFile = classFolder + "Logging.properties";

		try {
			//setLogger(logFile);
			log = Logger.getLogger(Listners.class);
		} catch (Exception e) {
			System.out.println("Not able to set Logger");
		}}}
