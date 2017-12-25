package systems.whitestar.welcome.DB;

import lombok.extern.log4j.Log4j;
import systems.whitestar.welcome.Models.UserPermission;

import javax.persistence.EntityManager;

/**
 * @author Tom Paulus
 * Created on 12/23/17.
 */
@Log4j
public class UserPermissions {
    public static void loadDefaults(final UserPermission[] permissions) {
        EntityManager entityManager = Controller.getNewEntityManager();
        entityManager.getTransaction().begin();
        for (UserPermission permission : permissions) {
            entityManager.persist(entityManager.contains(permission) ? permission : entityManager.merge(permission));
            log.debug(String.format("Persisting permission %s to DB", permission.getKey()));
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        log.debug(String.format("Loaded %d user permissions to DB", permissions.length));
    }
}
