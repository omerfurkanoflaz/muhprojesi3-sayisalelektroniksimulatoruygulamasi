package com.omerfurkanoflaz;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Komponent { //Soyut sınıf (Kendisinden türetilecek sınıflara yol gösterici sınıf diyebiliriz.)

    int x, y; //Mouse konumu için
    int tx = 0, ty = 0;
    ArrayList<IOBaglanti> inputs = new ArrayList<>(); //IOBaglanti sınıfı türünden girişleri tutan liste. (Kaç girişli olduğunu)
    ArrayList<IOBaglanti> outputs = new ArrayList<>(); //IOBaglanti türünden çıkışı tutan liste.
    static ArrayList<Komponent> ciz = new ArrayList<>();
    public Komponent() {
    }
    abstract void draw(Graphics2D g2d); //Bunu diğer sınıflar üzerinden override edip şekilleri çizmek için kullanacağız. (Soyut method)
    void baglantilariCiz(Graphics2D g2d) {
        inputCiz(g2d);
        outputCiz(g2d);
    }
    void inputCiz(Graphics2D g2d) { //inputs listesi içerisindeki giriş sayısı kadar giriş bağlantısı çizmek için tek tek bagla değişkenine atıp draw fonk. çağırıyoruz.
        for (IOBaglanti bagla : inputs) {
            bagla.draw(g2d);
        }
    }
    void outputCiz(Graphics2D g2d) { // çıkış bağlantısı çizmek için outputs listesindeki çıkışı bagla değişkenine atıp draw fonk. çağırıyoruz.
        for (IOBaglanti bagla : outputs) {
            bagla.draw(g2d);
        }
    }


    Shape getShape() {
        return null;
    }

    Boolean getState() {
        return null;
    }

    void onClick() {
    }
}
