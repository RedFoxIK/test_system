package ua.test.filters;

import org.apache.log4j.Logger;
import ua.test.dao.interfaces.AnswerDao;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encodingType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingType = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encodingType);
        response.setCharacterEncoding(encodingType);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
