package com.example.pc.projectdrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class score extends AppCompatActivity {
    private static score instance;
    public static TextView id_niveau1;
    public static TextView date_actuelle1;
    public static TextView heure_debut1;
    public static TextView heure_fin1;
    public static TextView Nombre_operation_reuss1;
    public static TextView Nombre_operation_echou1;
    public static TextView minimum_temps_operation_sec1;
    public static TextView moyen_temps_operation_sec1;

    public static TextView id_niveau2;
    public static TextView date_actuelle2;
    public static TextView heure_debut2;
    public static TextView heure_fin2;
    public static TextView Nombre_operation_reuss2;
    public static TextView Nombre_operation_echou2;
    public static TextView minimum_temps_operation_sec2;
    public static TextView moyen_temps_operation_sec2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        id_niveau1 = (TextView) findViewById(R.id.id_niveau1);
        date_actuelle1= (TextView) findViewById(R.id.date_actuelle1);
        heure_debut1 = (TextView) findViewById(R.id.heure_debut1);
        heure_fin1 = (TextView) findViewById(R.id.heure_fin1);
        Nombre_operation_reuss1 = (TextView) findViewById(R.id.nombre_operation_reuss1);
        Nombre_operation_echou1 = (TextView) findViewById(R.id.nombre_operation_echoue1);
        minimum_temps_operation_sec1 = (TextView) findViewById(R.id.minimum_temps_operation_sec1);
        moyen_temps_operation_sec1 = (TextView) findViewById(R.id.moyen_temps_operation_sec1);

        id_niveau2 = (TextView) findViewById(R.id.id_niveau2);
        date_actuelle2= (TextView) findViewById(R.id.date_actuelle2);
        heure_debut2 = (TextView) findViewById(R.id.heure_debut2);
        heure_fin2 = (TextView) findViewById(R.id.heure_fin2);
        Nombre_operation_reuss2 = (TextView) findViewById(R.id.nombre_operation_reuss2);
        Nombre_operation_echou2 = (TextView) findViewById(R.id.nombre_operation_echoue2);
        minimum_temps_operation_sec2 = (TextView) findViewById(R.id.minimum_temps_operation_sec2);
        moyen_temps_operation_sec2 = (TextView) findViewById(R.id.moyen_temps_operation_sec2);

        score.id_niveau2.setText(Globals.id_niveau1);
        score.date_actuelle2.setText(Globals.date_actuelle1);
        score.heure_debut2.setText(Globals.heure_debut1);
        score.heure_fin2.setText(Globals.heure_fin1);
        score.Nombre_operation_reuss2.setText(String.valueOf(Globals.Nombre_operation_reuss_level1));
        score.Nombre_operation_echou2.setText(String.valueOf(Globals.Nombre_operation_echou1));
        score.minimum_temps_operation_sec2.setText(String.valueOf(Globals.minimum_temps_operation_sec));
        score.moyen_temps_operation_sec2.setText(String.valueOf(Globals.moyen_temps_operation_sec1));

        score.id_niveau1.setText(Globals.id_niveau2);
        score.date_actuelle1.setText(Globals.date_actuelle2);
        score.heure_debut1.setText(Globals.heure_debut2);
        score.heure_fin1.setText(Globals.heure_fin2);
        score.Nombre_operation_reuss1.setText(String.valueOf(Globals.Nombre_operation_reuss_level2));
        score.Nombre_operation_echou1.setText(String.valueOf(Globals.Nombre_operation_echou2));
        score.minimum_temps_operation_sec1.setText(String.valueOf(Globals.minimum_temps_operation_sec));
        score.moyen_temps_operation_sec1.setText(String.valueOf(Globals.moyen_temps_operation_sec2));
    }
}
