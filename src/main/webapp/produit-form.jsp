<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un produit</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="page">
        <div class="card form-card">
            <h2>Ajouter un produit</h2>
            <p class="subtitle">Remplissez les informations ci-dessous.</p>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="message message-error"><%= error %></div>
            <%
                }
            %>

            <form action="action" method="post">
                <input type="hidden" name="action" value="addProduit">

                <div class="form-group">
                    <label>Nom</label>
                    <input type="text" name="nom" required>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <input type="text" name="description" required>
                </div>

                <div class="form-group">
                    <label>Prix</label>
                    <input type="number" step="0.01" min="0" name="prix" required>
                </div>

                <div class="actions">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="action?action=listProduits" class="btn btn-secondary">Retour</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>