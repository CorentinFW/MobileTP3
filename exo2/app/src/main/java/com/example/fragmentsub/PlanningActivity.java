package com.example.fragmentsub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "extra_user_id";

    private EditText etSlot0810;
    private EditText etSlot1012;
    private EditText etSlot1416;
    private EditText etSlot1618;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        etSlot0810 = findViewById(R.id.et_slot_08_10);
        etSlot1012 = findViewById(R.id.et_slot_10_12);
        etSlot1416 = findViewById(R.id.et_slot_14_16);
        etSlot1618 = findViewById(R.id.et_slot_16_18);

        Button btnSave = findViewById(R.id.btn_save_planning);
        btnSave.setOnClickListener(v -> savePlanning());
    }

    private void savePlanning() {
        long userId = getIntent().getLongExtra(EXTRA_USER_ID, -1L);
        if (userId == -1L) {
            Toast.makeText(this, R.string.error_unknown_user, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String slot0810 = etSlot0810.getText().toString().trim();
        String slot1012 = etSlot1012.getText().toString().trim();
        String slot1416 = etSlot1416.getText().toString().trim();
        String slot1618 = etSlot1618.getText().toString().trim();

        if (TextUtils.isEmpty(slot0810)
                || TextUtils.isEmpty(slot1012)
                || TextUtils.isEmpty(slot1416)
                || TextUtils.isEmpty(slot1618)) {
            Toast.makeText(this, R.string.error_planning_required, Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success;
        try (UserDbHelper dbHelper = new UserDbHelper(this)) {
            success = dbHelper.savePlanning(userId, slot0810, slot1012, slot1416, slot1618);
        }

        if (!success) {
            Toast.makeText(this, R.string.error_planning_save, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.success_planning_saved, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PlanningSummaryActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        startActivity(intent);
        finish();
    }
}

