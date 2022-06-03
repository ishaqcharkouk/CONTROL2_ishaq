package com.example.control2;

import java.io.Serializable;

public class Societe implements Serializable {
    public int id;
    private String Nom;
    private String SecteurActivité;
    private int Nombre;
    public Societe() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String Nom) {
        this.Nom =Nom;
    }
    public String getSecteurActivité() {
        return SecteurActivité;
    }
    public void setSecteurActivité(String SecteurActivité) { this.SecteurActivité = SecteurActivité;}
    public int getNombre() {
        return Nombre;
    }
    public void setNombre(int Nombre) {
        this.Nombre = Nombre;
    }
    public Societe(int id, String Nom,String SecteurActivité, int Nombre) {
        this.id = id;
        this.Nom = Nom;
        this.SecteurActivité = SecteurActivité;
        this.Nombre = Nombre;
    }

}
