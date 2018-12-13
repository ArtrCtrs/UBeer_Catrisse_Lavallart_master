Welcome to UBeer

This application's goal is to find bars at the center point of different people's locations

This application was created for a school project.
In the first activity, you may enter different addresses (and optional names)
When you press on search, a request to Google's geocode API be done for each entered address to get the latitudes and longitudes of those places.
Then, using the center point of all these places, another request with be done(using Google places API) to get bars in the vicinity.



Contributors :
Arthur Catrisse
Martin Lavallart

MaBaseSQLlite permet d'instancier la base de données
BarBDD permet de manipuler les données de la base de données (select, ajout, suppression )
Les données sont sauvgardées en local sur le téléphone ce qui permet de garder les données lors de la fermeture de l'application.