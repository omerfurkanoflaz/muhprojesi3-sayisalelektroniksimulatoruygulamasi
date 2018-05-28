package com.omerfurkanoflaz;

import java.awt.*;

public class Mouse {

    static String component = null;
    static Point MouseLocation = new Point(0, 0);
    static Point ClickLocation = new Point(0, 0);
    static Secim secim = Secim.Komponent;
    static Durum durum = Durum.sürükle;
    public enum Durum {
        KomponentYerlestir,
        sürükle
    };
    public enum Secim {

        Baglanti,
        Komponent
    };

    static void setComponent(String in) {
        component = in;
    }

    static String selectedComponent() {
        return component;
    }

    static void setMouseLocation(int x, int y) {
        MouseLocation.setLocation(x, y);
    }

    static void setClockLocation(Point p) {
        ClickLocation = p;
    }

    static int getClickX() {
        return (int) ClickLocation.getX();
    }

    static int getClickY() {
        return (int) ClickLocation.getY();
    }

    static int getLocX() {
        return (int) MouseLocation.getX();
    }

    static int getLocY() {
        return (int) MouseLocation.getY();
    }

}
