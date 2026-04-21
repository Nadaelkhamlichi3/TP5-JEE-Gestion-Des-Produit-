<%@ page import="model.Produit" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire produit</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="page">
        <div class="card form-card">
            <%
                Produit produit = (Produit) request.getAttribute("produit");
                boolean editMode = (produit != null);
            %>

            <h2><%= editMode ? "Modifier un produit" : "Ajouter un produit" %></h2>
            <p class="subtitle">
                <%= editMode ? "Modifiez les informations du produit." : "Remplissez les informations du produit." %>
            </p>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="message message-error"><%= error %></div>
            <%
                }
            %>

            <form action="action" method="post">
                <input type="hidden" name="action" value="<%= editMode ? "updateProduit" : "addProduit" %>">

                <%
                    if (editMode) {
                %>
                    <input type="hidden" name="idProduit" value="<%= produit.getIdProduit() %>">
                <%
                    }
                %>

                <div class="form-group">
                    <label>Nom</label>
                    <input type="text" name="nom"
                           value="<%= editMode ? produit.getNom() : "" %>" required>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <input type="text" name="description"
                           value="<%= editMode ? produit.getDescription() : "" %>" required>
                </div>

                <div class="form-group">
                    <label>Prix</label>
                    <input type="number" step="0.01" min="0" name="prix"
                           value="<%= editMode ? produit.getPrix() : "" %>" required>
                </div>

                <div class="actions">
                    <button type="submit" class="btn btn-primary">
                        <%= editMode ? "Modifier" : "Enregistrer" %>
                    </button>
                    <a href="action?action=listProduits" class="btn btn-secondary">Retour</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>