<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="page">
        <div class="card form-card">
            <h2>Ajouter un utilisateur</h2>
            <p class="subtitle">L'administrateur peut créer un USER ou un ADMIN.</p>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="message message-error"><%= error %></div>
            <%
                }
            %>

            <form action="action" method="post">
                <input type="hidden" name="action" value="addUser">

                <div class="form-group">
                    <label>Nom d'utilisateur</label>
                    <input type="text" name="username" required>
                </div>

                <div class="form-group">
                    <label>Mot de passe</label>
                    <input type="password" name="password" required>
                </div>

                <div class="form-group">
                    <label>Rôle</label>
                    <select name="roleName" required>
                        <option value="USER">USER</option>
                        <option value="ADMIN">ADMIN</option>
                    </select>
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