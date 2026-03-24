package com.example.fragmentsub;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_summary);

        long userId = getIntent().getLongExtra(PlanningActivity.EXTRA_USER_ID, -1L);
        if (userId == -1L) {
            Toast.makeText(this, R.string.error_unknown_user, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        PlanningData planningData;
        String login;
        try (UserDbHelper dbHelper = new UserDbHelper(this)) {
            planningData = dbHelper.getPlanningForUser(userId);
            login = dbHelper.getLoginById(userId);
        }

        if (planningData == null) {
            Toast.makeText(this, R.string.error_planning_missing, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ((TextView) findViewById(R.id.tv_summary_login)).setText(
                getString(R.string.summary_login, login == null ? "-" : login)
        );
        ((TextView) findViewById(R.id.tv_summary_slot_08_10)).setText(
                getString(R.string.summary_slot_08_10, planningData.getSlot0810())
        );
        ((TextView) findViewById(R.id.tv_summary_slot_10_12)).setText(
                getString(R.string.summary_slot_10_12, planningData.getSlot1012())
        );
        ((TextView) findViewById(R.id.tv_summary_slot_14_16)).setText(
                getString(R.string.summary_slot_14_16, planningData.getSlot1416())
        );
        ((TextView) findViewById(R.id.tv_summary_slot_16_18)).setText(
                getString(R.string.summary_slot_16_18, planningData.getSlot1618())
        );
    }
}

