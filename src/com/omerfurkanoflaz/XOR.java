package com.omerfurkanoflaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;

public class XOR extends Komponent {
    Color renk = Color.DARK_GRAY;
    Area sekil;
    boolean kosul = false;
    static BasicStroke kalinlik = new BasicStroke(5f);

    public XOR(int x, int y) {
        this.x = x;
        this.y = y;
        sekil = new OR.orCiz(x, y); //Or ile benzer olduğu için önce or çizip sonraki kısımları çiz.
        //Çıkış bağlantı çubuğunu çiz.
        outputs.add(new IOBaglanti(x + 60, y + 30, TypeEnum.Yon.Horizontal, TypeEnum.InputOutput.Output, this));
        for (int i = 0; i < 2; i++) {//iki girişli olduğu için for 2 kere dönecek 2 giriş çizecek.
            y+=20; //aradaki boşluğu ayarlamak için
            inputs.add(new IOBaglanti(x, y, TypeEnum.Yon.Horizontal, TypeEnum.Konum.LEFT, TypeEnum.InputOutput.Input, this));
        }
    }
    Area getShape() {
        return sekil;
    }
    synchronized Boolean getState() {
        Thread stateUpdater = new Thread() {
            public void run() {
                updateState();
            }
        };
        try {
            return kosul;
        } finally {
            stateUpdater.start();
        } }
    void updateState() {
        kosul = LogicIslemler.XOR(inputs);
    }
    void draw(Graphics2D g2d) {
        g2d.translate(tx, ty);
        baglantilariCiz(g2d);
        g2d.setColor(renk);
        g2d.setStroke(kalinlik);
        g2d.draw(sekil);
        g2d.drawArc(x - 25, y, 30, 60, -70, 140);
    }
}