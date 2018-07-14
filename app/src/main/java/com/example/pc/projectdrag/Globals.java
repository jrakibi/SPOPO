package com.example.pc.projectdrag;

import android.location.Location;
import android.location.LocationManager;
import android.widget.RadioButton;
import android.widget.Switch;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 01/05/2018.
 */


public class Globals {
    private static Globals instance;

    public static RadioButton lang;
    public static int soundStatus = 1;

    public static List<Integer> timeMinMax2 = new ArrayList<Integer>();
    public static List<Integer> timeMinMax1 = new ArrayList<Integer>();

    public static List<Integer> timeTableForAVG1 = new ArrayList<Integer>();
    public static List<Integer> timeTableForAVG2 = new ArrayList<Integer>();

    public static String id_application;
    public static String id_apprenant;
    public static String id_accompagnant;
    public static String id_exercice;
    public static String id_niveau1;
    public static String id_niveau2;
    public static String date_actuelle1;
    public static String date_actuelle2;
    public static String heure_debut1;
    public static String heure_debut2;
    public static String heure_fin1;
    public static String heure_fin2;
    public static int Nombre_operation_reuss_level1 = 0;
    public static int Nombre_operation_reuss_level2 = 0;
    public static int Nombre_operation_echou1 = 0;
    public static int Nombre_operation_echou2 = 0;
    public static int minimum_temps_operation_sec=0;
    public static int moyen_temps_operation_sec1=0;
    public static int moyen_temps_operation_sec2=0;
    public static double longitude;
    public static double latitude;
    public static String device;
    public static boolean flag;


    // Restrict the constructor from being instantiated
    private Globals(){}


    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
