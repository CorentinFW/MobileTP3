package com.example.fragmentsub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_LOGIN = "login";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_DATE_NAISSANCE = "date_naissance";
    public static final String COL_TELEPHONE = "telephone";
    public static final String COL_EMAIL = "email";
    public static final String COL_ADRESSE = "adresse";
    public static final String COL_INTERETS = "interets";

    public static final String TABLE_PLANNING = "planning";
    public static final String COL_PLANNING_ID = "id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_SLOT_08_10 = "slot_08_10";
    public static final String COL_SLOT_10_12 = "slot_10_12";
    public static final String COL_SLOT_14_16 = "slot_14_16";
    public static final String COL_SLOT_16_18 = "slot_16_18";

    public UserDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LOGIN + " TEXT NOT NULL UNIQUE, "
                + COL_PASSWORD + " TEXT NOT NULL, "
                + COL_NOM + " TEXT NOT NULL, "
                + COL_PRENOM + " TEXT NOT NULL, "
                + COL_DATE_NAISSANCE + " TEXT NOT NULL, "
                + COL_TELEPHONE + " TEXT NOT NULL, "
                + COL_EMAIL + " TEXT NOT NULL, "
                + COL_ADRESSE + " TEXT, "
                + COL_INTERETS + " TEXT NOT NULL"
                + ");";
        db.execSQL(createUsersTable);

        String createPlanningTable = "CREATE TABLE " + TABLE_PLANNING + " ("
                + COL_PLANNING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_ID + " INTEGER NOT NULL UNIQUE, "
                + COL_SLOT_08_10 + " TEXT NOT NULL, "
                + COL_SLOT_10_12 + " TEXT NOT NULL, "
                + COL_SLOT_14_16 + " TEXT NOT NULL, "
                + COL_SLOT_16_18 + " TEXT NOT NULL, "
                + "FOREIGN KEY(" + COL_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COL_ID + ")"
                + ");";
        db.execSQL(createPlanningTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(User user) {
        return createUser(user);
    }

    public long createUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOGIN, user.getLogin());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_DATE_NAISSANCE, user.getDateNaissance());
        values.put(COL_TELEPHONE, user.getTelephone());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_ADRESSE, user.getAdresse());
        values.put(COL_INTERETS, user.getCentresInteret());
        return db.insert(TABLE_USERS, null, values);
    }

    public boolean loginExists(String login) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT " + COL_ID + " FROM " + TABLE_USERS + " WHERE " + COL_LOGIN + " = ? LIMIT 1",
                new String[]{login})) {
            return cursor.moveToFirst();
        }
    }

    public long authenticate(String login, String password) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT " + COL_ID + " FROM " + TABLE_USERS
                        + " WHERE " + COL_LOGIN + " = ? AND " + COL_PASSWORD + " = ? LIMIT 1",
                new String[]{login, password})) {
            if (!cursor.moveToFirst()) {
                return -1L;
            }
            return cursor.getLong(0);
        }
    }

    public boolean savePlanning(long userId, String slot0810, String slot1012, String slot1416, String slot1618) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID, userId);
        values.put(COL_SLOT_08_10, slot0810);
        values.put(COL_SLOT_10_12, slot1012);
        values.put(COL_SLOT_14_16, slot1416);
        values.put(COL_SLOT_16_18, slot1618);

        int updatedRows = db.update(TABLE_PLANNING, values, COL_USER_ID + " = ?", new String[]{String.valueOf(userId)});
        if (updatedRows > 0) {
            return true;
        }
        return db.insert(TABLE_PLANNING, null, values) != -1;
    }

    public PlanningData getPlanningForUser(long userId) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT " + COL_SLOT_08_10 + ", " + COL_SLOT_10_12 + ", " + COL_SLOT_14_16 + ", " + COL_SLOT_16_18
                        + " FROM " + TABLE_PLANNING + " WHERE " + COL_USER_ID + " = ? LIMIT 1",
                new String[]{String.valueOf(userId)})) {
            if (!cursor.moveToFirst()) {
                return null;
            }
            return new PlanningData(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        }
    }

    public String getLoginById(long userId) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT " + COL_LOGIN + " FROM " + TABLE_USERS + " WHERE " + COL_ID + " = ? LIMIT 1",
                new String[]{String.valueOf(userId)})) {
            if (!cursor.moveToFirst()) {
                return null;
            }
            return cursor.getString(0);
        }
    }
}

