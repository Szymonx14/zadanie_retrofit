package com.example.retrofit_zadanie;
import com.google.gson.annotations.SerializedName;


public class Wiadomosc {
    @SerializedName("tytul")
    private String tytul;

    @SerializedName("tresc")
    private String tresc;

    @SerializedName("data")
    private String data;

    public Wiadomosc(String tytul, String tresc, String data) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data = data;
    }

    public String getTytul() {
        return tytul;
    }
    public String getTresc() {
        return tresc;
    }
    public String getData() {
        return data;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "tytul='" + tytul + '\'' +
                ", tresc='" + tresc + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
