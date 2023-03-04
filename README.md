# Accar Areno

L'application web permettant de gérer vos concerts.

Elle permet de voir les concerts prévus, les soirées, les salles, les groupes et leurs artistes, et de réserver des billets sur son compte.

## Présentation de l'équipe

- Professeur : Éric **Cariou**
- Professeur : Lévy **Marques**
- Développeurse : Alexia **Sorin**
- Développeur : Abderraouf **Chebbah**
- Développeur : Christophe **Garcia**

## Ports utilisés

- **8079** : Serveur Tomcat pour JPA Servlets
- **8080** : Serveur Tomcat pour Spring
- **8081** : VueJs

## Lancement

Pour pouvoir utiliser l'application, il faut : 
- Avoir une base de données (locale ou non) remplie : exécuter le script de création de tables (doc/values.sql) et d'insertion des valeurs (doc/zfm1-zgarciach_1.sql)
- Lancer les servlets : ouvrir le projet servlet dans Eclipse et lancer le projet en tant que serveur à l'aide de Tomcat (tools/apache-tomcat-10.0.27)
- Lancer Spring : ouvrir le projet spring dans intelliJ et lancer spring/src/main/java/com/App.js
- Lancer VueJs : ouvrir un terminal, se déplacer dans vuejs, et lancer le script serve (`npm run serve`)

Dorénavent, l'application est censée être démarrée et complétement utilisable.

### Troubleshooting

Cependant, si vous rencontrez des erreurs lors du lancement, voici peut-être la solution :

- Problème avec Tomcat pour le lancement des servlets : installer localement [Tomcat 10.0.27](https://archive.apache.org/dist/tomcat/tomcat-10/v10.0.27/bin/). Mettre à jour Maven (clic droit sur le projet > Maven > Update Maven). Relancer le serveur (clic droit sur le projet > Run as server > Choisir Tomcat 10.0 > Choisir le dossier Tomcat nouvellement dézippé)
- Problème avec la base de données : Récupérer les scripts de création de tables et d'insertion des valeurs (doc/*.sql). Installer et exécuter Wamp (permettant de lancer un serveur lcoalhost). Ouvrir PhpMyAdmin (login : 'root', mdp : ''). Exécuter respectivement les scripts sql. Relancer l'ensemble des projets.

## Informations complémentaires

Tout le back est fonctionnel : récupération/ajout/modification/suppression des billets, utilisateurs, concerts, soirées, salles, artistes, groupes (MySQL) et ressources (MongoDB). Les contraintes ont été appliquées (ex : 2 concerts ne peuvent pas avoir lieu en même temps dans une même salle).

Le front implémente le CRUD de soirées, salles, concerts, artistes, groupes, utilisateurs et ressources. Il manque billets (mais affichage OK). Les pages HTML et composants Vue ne communiquent qu’avec les serveurs Web, aucun accès direct à une BDD (SQL ou MongoDB) n’est réalisé.

L’application gère un système de connexion : elle s’utilise sans connexion, avec un compte utilisateur ou un compte administrateur. Les fonctionnalités sont adaptées. 
