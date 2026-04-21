package filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/action")
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