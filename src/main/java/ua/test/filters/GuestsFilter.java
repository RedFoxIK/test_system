package ua.test.filters;

import org.apache.log4j.Logger;
import ua.test.constants.Links;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GuestsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession(false);

        if ( httpSession == null || httpSession.getAttribute("idUser") == null ) {
            ((HttpServletResponse) response).sendRedirect(Links.HOME_PAGE);
        } else {
                filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
