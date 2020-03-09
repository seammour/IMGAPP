package com.rwdata.coach.controleur;

import android.content.Context;

import com.rwdata.coach.modele.Profil;
import com.rwdata.coach.vue.Serializer;

public final class Controleur {
    private static Controleur instance = null;
    private static Profil profil;
    private static String fileName = "SaveFile";

    public static final Controleur getInstance(Context context) {
        if(Controleur.instance == null){
            Controleur.instance = new Controleur();
            recupSerialize(context);
        }
        return instance;
    }
    private Controleur(){
        super();
    }


    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context) {
        profil =new Profil( poids,  taille,  age,  sexe);
        Serializer.serialize(fileName, profil, context);
    }
    public float getImg(){
            return profil.getImg();
    }

    public String getMessage(){
        return profil.getMessage();
    }
    private static void recupSerialize(Context context){
        profil = (Profil) Serializer.deSerialize(fileName, context);
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        } else {
            return profil.getPoids();
        }
    }

    public Integer getTaille(){
        if(profil == null){
            return null;
        } else {
            return profil.getTaille();
        }
    }

    public Integer getAge(){
        if(profil == null){
            return null;
        } else {
            return profil.getAge();
        }
    }
    public Integer getSexe(){
        if(profil == null){
            return null;
        } else {
            return profil.getSexe();
        }
    }

}

