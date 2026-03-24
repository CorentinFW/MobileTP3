# Exo2 - Inscription, Connexion, Planning (SQLite)

Ce projet Android implemente un flux complet base sur SQLite:

1. Ecran de choix: **Nouvelle inscription** ou **Connexion**.
2. Inscription avec validations:
   - login commence par une lettre, max 10 caracteres (`[A-Za-z][A-Za-z0-9_]{0,9}`)
   - mot de passe de 6 caracteres
   - login unique en base
3. Connexion par login/mot de passe.
4. Ecran Planning avec 4 creneaux (08-10, 10-12, 14-16, 16-18).
5. Sauvegarde du planning puis affichage d'une synthese.

## Structure principale

- `app/src/main/java/com/example/fragmentsub/ChoiceActivity.java`
- `app/src/main/java/com/example/fragmentsub/RegistrationActivity.java`
- `app/src/main/java/com/example/fragmentsub/LoginActivity.java`
- `app/src/main/java/com/example/fragmentsub/PlanningActivity.java`
- `app/src/main/java/com/example/fragmentsub/PlanningSummaryActivity.java`
- `app/src/main/java/com/example/fragmentsub/UserDbHelper.java`

## Lancer les tests unitaires

```bash
cd /home/corentinfay/Bureau/MobileTP3/exo2
./gradlew testDebugUnitTest
```

## Construire l'application

```bash
cd /home/corentinfay/Bureau/MobileTP3/exo2
./gradlew assembleDebug
```

