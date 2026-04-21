# TP5 JEE - Gestion des Produits

## 📌 Description
Application web JEE permettant la gestion des produits et des utilisateurs avec authentification et gestion des rôles (ADMIN / USER).

## 🧱 Technologies utilisées
- Java EE (Servlets, JSP)
- Hibernate (Annotations)
- MySQL
- Maven
- Apache Tomcat

## 🔐 Fonctionnalités

### 👤 Authentification
- Login / Logout
- Inscription utilisateur

### 👥 Gestion des utilisateurs
- Création d’utilisateurs (ADMIN peut créer USER ou ADMIN)
- Vérification des doublons

### 📦 Gestion des produits (ADMIN uniquement)
- Ajouter un produit
- Modifier un produit
- Supprimer un produit
- Afficher la liste

### 🔒 Sécurité
- Filtre AuthFilter → accès sécurisé
- Filtre AdminFilter → accès réservé ADMIN

---

## 🧠 Architecture MVC2

- **Model** : Entités Hibernate (User, Role, Produit)
- **DAO** : Accès base de données
- **Service** : Logique métier
- **Controller** : ActionServlet
- **View** : JSP

---

## 🗃️ Base de données

- `users`
- `roles`
- `produits`

---

## 🌿 Branches Git

- `main` → version principale
- `xml-version` → version Hibernate XML
- `annotations-version` → version finale avec annotations ✅

---

## 🚀 Auteur
Nada El Khamlichi
