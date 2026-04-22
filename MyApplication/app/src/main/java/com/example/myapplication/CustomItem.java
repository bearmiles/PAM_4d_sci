package com.example.myapplication;

public class CustomItem {
    public String pytanie;
    public String odpA;
    public String odpB;
    public String odpC;
    public int poprawne;
    public int ZaznaczonaOdp;

    public CustomItem(String pyt, String odpa, String odpb, String odpc, int poprawne, int zaznaczonaOdp){
        this.pytanie = pyt;
        this.odpA = odpa;
        this.odpB = odpb;
        this.odpC = odpc;
        this.poprawne = poprawne;
        this.ZaznaczonaOdp = zaznaczonaOdp;
    }

    public String getOdpA() {
        return odpA;
    }

    public String getOdpB() {
        return odpB;
    }

    public String getOdpC() {
        return odpC;
    }

    public String getPytanie() {
        return pytanie;
    }

    public int getPoprawne() {
        return poprawne;
    }

    public int getZaznaczonaOdp(){
        return ZaznaczonaOdp;
    }
}
