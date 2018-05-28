package com.omerfurkanoflaz;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class AND extends Komponent { //Komponent sınıfından türettim.
    Area sekil;
    //Hem 2 girişli hem 3 girişli and kapısı olacağı için 2 yapılandırıcı
    public AND(int x, int y) {
        this(x, y, 2); }
    public AND(int x, int y, int n) {
        this.x = x; //parametre olarak gelen x değerini bu sınıfa ait x değerine ata.
        this.y = y; //paramtre olarak gelen y değerini bu sınıfa ait y değerine ata.
        sekil = new AndCiz(x, y);
        outputs.add(new IOBaglanti(x+65, y +33, TypeEnum.Yon.Horizontal, TypeEnum.InputOutput.Output, this)); //çıkış bağlantı çubuğunu çiz.
        int bosluk = 65 / (n + 1); //girişler arasındaki aralık için
        int giris = y;
        for (int i = 0; i < n; i++) {
            giris += bosluk;
            //Gelen n değerine göre for o kadr dönecek ve o kadar giriş çizilecek.
            inputs.add(new IOBaglanti(x, giris, TypeEnum.Yon.Horizontal, TypeEnum.Konum.LEFT, TypeEnum.InputOutput.Input, this));
        } }
    Area getShape() {
        return sekil;
    }
    Boolean getState() {
        return LogicIslemler.AND(inputs);
    }
    void draw(Graphics2D g2d) {
        Color renk = Color.DARK_GRAY;
        BasicStroke kalinlik = new BasicStroke(5f);
        g2d.translate(tx, ty);
        baglantilariCiz(g2d);
        g2d.setColor(renk);
        g2d.setStroke(kalinlik);
        g2d.draw(sekil);
    }
    public static class AndCiz extends Area {
        int x,y;
        public AndCiz(int x, int y) {
            this.x = x;
            this.y = y;
            Ellipse2D.Double elips = new Ellipse2D.Double(x, y, 65, 65);
            Rectangle rectangle = new Rectangle(x, y, 33, 65);
            this.add(new Area(elips));
            this.add(new Area(rectangle));
        }
    }
}
