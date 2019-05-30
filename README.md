# broker

Système minimal de publication/souscription en BCM4Java.

Execution sous Eclipse
Pour les mono JVM (CVM et CVM2) simplement un run as Java application

Pour la multi JVM :
executer le fichier Distributed_Launcher : Il faut au préalable modifier les arguments ligne 25 comme spécifier en commentaires
et changer également le début de chemin dans le config.xml et la dcvm.policy (dans src/app)