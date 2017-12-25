package systems.whitestar.welcome.DB;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import systems.whitestar.welcome.Secret;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Log4j
public class Controller {
    @Setter
    private static EntityManagerFactory sessionFactory;

    public static void setup() {
        Properties props = new Properties();
        props.setProperty("javax.persistence.jdbc.url", Secret.getInstance().getSecret("db.url"));
        props.setProperty("javax.persistence.jdbc.user", Secret.getInstance().getSecret("db.user"));
        props.setProperty("javax.persistence.jdbc.password", Secret.getInstance().getSecret("db.password"));

        try {
            sessionFactory = Persistence.createEntityManagerFactory("systems.whitestar.welcome.jpa", props);
            log.info("Session Factory is ready to go!");
        } catch (Exception e) {
            log.fatal("Could not start Session Factory", e);
            throw e;
        }
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            log.warn("Closing Session Factory");
            sessionFactory.close();
        }
    }

    static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }

    static EntityManager getNewEntityManager() {
        return getSessionFactory().createEntityManager();
    }
}
