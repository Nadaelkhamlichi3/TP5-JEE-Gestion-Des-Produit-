<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="card auth-card">
            <h2>Créer un compte</h2>
            <p class="subtitle">Le compte créé aura le rôle USER.</p>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="message message-error"><%= error %></div>
            <%
                }
            %>

            <form action="action" method="post">
                <input type="hidden" name="action" value="register">

                <div class="form-group">
                    <label>Nom d'utilisateur</label>
                    <input type="text" name="username" required>
                </div>

                <div class="form-group">
                    <label>Mot de passe</label>
                    <input type="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary">S'inscrire</button>
                <a href="action?action=showLogin" class="btn btn-secondary">Retour</a>
            </form>
        </div>
    </div>
</body>
</html>