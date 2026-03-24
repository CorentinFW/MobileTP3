package com.example.fragmentsub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button btnInscription = findViewById(R.id.btn_new_registration);
        Button btnConnexion = findViewById(R.id.btn_login);

        btnInscription.setOnClickListener(v ->
                startActivity(new Intent(ChoiceActivity.this, RegistrationActivity.class))
        );
        btnConnexion.setOnClickListener(v ->
                startActivity(new Intent(ChoiceActivity.this, LoginActivity.class))
        );
    }
}

