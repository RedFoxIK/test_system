package ua.test.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UriFilter implements Filter {
    private final String firstUri = "testing_system/";
    private String validURIs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validURIs = filterConfig.getInitParameter("validURI").replaceAll(" ", "");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String errorURI = "/jsp/404.jsp";
//        if (isValid(request) || errorURI.equals(request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.sendRedirect(errorURI);
//        }
    }

    private boolean isValid(HttpServletRequest request) {
        String[] splicedURIs = validURIs.split("[|]");
        String requestURI = request.getRequestURI();

        for (String s: splicedURIs) {
            if (requestURI.equals(s)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void destroy() {

    }
}
