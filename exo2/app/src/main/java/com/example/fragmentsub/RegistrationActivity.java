package com.example.fragmentsub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private EditText etNom;
    private EditText etPrenom;
    private EditText etDateNaissance;
    private EditText etTelephone;
    private EditText etEmail;
    private EditText etAdresse;
    private CheckBox cbSport;
    private CheckBox cbMusique;
    private CheckBox cbLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        etNom = findViewById(R.id.et_nom);
        etPrenom = findViewById(R.id.et_prenom);
        etDateNaissance = findViewById(R.id.et_date_naissance);
        etTelephone = findViewById(R.id.et_telephone);
        etEmail = findViewById(R.id.et_email);
        etAdresse = findViewById(R.id.et_adresse);
        cbSport = findViewById(R.id.cb_sport);
        cbMusique = findViewById(R.id.cb_musique);
        cbLecture = findViewById(R.id.cb_lecture);

        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(v -> submitRegistration());
    }

    private void submitRegistration() {
        String login = etLogin.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String dateNaissance = etDateNaissance.getText().toString().trim();
        String telephone = etTelephone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String adresse = etAdresse.getText().toString().trim();

        if (TextUtils.isEmpty(login)
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(nom)
                || TextUtils.isEmpty(prenom)
                || TextUtils.isEmpty(dateNaissance)
                || TextUtils.isEmpty(telephone)
                || TextUtils.isEmpty(email)) {
            Toast.makeText(this, R.string.error_required_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!ValidationUtils.isLoginValid(login)) {
            etLogin.setError(getString(R.string.error_invalid_login));
            return;
        }

        if (!ValidationUtils.isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_invalid_email));
            return;
        }

        String interests = User.buildInterestsString(
                cbSport.isChecked(),
                cbMusique.isChecked(),
                cbLecture.isChecked()
        );

        if (TextUtils.isEmpty(interests)) {
            Toast.makeText(this, R.string.error_no_interest, Toast.LENGTH_SHORT).show();
            return;
        }

        long userId;
        try (UserDbHelper dbHelper = new UserDbHelper(this)) {
            if (dbHelper.loginExists(login)) {
                etLogin.setError(getString(R.string.error_login_exists));
                return;
            }

            User user = new User(login, password, nom, prenom, dateNaissance, telephone, email, adresse, interests);
            userId = dbHelper.createUser(user);
        }

        if (userId == -1L) {
            Toast.makeText(this, R.string.error_db_insert, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.success_saved, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PlanningActivity.class);
        intent.putExtra(PlanningActivity.EXTRA_USER_ID, userId);
        startActivity(intent);
        finish();
    }
}

