package com.rwdata.coach.modele;

import android.util.Log;

import java.io.Serializable;

public class Profil implements Serializable {


    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;
    public static final String NORMAL = "normal";
    public static final String TROP_FAIBLE = "trop faible";
    public static final String TROP_ELEVE = "trop elevé";

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculImg();
        this.resultImg();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    private void calculImg() {

     int[] totot =  {1, 2};
        float tailleM = (float)taille/100;
        this.img = (float) (((1.2 * poids) / (tailleM * tailleM))
                +(0.23 * age) - (10.83 * sexe) - 5.4) ;

    }

    private void resultImg() {
        float min;
        float max;
        if(sexe==0){
            min = minFemme;
            max = maxFemme;
        } else {
            min = minHomme;
            max = maxHomme;
        }
        message = NORMAL;
        Log.d("message", "(min, max, img) = " + min +", "+ max +", "+ img  );

        if(img<min){
            message = TROP_FAIBLE;
            Log.d("message", "img<min" );

        } else if (img>max) {
            message = TROP_ELEVE;
            Log.d("message", "img>max" );
        }
    }
}
