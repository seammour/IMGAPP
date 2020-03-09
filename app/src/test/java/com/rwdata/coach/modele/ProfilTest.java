package com.rwdata.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    private static Profil profil = new Profil(67,165,35,0);

    private float img = (float) 32.2;

    @Test
    public void getImg() {
        assertEquals(img, profil.getImg(), 0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(Profil.TROP_ELEVE, profil.getMessage());
    }
}