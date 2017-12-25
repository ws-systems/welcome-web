package systems.whitestar.welcome;

import lombok.extern.log4j.Log4j;
import org.jtwig.web.servlet.JtwigRenderer;
import systems.whitestar.welcome.DB.Sites;
import systems.whitestar.welcome.Models.Site;

import javax.persistence.NoResultException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Tom Paulus
 * Created on 12/24/17.
 */
@Log4j
public class SiteFilter implements Filter {
    private static final String ROOT_SUBDOMAIN = "welcome";
    private static final String NO_SUBDOMAIN_TEMPLATE = "/templates/Landing/no_sub.twig";

    private final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();

    @SuppressWarnings("RedundantThrows")
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Intentionally Blank
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Servlet Path = " + ((HttpServletRequest) request).getServletPath());

        // Assets should be served regardless of if the site exists
        if (((HttpServletRequest) request).getServletPath().equals("/assets")) {
            chain.doFilter(request, response);
            return;
        }

        if (((HttpServletRequest) request).getSession(false) == null ||
                ((HttpServletRequest) request).getSession().getAttribute("site") == null) {
            String[] url = ((HttpServletRequest) request).getRequestURL().toString().replaceFirst("https?://", "").split("\\.");
            log.debug("Received Request at URL - " + Arrays.toString(url));

            if (url[0].toLowerCase().equals(ROOT_SUBDOMAIN))
                // Landing Page for Welcome
                log.debug("Requested Landing Page");
            else {
                try {
                    Site site = Sites.getSiteFromSubdomain(url[0]);
                    log.debug("Requested Site - " + site.getDisplayName());
                    ((HttpServletRequest) request).getSession(true).setAttribute("site", site);
                } catch (NoResultException e) {
                    log.debug("No site exists at subdomain - " + url[0]);

                    ((HttpServletResponse) response).setHeader("Content-Type", MediaType.TEXT_HTML);

                    renderer.dispatcherFor(NO_SUBDOMAIN_TEMPLATE)
                            .render(((HttpServletRequest) request), ((HttpServletResponse) response));

                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Intentionally Blank
    }
}
