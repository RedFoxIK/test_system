package ua.test.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
