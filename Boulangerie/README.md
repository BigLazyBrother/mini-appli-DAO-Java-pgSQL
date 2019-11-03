# projet-integrite-2019-BigLazyBrother
projet-integrite-2019-BigLazyBrother created by GitHub Classroom

Dossier du projet.

Pour fonctionner sans modification du code, ce programme a besoin :
  → d'un compte d'accès à la base de données :
    +––––––––––––+––––––––––––––––––––+
    |  USERNAME  |      PASSWORD      |
    +––––––––––––+––––––––––––––––––––+
    | userLambda | datenbankschlussel |
    +––––––––––––+––––––––––––––––––––+
  → d'une base de donnée :
    boulangerie
    → contenant deux tables :
      patisserie
      → contenant les colonnes :
        +–––––––––+––––––––+–––––––––––––+
        |FIELDNAME|DATATYPE|SPECIFICITIES|
        +–––––––––+––––––––+–––––––––––––+
        | id      | serial | Primary key |
        | nom     | text   |             |
        | couleur | text   |             |
        | poids   | text   |             |
        | prix    | text   |             |
        +–––––––––+––––––––+–––––––––––––+
      patissier
      → contenant les colonnes :
        +–––––––––––––––+––––––––+–––––––––––––––––+
        |   FIELDNAME   |DATATYPE|  SPECIFICITIES  |
        +–––––––––––––––+––––––––+–––––––––––––––––+
        | id            | serial |   Primary key   |
        | nom           |  text  |                 |
        | courriel      |  text  |                 |
        | age           |  text  |                 |
        | ville         |  text  |                 |
        | id_patisserie |integer |FK→patisserie(id)|
        +–––––––––––––––+––––––––+–––––––––––––––––+