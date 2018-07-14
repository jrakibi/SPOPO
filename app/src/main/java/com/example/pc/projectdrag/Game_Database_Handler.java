package com.example.pc.projectdrag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by Lenovo on 4/15/2018.
 */

public class Game_Database_Handler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Game";
    // Table name
    private static final String TABLE_GAME_i = "Game_Infos";
    // Table Columns names
    private static String a="id";
    private static String b="id_application";
    private static String c="id_apprenant";
    private static String d="id_accompagnant";
    private static String e="id_exercice";
    private static String f="id_niveau";
    private static String g="date_actuelle";
    private static String h="heure_debut";
    private static String i="heure_fin";
    private static String j="Nombre_operation_reuss";
    private static String k="Nombre_operation_echou";
    private static String l="minimum_temps_operation_sec";
    private static String m="moyen_temps_operation_sec";
    private static String n="longitude";
    private static String o="latitude";
    private static String p="device";
    private static String q="flag";

    public Game_Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GAME_i + "("
                + a + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + b + " VARCHAR(100),"
                + c + " VARCHAR(100),"
                + d + " VARCHAR(100),"
                + e + " VARCHAR(100),"
                + f + " VARCHAR(100),"
                + g + " VARCHAR(100),"
                + h + " VARCHAR(100),"
                + i + " VARCHAR(100),"
                + j + " INTEGER,"
                + k + " INTEGER,"
                + l + " INTEGER,"
                + m + " INTEGER,"
                + n + " DOUBLE,"
                + o + " DOUBLE,"
                + p + " VARCHAR(100),"
                + q + " BOOLEAN"
                +")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_i);
        // Creating tables again
        onCreate(db);
    }

    public void addGame(Game Game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(b, Game.getId_application());
        values.put(c, Game.getId_apprenant());
        values.put(d, Game.getId_accompagnant());
        values.put(e, Game.getId_exercice());
        values.put(f, Game.getId_niveau());
        values.put(g, Game.getDate_actuelle());
        values.put(h, Game.getHeure_debut());
        values.put(i, Game.getHeure_fin());
        values.put(j, Game.getNombre_operation_reuss());
        values.put(k, Game.getNombre_operation_echou());
        values.put(l, Game.getMinimum_temps_operation_sec());
        values.put(m, Game.getMoyen_temps_operation_sec());
        values.put(n, Game.getLongitude());
        values.put(o, Game.getLatitude());
        values.put(p, Game.getDevice());
        values.put(q, Game.isFlag());

        // Inserting Row
        db.insert(TABLE_GAME_i, null, values);
        db.close(); // Closing database connection
    }

    public String getData(String level){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT "+Game_Database_Handler.TABLE_GAME_i+".* FROM "+Game_Database_Handler.TABLE_GAME_i+" WHERE "+Game_Database_Handler.f+"=? ", new String[]{level});
        if(c.getCount() == 0) return "Nothing to show !";
        else {
            c.moveToNext();
            db.close();
            return "Date : "+c.getString(6)+" Heure debut: "+c.getString(7)+" heure fin: "+c.getString(8)+
                    "NB ress: "+c.getDouble(9)+ " Min "+c.getDouble(11)+" Moy: "+c.getDouble(12);
        }
    }
}
