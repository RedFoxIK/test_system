package ua.test.filters;

import org.apache.log4j.Logger;
import ua.test.entity.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentInTestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession(false);

        if ( httpSession != null && httpSession.getAttribute("userTest") != null ) {
            ((HttpServletResponse) response).sendRedirect("/testing_system/student/test");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
