package com.example.fragmentsub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);

        Button btnConnect = findViewById(R.id.btn_connect);
        btnConnect.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String login = etLogin.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.error_required_login_password, Toast.LENGTH_SHORT).show();
            return;
        }

        long userId;
        try (UserDbHelper dbHelper = new UserDbHelper(this)) {
            userId = dbHelper.authenticate(login, password);
        }

        if (userId == -1L) {
            Toast.makeText(this, R.string.error_login_failed, Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            return;
        }

        Intent intent = new Intent(this, PlanningActivity.class);
        intent.putExtra(PlanningActivity.EXTRA_USER_ID, userId);
        startActivity(intent);
        finish();
    }
}

