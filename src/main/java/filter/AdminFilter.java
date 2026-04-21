package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String action = req.getParameter("action");

        boolean adminOnlyAction =
                "showAddProduit".equals(action) ||
                "addProduit".equals(action) ||
                "showEditProduit".equals(action) ||
                "updateProduit".equals(action) ||
                "deleteProduit".equals(action) ||
                "showAddUser".equals(action) ||
                "addUser".equals(action);

        if (!adminOnlyAction) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("role") == null) {
            resp.sendRedirect(req.getContextPath() + "/action?action=showLogin");
            return;
        }

        String role = (String) session.getAttribute("role");

        if (!"ADMIN".equals(role)) {
            resp.sendRedirect(req.getContextPath() + "/action?action=listProduits");
            return;
        }

        chain.doFilter(request, response);
    }
}