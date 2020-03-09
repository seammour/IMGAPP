package com.rwdata.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.rwdata.coach.R;
import com.rwdata.coach.controleur.Controleur;
import com.rwdata.coach.modele.Profil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        this.controleur = Controleur.getInstance(this);
        init();


    }

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblImg;
    private ImageView imgSmiley;
    private Button btnCalc;
    private Controleur controleur;

    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblImg = (TextView) findViewById(R.id.lblImg);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        ecouterCalcul();
        recupProfil();
    }

    public void ecouterCalcul() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                Log.i("message", "test*****************************************");
                Log.d("message", "test*****************************************");
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception ex) {

                }
                if (rdHomme.isChecked()) {
                    sexe = 1;
                    Log.d("message", "Homme*****************************************");
                }
                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Saisie incorrect", Toast.LENGTH_SHORT).show();

                } else {
                    afficherResult(poids, taille, age, sexe);
                    Log.d("message", "afficherResult*****************************************");

                }

            }
        });
    }

    private void afficherResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        Log.d("message", "(poids, taille, age, sexe) = " + poids +", "+ taille +", "+ age +", "+ sexe );

        this.controleur.creerProfil(poids, taille, age, sexe, this);
        float img = this.controleur.getImg();
        Log.d("message", "img"+ img );

        String message = this.controleur.getMessage();
        if (Profil.NORMAL.equals(message)) {
            imgSmiley.setImageResource(R.drawable.normal);
        } else if (Profil.TROP_FAIBLE.equals(message)) {
            imgSmiley.setImageResource(R.drawable.maigre);
        } else {
            imgSmiley.setImageResource(R.drawable.grasse);
        }
        lblImg.setText(String.format("%.01f", img) + "  : IMG " + message);

    }
    private void recupProfil(){
        if(controleur.getPoids() != null ){
            txtPoids.setText(controleur.getPoids().toString());
            txtTaille.setText(controleur.getTaille().toString());
            txtAge.setText(controleur.getAge().toString());
            rdFemme.setChecked(true);
            if(controleur.getSexe() ==1){
                rdHomme.setChecked(true);
            }
            ((Button)findViewById(R.id.btnCalc)).performClick();
        }
    }
}
