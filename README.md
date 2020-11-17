# ipi-mdd-050-ex
Cours de SOA, module 050 pour l'IPI

## Client web à télécharger

https://github.com/pjvilloud/ipi-mdd-050-web/releases/download/untagged-2953328f933b89cfe909/ipi-mdd-050-web-v1.2.zip

Le fichier `index.html` contient toutes les questions à faire pour le TP !

Si besoin d'un dump pour la base de données entreprise : 
`/src/main/resources/dumpbdd.sql`



========================================
Pour simplifier : rappel ici de ce qu'il y a à faire (présent dans le index.html)



Bienvenue dans l'interface de gestion des employés !

Cette application web est paramétrée pour communiquer avec une API REST accessible à l'adresse http://localhost:5367.

Il est nécessaire de développer les services webs nécessaires pour que cette application fonctionne. Voici l'ensemble des fonctionnalités :

    1 - Compter le nombre d'employés

    A côté du lien Liste des employés, on doit voir apparaître le nombre d'employés. L'appel qui est effectué est GET /employes/count.
    2 - Afficher un employé

        En cliquant ici, on peut afficher les informations basiques du commercial d'identifiant 6 (matricule C00002). L'appel qui est effectué est GET /employes/6.
        En cliquant ici, on peut afficher les informations basiques du manager d'identifiant 7 (matricule M00003). L'appel qui est effectué est GET /employes/7.
        En cliquant ici, on peut afficher les informations basiques du technicien d'identifiant 9 (matricule T00005). L'appel qui est effectué est GET /employes/9.
        En cliquant ici, on essaye d'afficher l'employé d'identifiant 0 mais on doit obtenir une erreur 404 car il n'existe pas.
    3 - Recherche par matricule

    Lorsqu'on recherche le matricule C00019 dans la barre de recherche, on tombe sur Sarah Renault. L'appel qui est effectué est GET /employes?matricule=C00019. Lorsqu'on recherche un matricule inexistant commme ABCDEF, on obtient une erreur 404.
    4 - Liste des employés

    En cliquant ici, tous les employés sont affichés, de manière paginée. Il est possible de changer de page en utilisant les boutons et de trier les résultats en cliquant sur les différentes colonnes. L'appel effectué est GET /employes?page=0&size=10&sortProperty=matricule&sortDirection=ASC
    5 - Création d'un Commercial

    En cliquant ici ou via le bouton Nouvel employé, Commercial, présent dans la liste des employés, on accède au formulaire de création d'un commercial. L'appel qui est effectué est POST /employes avec les données de l'employé en JSON dans le champ data de la requête. Créer un commercial qui existe déjà (même matricule) lance une erreur 409.
    6 - Modification d'un Commercial

    En cliquant ici ou en consultant les détails du commercial de matricule C00002 (id 8), il est possible de modifier les informations du commercial d'identifiant 8 qui sont persistées en base de donnée lorsqu'on clique sur le bouton Enregistrer. L'appel qui est effectué est PUT /employes/8 avec les données de l'employé en JSON dans le champ data de la requête.
    7 - Suppression d'un Commercial

    En cliquant ici ou en consultant les détails du commercial de matricule C00018 (id 22), il est possible de supprimer ce dernier lorsqu'on clique sur le bouton Supprimer. L'appel qui est effectué est DELETE /employes/22.
    8 - Création, modification et suppression d'un Technicien

    Vérifier que ces opérations fonctionnent avec un technicien.
    9 - Création, modification et suppression d'un Manager

    Vérifier que ces opérations fonctionnent avec un manager.
    10 - Ajouter ou supprimer un technicien dans l'équipe d'un manager

    En cliquant ici ou en consultant le détail du manager M00528 (id 532), il est possible de supprimer (Appel API GET /managers/532/equipe/576/remove) un membre de son équipe avec le bouton et d'ajouter (Appel API GET /managers/532/equipe/T00110/add) un membre à l'équipe en renseignant son matricule (dans l'exemple T00110) et en cliquant sur le bouton .
    11 - Ajouter ou supprimer un manager à un technicien

    En cliquant ici ou en consultant le détail du technicien T00572 (id 576), il est possible de supprimer (Appel API déjà développé dans la modification du technicien) un membre de son équipe avec le bouton et d'ajouter (Appel API GET /techniciens/576/equipe/M00528/add) un membre à l'équipe en renseignant son matricule (dans l'exemple M00528) et en cliquant sur le bouton .

