package systems.whitestar.welcome.DB;

import systems.whitestar.welcome.Models.Site;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author Tom Paulus
 * Created on 12/23/17.
 */
public class Sites {
    public static Site getSiteFromSubdomain(final String subdomain) throws NoResultException {
        EntityManager entityManager = Controller.getNewEntityManager();
        entityManager.getTransaction().begin();

        Site site = (Site) entityManager
                .createQuery("select s from Site s where s.subdomain = '" + subdomain + "'")
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return site;
    }
}
