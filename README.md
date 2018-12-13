Bienvenue sur UBeer !
UBeer est une application de recherche de lieux d’intérêts, qui s’appuie sur plusieurs API de Google.

C’est un projet étudiant réalisé en cours.

Dans la première activité (SetupActivity), il possible de remplir des adresses (et les noms en option). Il est possible de rajouter ou d’enlever des lignes d’adresses, en appuyant sur les boutons  +  et  -. Le bouton « exemple » rempli automatiquement un exemple d’adresses.

Au click sur le bouton de recherche, la classe de gestion « getCoordinatesService » est appelée. En premier lieu, un appel vers l’API « geocodes » de Google est effectué pour chaque addresse renseignée, afin de déterminer les coordonnées géographiques de ces adresses.
Ensuite, un calcul est effectué pour trouver le point central entre tous ces lieux.

Finalement, un dernier appel, vers une seconde API « places » de google est effectué pour trouver une liste de bars dans les environs des coordonnées centrales trouvées. Le résultat est au format Json, que nous transformons en objets  « Bars ».
Si des bars ont été trouvés, ceci lance une seconde activité « ResultsActivity », qui affiche ces résultats dans une RecyclerView. On y retrouve le nom, la note et une indication si le bar est ouvert actuellement.

En cliquant sur un des bars trouvés, une troisième activité « BarDetailActivity » s’ouvre, avec des détails en plus, l’adresse et une image s’il y en a une de disponible pour le bar en question.
Nous avons rajouté une rapide animation sur la note du bar, au passage entre ces deux activités.

Dans cette activité, il y a également la possibilité d’ajouter le bar en question à sa liste de favoris. Nous utilisons une base SQLite afin de stocker la liste des bar favoris. Si le bar est déjà en base, il ne se rajoutera pas une seconde fois.
« MaBaseSQLlite » permet d'instancier la base de données. « BarBDD » permet de manipuler les données de la base de données (select, ajout, suppression ). Les données sont sauvegardées en local sur le téléphone ce qui permet de garder les données lors de la fermeture de l'application.

Afin d’accéder à sa liste de favoris, il faut cliquer sur l’étoile du menu (en haut). Ceci ouvre une autre activité, similaire à celle des résultats, avec un RecyclerView pour afficher ces favoris. Il est ici aussi possible d’ouvrir une nouvelle activité au click sur un des bars avec le détail sur celui-ci.

Si l’on clique sur le deuxième item du menu, la choppe de bière, on ouvre une « SetupACtivity », qui permet de recommencer une recherche d’adresses.

Nous avons organisé l’architecture du code en plusieurs dossiers : « controler » avec les différentes activités, « data » pour tous les échanges avec les 2 API et la base de données SQLite, « model » pour les classes génériques, et « view » pour les adapteurs et outils d’affichage ». Tous les fichiers XML sont dans les ressources, en version portrait et en version paysage. Tous les textes sont des strings.xml, en version anglaise et française, et s’adapte en fonction de la langue du téléphone.

Nous avons créé une branche secondaire sur Git afin de pouvoir travailler en simultané sur différents aspects de l’application.

Contributeurs :
Arthur Catrisse
Martin Lavallart