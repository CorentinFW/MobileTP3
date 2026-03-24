package com.example.fragmentsub;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegistrationFragment extends Fragment {

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

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etLogin = view.findViewById(R.id.et_login);
        etPassword = view.findViewById(R.id.et_password);
        etNom = view.findViewById(R.id.et_nom);
        etPrenom = view.findViewById(R.id.et_prenom);
        etDateNaissance = view.findViewById(R.id.et_date_naissance);
        etTelephone = view.findViewById(R.id.et_telephone);
        etEmail = view.findViewById(R.id.et_email);
        etAdresse = view.findViewById(R.id.et_adresse);
        cbSport = view.findViewById(R.id.cb_sport);
        cbMusique = view.findViewById(R.id.cb_musique);
        cbLecture = view.findViewById(R.id.cb_lecture);

        Button btnSubmit = view.findViewById(R.id.btn_submit);
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
            Toast.makeText(requireContext(), R.string.error_required_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), R.string.error_invalid_email, Toast.LENGTH_SHORT).show();
            return;
        }

        String interests = User.buildInterestsString(
                cbSport.isChecked(),
                cbMusique.isChecked(),
                cbLecture.isChecked()
        );

        if (TextUtils.isEmpty(interests)) {
            Toast.makeText(requireContext(), R.string.error_no_interest, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(login, password, nom, prenom, dateNaissance, telephone, email, adresse, interests);
        long insertedId;
        try (UserDbHelper dbHelper = new UserDbHelper(requireContext())) {
            insertedId = dbHelper.insertUser(user);
        }

        if (insertedId == -1) {
            Toast.makeText(requireContext(), R.string.error_db_insert, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(requireContext(), R.string.success_saved, Toast.LENGTH_SHORT).show();

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, SummaryFragment.newInstance(user, insertedId))
                .addToBackStack(null)
                .commit();
    }
}


