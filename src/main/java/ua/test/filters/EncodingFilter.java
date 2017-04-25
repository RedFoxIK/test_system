package ua.test.filters;

import org.apache.log4j.Logger;
import ua.test.dao.interfaces.AnswerDao;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        LOGGER.info("I have worked");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
