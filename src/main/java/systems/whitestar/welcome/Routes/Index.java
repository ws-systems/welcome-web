package systems.whitestar.welcome.Routes;

import org.jtwig.web.servlet.JtwigRenderer;
import systems.whitestar.welcome.Models.Site;
import systems.whitestar.welcome.Models.SiteAsset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tom Paulus
 * Created on 12/23/17.
 */
public class Index extends HttpServlet {
    private static final String DEFAULT_TEMPLATE = "/templates/Landing/landing.twig";
    private final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null && req.getSession().getAttribute("site") != null) {
            Site site = (Site) req.getSession().getAttribute("site");
            SiteAsset assets = site.getAssets();
            // TODO Render the correct Site Content or QC content if necessary
            // TODO Pull Template Pack from S3.

        } else {
            // Using Default Landing Template
            String url = req.getRequestURL().toString().replaceFirst("https?://", "").replace("/", "");
            req.setAttribute("domain_stem", url);
        }

        renderer.dispatcherFor(DEFAULT_TEMPLATE)
                .render(req, resp);
    }
}
