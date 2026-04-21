package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String action = req.getParameter("action");

        boolean publicAction =
                action == null ||
                "showLogin".equals(action) ||
                "login".equals(action) ||
                "showRegister".equals(action) ||
                "register".equals(action);

        if (publicAction) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("connectedUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/action?action=showLogin");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}