package com.omerfurkanoflaz;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaglantiKablolari extends Komponent {
    Dugum baslangic,bitis;
    static CopyOnWriteArrayList<BaglantiKablolari> baglantilar = new CopyOnWriteArrayList<>();
    GeneralPath cizgi;
    int[] x;
    int[] y;
    int dugum;
    public BaglantiKablolari(int[] xn, int[] yn, int nokta, Dugum x1, Dugum x2) {
        baslangic =x1;
        bitis = x2;
        x1.connect.add(x2);
        x2.connect.add(x1);
        dugum = nokta;
        x = new int[dugum];
        y = new int[dugum];
        for (int i = 0; i < dugum; i++) {
            x[i] = xn[i];
            y[i] = yn[i];
        }
        x[0] = x1.xt;
        y[0] = x1.yt;
        x[dugum - 2] = x2.xt;
        y[dugum - 2] = x2.yt;
        cizgi = new GeneralPath(GeneralPath.WIND_EVEN_ODD, dugum);
        cizgi.moveTo(x[0], y[0]);
        for (int index = 1; index < dugum; index++) {
            cizgi.lineTo(x[index], y[index]);
        };
        baglantilar.add(this);
    }
    public BaglantiKablolari(Dugum c1, Dugum c2) {
        baslangic= c1;
        bitis = c2;
    }
    public void draw(Graphics2D g2d) {
        BasicStroke kalinlik = new BasicStroke(3);
        g2d.setColor(Color.RED);
        g2d.setStroke(kalinlik);
        g2d.draw(cizgi);
    }
    public static void DrawAll(Graphics2D g2d) {
        for (BaglantiKablolari wire : baglantilar) {
            wire.draw(g2d);
        }
    }

}
