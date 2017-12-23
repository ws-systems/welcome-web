package systems.whitestar.welcome;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
public class Init implements ServletContextListener {
    @Override public void contextInitialized(ServletContextEvent sce) {
        DB.setup();
    }

    @Override public void contextDestroyed(ServletContextEvent sce) {
        DB.shutdown();
    }
}
