# mini-appli-DAO-Java-pgSQL

Mini CRUD écrit en Java avec interface graphique simpliste pour tester la programmation de DAO

27 août 2019 – Création du repository **projet-integrite-2019-BigLazyBrother** sur GitHub Classroom

30 août 2019 – Mise en place d'un DAO pour les patissiers

3 sept. 2019 – Tests en FXML pour soigner l'interface graphique

4 sept. 2019 – Ajout de la page d'ajout de patissiers, n'utilise cependant pas de fxml. Gestion de l'ajout des patissiers.

6 sept. 2019 – Ajout de la page de modification de patissier, gestion de la modification des patissiers.

10 sept. 2019 – Ajout du script SQL permettant l'ajout de procédures stockées.

Pour fonctionner sans modification du code, ce programme a besoin :

* d'un compte d'accès à la base de données :

| USERNAME   | PASSWORD           |
| ---------- | ------------------ |
| userLambda | datenbankschlussel |

* d'une base de donnée :
      boulangerie
  *  contenant deux tables :
          patisserie
    *  contenant les colonnes :

| FIELDNAME | DATATYPE | SPECIFICITIES |
| --------- | -------- | ------------- |
| id        | serial   | Primary key   |
| nom       | text     |               |
| couleur   | text     |               |
| poids     | text     |               |
| prix      | text     |               |

          et patissier
    *  contenant les colonnes :

| FIELDNAME     | DATATYPE | SPECIFICITIES     |
| ------------- | -------- | ----------------- |
| id            | serial   | Primary key       |
| nom           | text     |                   |
| courriel      | text     |                   |
| age           | text     |                   |
| ville         | text     |                   |
| id_patisserie | integer  | FK→patisserie(id) |

​       