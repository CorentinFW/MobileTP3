package com.example.fragmentsub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

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

    public UserDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LOGIN + " TEXT NOT NULL, "
                + COL_PASSWORD + " TEXT NOT NULL, "
                + COL_NOM + " TEXT NOT NULL, "
                + COL_PRENOM + " TEXT NOT NULL, "
                + COL_DATE_NAISSANCE + " TEXT NOT NULL, "
                + COL_TELEPHONE + " TEXT NOT NULL, "
                + COL_EMAIL + " TEXT NOT NULL, "
                + COL_ADRESSE + " TEXT, "
                + COL_INTERETS + " TEXT NOT NULL"
                + ");";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(User user) {
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
}

