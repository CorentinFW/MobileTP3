package com.example.fragmentsub;

public class User {
    private final String login;
    private final String password;
    private final String nom;
    private final String prenom;
    private final String dateNaissance;
    private final String telephone;
    private final String email;
    private final String adresse;
    private final String centresInteret;

    public User(String login, String password, String nom, String prenom, String dateNaissance,
                String telephone, String email, String adresse, String centresInteret) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.centresInteret = centresInteret;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCentresInteret() {
        return centresInteret;
    }

    public static String buildInterestsString(boolean sport, boolean musique, boolean lecture) {
        StringBuilder sb = new StringBuilder();
        if (sport) {
            sb.append("sport");
        }
        if (musique) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("musique");
        }
        if (lecture) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("lecture");
        }
        return sb.toString();
    }
}

