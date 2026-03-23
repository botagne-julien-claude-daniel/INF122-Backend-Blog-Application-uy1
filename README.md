# TAF 1 : Développement Backend avec Spring Boot (INF222)

Ce projet consiste en la création d'une API REST pour la gestion d'un blog, réalisée dans le cadre de l'UE INF222 à l'Université de Yaoundé I. 

## 📋 Présentation
L'application permet de gérer des articles de blog (CRUD) et inclut une fonctionnalité de recherche par mot-clé. Elle a été conçue suite à un parcours d'apprentissage personnalisé sur la plateforme **CleeRoute**.

## 🛠 Technologies utilisées
- **Java 17**
- **Spring Boot 3.2.0** (Spring Web, Spring Data JPA)
- **H2 Database** (Base de données en mémoire)
- **SpringDoc OpenAPI (Swagger UI)** pour la documentation

## 🚀 Installation et Lancement
Depuis le terminal (Ubuntu/Windows) :
1. Cloner le dépôt : `git clone [URL_DU_DEPOT]`
2. Lancer l'application : `./mvnw spring-boot:run`

## 🔗 Points d'accès (Endpoints)
L'API est accessible sur `http://localhost:8080/api/articles` :
- **GET** `/api/articles` : Liste tous les articles.
- **POST** `/api/articles` : Ajouter un nouvel article.
- **GET** `/api/articles/{id}` : Récupérer un article par son ID.
- **PUT** `/api/articles/{id}` : Modifier un article.
- **DELETE** `/api/articles/{id}` : Supprimer un article.
- **GET** `/api/articles/search?query=...` : Rechercher dans le titre ou le contenu.

## 📖 Documentation API
La documentation interactive (Swagger UI) est disponible à l'adresse suivante une fois le serveur lancé :
`http://localhost:8080/swagger-ui/index.html`

