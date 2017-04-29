package ua.test.filters;

import org.apache.log4j.Logger;
import ua.test.entity.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentInTestFilter implements Filter {
    private static final String START_TEST_URI = "/testing_system/student/start_test";
    private static final String TEST_URI = "/testing_system/student/test";
    private static final String END_TEST_URI = "/testing_system/student/test_end";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
//        String uri = ((HttpServletRequest) request).getRequestURI();
//
//        System.out.println(uri.equals(TEST_URI));
//
//        if ( httpSession != null && httpSession.getAttribute("userTest") != null ) {
//            if ( !( uri.equals(START_TEST_URI) || uri.equals(END_TEST_URI) || uri.equals(TEST_URI) ) ) {
//                ((HttpServletResponse) response).sendRedirect("/testing_system/student/test");
//                return;
//            }
//        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
