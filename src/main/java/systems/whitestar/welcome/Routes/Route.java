package systems.whitestar.welcome.Routes;

import lombok.extern.log4j.Log4j;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import systems.whitestar.welcome.DB.Users;
import systems.whitestar.welcome.Meta;
import systems.whitestar.welcome.Models.User;
import systems.whitestar.welcome.Secret;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Tom Paulus
 * Created on 12/23/17.
 */
@Log4j
public class Route {
    private static final String SSO_UPDATE_INFO_URL = Secret.getInstance().getSecret("sso.update-info-URL");

    /**
     * Add Monitor Dashboard Build Information
     *
     * @param request {@link HttpServletRequest} Request
     */
    static void addMeta(HttpServletRequest request) {
        if (Meta.buildInfo != null) {
            request.setAttribute("app_version", Meta.buildInfo.getVersion());
            request.setAttribute("app_build", Meta.buildInfo.getBuild());
            request.setAttribute("app_build_time", Meta.buildInfo.getTime());
        }

        request.setAttribute("hide_issue_link", Secret.getInstance().getSecret("issues.hideLink"));
        request.setAttribute("issue_link", Secret.getInstance().getSecret("issues.link"));
    }

    /**
     * Set User Attributes in Request
     *
     * @param request {@link HttpServletRequest} Request
     */
    static void setUserData(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            ProfileManager manager = new ProfileManager(new J2EContext(request, null));
            //noinspection unchecked
            Optional<CommonProfile> profile = manager.get(true);
            if (profile.isPresent()) {
                User user;

                try {
                    User existingUser = Users.getByExternalId(profile.get().getId());

                    // Incomplete Session
                    // Update User with SSO Provider Information
                    user = User.builder()
                            .name((String) profile.get().getAttribute("name"))
                            .email(profile.get().getEmail())
                            .externalId(profile.get().getId())
                            .build();

                    existingUser = User.merge(existingUser, user);
                    Users.update(existingUser);
                    request.setAttribute("first_login", false);
                } catch (NoResultException e) {
                    log.debug("User does not yet exist", e);
                    // Need to Create User
                    log.info(String.format("User with email %s does not yet exist - creating", profile.get().getEmail()));
                    user = User.builder()
                            .name((String) profile.get().getAttribute("name"))
                            .email(profile.get().getEmail())
                            .externalId(profile.get().getId())
                            .build();
                    Users.update(user);
                    request.setAttribute("first_login", true);
                }

                request.setAttribute("user", user);
            }
        } else {
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("user", user);
            request.setAttribute("first_login", false);
        }

        request.setAttribute("sso_update_info_url", SSO_UPDATE_INFO_URL);
    }


}
