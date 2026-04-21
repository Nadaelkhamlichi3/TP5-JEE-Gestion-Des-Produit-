package web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Produit;
import model.User;
import service.ProduitService;
import service.ProduitServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.DataInitializer;

public class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProduitService produitService = new ProduitServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public void init() throws ServletException {
        DataInitializer.initializeRoles();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.trim().isEmpty()) {
            action = "showLogin";
        }

        switch (action) {

            case "showLogin":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;

            case "showRegister":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;

            case "register":
                register(request, response);
                break;

            case "login":
                login(request, response);
                break;

            case "logout":
                logout(request, response);
                break;

            case "home":
                response.sendRedirect(request.getContextPath() + "/action?action=listProduits");
                break;

            case "listProduits":
                listProduits(request, response);
                break;

            case "showAddProduit":
                request.getRequestDispatcher("produit-form.jsp").forward(request, response);
                break;

            case "addProduit":
                addProduit(request, response);
                break;

            case "showEditProduit":
                showEditProduit(request, response);
                break;

            case "updateProduit":
                updateProduit(request, response);
                break;

            case "deleteProduit":
                deleteProduit(request, response);
                break;

            case "showAddUser":
                request.getRequestDispatcher("user-form.jsp").forward(request, response);
                break;

            case "addUser":
                addUser(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/action?action=showLogin");
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        boolean created = userService.registerUser(username.trim(), password.trim(), "USER");

        if (created) {
            response.sendRedirect(request.getContextPath() + "/action?action=showLogin");
        } else {
            request.setAttribute("error", "Nom d'utilisateur déjà utilisé.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Veuillez remplir tous les champs.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = userService.login(username.trim(), password.trim());

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("connectedUser", user);
            session.setAttribute("role", user.getRole().getName());

            response.sendRedirect(request.getContextPath() + "/action?action=listProduits");
        } else {
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/action?action=showLogin");
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Produit> produits = produitService.getAllProduits();
        request.setAttribute("produits", produits);

        String success = request.getParameter("success");
        if (success != null && !success.trim().isEmpty()) {
            request.setAttribute("success", success);
        }

        request.getRequestDispatcher("produits.jsp").forward(request, response);
    }

    private void addProduit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String prixStr = request.getParameter("prix");

        if (nom == null || nom.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || prixStr == null || prixStr.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("produit-form.jsp").forward(request, response);
            return;
        }

        double prix;
        try {
            prix = Double.parseDouble(prixStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Le prix doit être un nombre valide.");
            request.getRequestDispatcher("produit-form.jsp").forward(request, response);
            return;
        }

        Produit produit = new Produit();
        produit.setNom(nom.trim());
        produit.setDescription(description.trim());
        produit.setPrix(prix);

        produitService.saveProduit(produit);

        String message = URLEncoder.encode("Produit ajouté avec succès.", StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/action?action=listProduits&success=" + message);
    }

    private void showEditProduit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Produit produit = produitService.getProduitById(id);

        request.setAttribute("produit", produit);
        request.getRequestDispatcher("produit-form.jsp").forward(request, response);
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Long id = Long.parseLong(request.getParameter("idProduit"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String prixStr = request.getParameter("prix");

        if (nom == null || nom.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || prixStr == null || prixStr.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("produit-form.jsp").forward(request, response);
            return;
        }

        double prix;
        try {
            prix = Double.parseDouble(prixStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Le prix doit être un nombre valide.");
            request.getRequestDispatcher("produit-form.jsp").forward(request, response);
            return;
        }

        Produit produit = produitService.getProduitById(id);
        produit.setNom(nom.trim());
        produit.setDescription(description.trim());
        produit.setPrix(prix);

        produitService.updateProduit(produit);

        String message = URLEncoder.encode("Produit modifié avec succès.", StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/action?action=listProduits&success=" + message);
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        produitService.deleteProduit(id);

        String message = URLEncoder.encode("Produit supprimé avec succès.", StandardCharsets.UTF_8.name());
        response.sendRedirect(request.getContextPath() + "/action?action=listProduits&success=" + message);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleName = request.getParameter("roleName");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || roleName == null || roleName.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("user-form.jsp").forward(request, response);
            return;
        }

        boolean created = userService.registerUser(username.trim(), password.trim(), roleName.trim());

        if (created) {
            String message = URLEncoder.encode("Utilisateur ajouté avec succès.", StandardCharsets.UTF_8.name());
            response.sendRedirect(request.getContextPath() + "/action?action=listProduits&success=" + message);
        } else {
            request.setAttribute("error", "Nom d'utilisateur déjà utilisé.");
            request.getRequestDispatcher("user-form.jsp").forward(request, response);
        }
    }
}