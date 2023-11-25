package com.example.recept2;

import java.sql.Blob;

public class Recept {
    private int Id;
    private String Nev;
    private String Leiras;
    private String Kep;
    private int Hanyfo;
    private String Alapanyagok;
    private String Kategoria;

    public Recept(String nev, String kep, String leiras, int hanyfo, String alapanyagok, String kategoria) {
        Nev = nev;
        Kep = kep;
        Leiras = leiras;
        Hanyfo = hanyfo;
        Alapanyagok = alapanyagok;
        Kategoria = kategoria;
    }

    public Recept(int id, String nev, String kep, String leiras, int hanyfo, String alapanyagok, String kategoria) {
        Id = id;
        Nev = nev;
        Kep = kep;
        Leiras = leiras;
        Hanyfo = hanyfo;
        Alapanyagok = alapanyagok;
        Kategoria = kategoria;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNev() {
        return Nev;
    }

    public void setNev(String nev) {
        Nev = nev;
    }

    public String getLeiras() {
        return Leiras;
    }

    public void setLeiras(String leiras) {
        Leiras = leiras;
    }

    public int getHanyfo() {
        return Hanyfo;
    }

    public void setHanyfo(int hanyfo) {
        Hanyfo = hanyfo;
    }

    public String getAlapanyagok() {
        return Alapanyagok;
    }

    public void setAlapanyagok(String alapanyagok) {
        Alapanyagok = alapanyagok;
    }

    public String getKategoria() {
        return Kategoria;
    }

    public void setKategoria(String kategoria) {
        Kategoria = kategoria;
    }
}
