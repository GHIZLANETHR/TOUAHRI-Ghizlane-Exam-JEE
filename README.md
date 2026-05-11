# 🛡️ Gestion des Contrats d'Assurance — API REST Spring Boot

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-HMAC--SHA512-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![H2](https://img.shields.io/badge/H2-In--Memory-1F305F?style=for-the-badge&logo=h2&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

**Projet d'examen JEE — Ghizlane Touahri**

</div>

---

## 📋 Description

Application backend de gestion de contrats d'assurance (**automobile**, **habitation**, **santé**) avec gestion des clients et de leurs paiements.

L'API REST est entièrement sécurisée par **Spring Security + JWT (stateless)**, avec trois niveaux d'accès : `CLIENT`, `EMPLOYE`, `ADMIN`.

L'architecture s'inspire du pattern **e-banking backend** : DTOs, mappers, service métier transactionnel, exceptions personnalisées, audit automatique et documentation Swagger.

---

## ✅ Fonctionnalités

- [x] Couche DAO : entités JPA avec héritage `SINGLE_TABLE` pour les contrats
- [x] DTOs & Mappers pour découpler entités et couche web
- [x] Service métier avec interface et implémentation `@Transactional`
- [x] Contrôleurs REST : clients, contrats, paiements, dashboard
- [x] Sécurité stateless avec **Spring Security + JWT (HMAC-SHA512)**
- [x] Trois rôles : `CLIENT`, `EMPLOYE`, `ADMIN`
- [x] Gestion des erreurs standardisée via `GlobalExceptionHandler`
- [x] Audit automatique (`createdAt`, `updatedAt`, `createdBy`, `updatedBy`)
- [x] Documentation **Swagger UI** (OpenAPI)
- [x] Jeu de données de test initialisé au démarrage
- [x] Base H2 en mémoire (compatible MySQL)

---

## 🧰 Stack technique

| Domaine | Technologie |
|---|---|
| Framework | Spring Boot 3.2.5 |
| Langage | Java 17 |
| Persistance | Spring Data JPA / Hibernate |
| Base de données | H2 (mémoire) — compatible MySQL |
| Sécurité | Spring Security + OAuth2 Resource Server JWT |
| Token JWT | Nimbus JOSE + JWT — HMAC-SHA512 |
| Documentation | Springdoc OpenAPI (Swagger UI) |
| Validation | Bean Validation (`@NotNull`, `@Valid`…) |
| Mapping | BeanUtils / MapStruct manuel |
| Boilerplate | Lombok |

---

## 📁 Structure du projet

```
org.enset.touahrighizlaneexamjee
├── config/                        # DataInitializer, OpenApiConfig
├── dtos/                          # ClientDTO, ContratDTO (abstraite et sous-types), PaiementDTO…
├── entities/                      # BaseEntity, Client, Contrat (SINGLE_TABLE), Paiement, AppUser, AppRole
├── enums/                         # StatutContrat, TypeLogement, NiveauCouverture, TypePaiement
├── exceptions/                    # ClientNotFoundException, ContratNotFoundException…
├── mappers/                       # AssuranceMapperImpl
├── repositories/                  # ClientRepository, ContratRepository, PaiementRepository…
├── security/                      # SecurityConfig, SecurityController, UserDetailServiceImpl, AuditorAwareImpl
├── services/                      # AssuranceService (interface) + AssuranceServiceImpl
├── web/                           # ClientRestController, ContratRestController, PaiementRestController,
│                                  # DashboardRestController, GlobalExceptionHandler
└── TouahriGhizlaneExamJeeApplication.java
```

---

## 🚀 Lancement

### Prérequis

- Java 17+
- Maven 3.8+

### Étapes

```bash
# 1. Cloner le dépôt
git clone https://github.com/votrecompte/TOUAHRI-GHIZLANE-EXAM-JEE.git
cd TOUAHRI-GHIZLANE-EXAM-JEE

# 2. Compiler et démarrer
mvn clean spring-boot:run
```

> Ou importer le projet dans IntelliJ / Eclipse et lancer `TouahriGhizlaneExamJeeApplication`.

### Accès

| Interface | URL |
|---|---|
| Swagger UI | http://localhost:8085/swagger-ui/index.html |
| Console H2 | http://localhost:8085/h2-console |

> Console H2 — JDBC URL : `jdbc:h2:mem:assurancedb` · User : `sa` · Mot de passe : *(vide)*

---

## 👥 Utilisateurs de test

| Login | Mot de passe | Rôles |
|---|---|---|
| `admin` | `1234` | `CLIENT`, `EMPLOYE`, `ADMIN` |
| `employe` | `1234` | `EMPLOYE` |
| `client` | `1234` | `CLIENT` |

> *Authentification configurée avec `NoOpPasswordEncoder` (mots de passe en clair) pour faciliter les tests. Remplacer par BCrypt en production.*

---

## 🔐 Authentification JWT

### Obtenir un token

```bash
curl -X POST "http://localhost:8085/auth/login?username=admin&password=1234"
```

**Réponse :**
```json
{
  "access-token": "eyJhbGciOiJIUzUxMiJ9.eyJzY29wZSI6IkNMSUVOVCBFTVBMT1lFIEFETUlOIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3NDY5NjQwMDB9..."
}
```

### Appeler un endpoint protégé

```bash
curl -H "Authorization: Bearer <votre_token>" http://localhost:8085/api/clients
```

> Dans **Swagger UI**, cliquez sur **Authorize** (en haut à droite) et saisissez `Bearer <votre_token>`.

---

## 📡 Endpoints principaux

### Authentification

| Méthode | URL | Description | Accès |
|---|---|---|---|
| `POST` | `/auth/login` | Obtenir un token JWT | Public |

### Clients

| Méthode | URL | Description | Accès |
|---|---|---|---|
| `GET` | `/api/clients` | Liste des clients | CLIENT / EMPLOYE / ADMIN |
| `GET` | `/api/clients/{id}` | Détail d'un client | CLIENT / EMPLOYE / ADMIN |
| `GET` | `/api/clients/search?nom=…` | Rechercher un client | CLIENT / EMPLOYE / ADMIN |
| `POST` | `/api/clients` | Créer un client | EMPLOYE / ADMIN |
| `PUT` | `/api/clients/{id}` | Modifier un client | EMPLOYE / ADMIN |
| `DELETE` | `/api/clients/{id}` | Supprimer un client | ADMIN |

### Contrats

| Méthode | URL | Description | Accès |
|---|---|---|---|
| `POST` | `/api/contrats/automobile` | Créer un contrat auto | EMPLOYE / ADMIN |
| `POST` | `/api/contrats/habitation` | Créer un contrat habitation | EMPLOYE / ADMIN |
| `POST` | `/api/contrats/sante` | Créer un contrat santé | EMPLOYE / ADMIN |
| `GET` | `/api/contrats` | Liste des contrats | EMPLOYE / ADMIN |
| `GET` | `/api/contrats/{id}` | Détail d'un contrat | CLIENT / EMPLOYE / ADMIN |
| `PATCH` | `/api/contrats/{id}/statut?statut=VALIDE` | Changer le statut | EMPLOYE / ADMIN |
| `GET` | `/api/contrats/search?…` | Rechercher des contrats | EMPLOYE / ADMIN |

### Paiements & Dashboard

| Méthode | URL | Description | Accès |
|---|---|---|---|
| `POST` | `/api/paiements` | Ajouter un paiement | EMPLOYE / ADMIN |
| `GET` | `/api/paiements/contrat/{contratId}` | Paiements d'un contrat | CLIENT / EMPLOYE / ADMIN |
| `GET` | `/api/dashboard/stats` | Statistiques globales | ADMIN |

> Pour la documentation complète, consultez **[Swagger UI](http://localhost:8085/swagger-ui/index.html)** après démarrage.

---

## 🏗️ Choix d'architecture

### Héritage SINGLE_TABLE

Les types de contrats (`ContratAutomobile`, `ContratHabitation`, `ContratSante`) sont stockés dans une seule table `contrat` avec une colonne discriminatrice `TYPE` (`CA` / `CH` / `CS`). Cela simplifie les requêtes polymorphes.

### Audit automatique

Toutes les entités métier héritent de `BaseEntity` :

```java
createdAt  // date de création (automatique)
updatedAt  // date de dernière modification (automatique)
createdBy  // utilisateur connecté (via AuditorAwareImpl)
updatedBy  // utilisateur connecté (via AuditorAwareImpl)
```

### Sécurité stateless

- Chaque requête doit contenir un token JWT valide dans le header `Authorization`.
- Aucune session HTTP n'est maintenue côté serveur.
- CORS configuré pour autoriser `http://localhost:4200` (Angular).
- Endpoints publics : `/auth/login`, Swagger UI, console H2.
- Les rôles sont encodés en `SCOPE_CLIENT`, `SCOPE_EMPLOYE`, `SCOPE_ADMIN` dans le token.

---

## 🔮 Améliorations envisagées

- [ ] Frontend Angular pour consommer l'API
- [ ] Tests unitaires et d'intégration
- [ ] Déploiement avec base MySQL distante
- [ ] Pagination sur les listes longues
- [ ] Codes d'erreur métier enrichis
- [ ] Notifications email à la validation d'un contrat

---

<div align="center">

*Projet réalisé dans le cadre de l'examen JEE — **Ghizlane Touahri***

</div>
