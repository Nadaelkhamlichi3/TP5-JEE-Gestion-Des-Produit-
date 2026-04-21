<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="card auth-card">
            <h2>Connexion</h2>
            <p class="subtitle">Connectez-vous pour accéder à la liste des produits.</p>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="message message-error"><%= error %></div>
            <%
                }
            %>

            <form action="action" method="post">
                <input type="hidden" name="action" value="login">

                <div class="form-group">
                    <label>Nom d'utilisateur</label>
                    <input type="text" name="username" required>
                </div>

                <div class="form-group">
                    <label>Mot de passe</label>
                    <input type="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary">Se connecter</button>
            </form>

            <p style="margin-top: 18px;">
                Pas de compte ?
                <a href="action?action=showRegister">Créer un compte</a>
            </p>
        </div>
    </div>
</body>
</html>