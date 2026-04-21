<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

    <div class="page">
        <%
            String role = (String) session.getAttribute("role");
            User user = (User) session.getAttribute("connectedUser");
            String success = (String) request.getAttribute("success");
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
        %>

        <div class="topbar">
            <div>
                <h2>Tableau des produits</h2>
                <div class="user-badge">
                    Connecté en tant que <strong><%= user.getUsername() %></strong> - rôle : <strong><%= role %></strong>
                </div>
            </div>

            <div class="actions">
                <%
                    if ("ADMIN".equals(role)) {
                %>
                    <a href="action?action=showAddProduit" class="btn btn-primary">+ Ajouter un produit</a>
                    <a href="action?action=showAddUser" class="btn btn-secondary">+ Ajouter un utilisateur</a>
                <%
                    }
                %>

                <a href="action?action=logout" class="btn btn-secondary">Déconnexion</a>
            </div>
        </div>

        <%
            if (success != null && !success.trim().isEmpty()) {
        %>
            <div class="message message-success"><%= success %></div>
        <%
            }
        %>

        <div class="card table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <%
                            if ("ADMIN".equals(role)) {
                        %>
                            <th>Actions</th>
                        <%
                            }
                        %>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (produits != null && !produits.isEmpty()) {
                            for (Produit p : produits) {
                    %>
                        <tr>
                            <td><%= p.getIdProduit() %></td>
                            <td><%= p.getNom() %></td>
                            <td><%= p.getDescription() %></td>
                            <td><%= String.format("%.2f DH", p.getPrix()) %></td>

                            <%
                                if ("ADMIN".equals(role)) {
                            %>
                                <td>
                                    <a href="action?action=showEditProduit&id=<%= p.getIdProduit() %>">Modifier</a>
                                    |
                                    <a href="action?action=deleteProduit&id=<%= p.getIdProduit() %>"
                                       onclick="return confirm('Supprimer ce produit ?');">Supprimer</a>
                                </td>
                            <%
                                }
                            %>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="<%= "ADMIN".equals(role) ? 5 : 4 %>" class="empty-row">Aucun produit disponible.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>