package systems.whitestar.welcome;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import systems.whitestar.welcome.DB.Controller;
import systems.whitestar.welcome.DB.UserPermissions;
import systems.whitestar.welcome.Models.UserPermission;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Log4j
public class Init implements ServletContextListener {
    private static final String SECRET_DEFAULTS_FILE = "secret_defaults.properties";
    private static final String USER_PERMISSIONS_DEFUALTS_FILE = "default_user_permissions.json";

    @Override public void contextInitialized(ServletContextEvent sce) {
        try {
            loadSecretDefaults();
        } catch (IOException e) {
            log.error("Could not load Default Properties File");
        } catch (RuntimeException e) {
            log.error("Problem Loading Defaults to Vault", e);
        }

        Controller.setup();

        loadUserPermissionDefaults();
    }


    @Override public void contextDestroyed(ServletContextEvent sce) {
        Controller.shutdown();
    }

    private void loadSecretDefaults() throws IOException {
        final Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(SECRET_DEFAULTS_FILE));

        for (String property : properties.stringPropertyNames()) {
            String propertyValue = null;

            try {
                log.debug("Getting value of property - " + property);
                propertyValue = Secret.getInstance().getSecret(property);
                log.debug("Value of property " + property + " is - " + propertyValue);
            } catch (RuntimeException e) {
                log.warn("Problem getting value of property - " + property, e);
            }

            if (propertyValue == null) {
                log.info("Value for property " + property + " is not set. Setting to default value.");
                log.debug("Default value for property " + property + " is \"" + properties.getProperty(property) + "\"");

                Map<String, String> secrets;
                try {
                    secrets = Secret.getInstance().getApplication();
                } catch (RuntimeException e1) {
                    log.warn("Application does not yet exist. Creating.");
                    secrets = new TreeMap<>();
                }

                secrets.put(property, properties.getProperty(property));

                final TreeMap<String, Object> map = new TreeMap<>(secrets);
                Secret.getInstance().setSecret(map);
            }
        }
    }

    private void loadUserPermissionDefaults() {
        UserPermission[] permissions = new Gson().fromJson(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(USER_PERMISSIONS_DEFUALTS_FILE)),
                UserPermission[].class
        );
        log.info(String.format("Loaded %d permissions", permissions.length));
        log.debug(Arrays.toString(permissions));
        UserPermissions.loadDefaults(permissions);
    }
}
