# TP Fragments + SQLite

Cette application Android (Java) propose un flux d'inscription en 2 fragments :

1. `RegistrationFragment` : saisie des informations utilisateur et des centres d'interet.
2. `SummaryFragment` : affichage de la synthese des donnees saisies.

Les donnees sont enregistrees dans une base SQLite locale via `UserDbHelper`.

## Structure cle

- `app/src/main/java/com/example/fragmentsub/MainActivity.java` : hote des fragments.
- `app/src/main/java/com/example/fragmentsub/RegistrationFragment.java` : formulaire d'inscription.
- `app/src/main/java/com/example/fragmentsub/SummaryFragment.java` : ecran de synthese.
- `app/src/main/java/com/example/fragmentsub/UserDbHelper.java` : creation et insertion SQLite.
- `app/src/main/java/com/example/fragmentsub/User.java` : modele de donnees utilisateur.

## Lancer les tests

```bash
./gradlew test
```

## Demarrer l'application

```bash
./gradlew installDebug
```

Puis lancer l'app sur un emulateur ou appareil Android.

