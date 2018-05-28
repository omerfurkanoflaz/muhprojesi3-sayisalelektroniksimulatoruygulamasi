package com.omerfurkanoflaz;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class OR extends Komponent {
    Area sekil;
    Color color = Color.DARK_GRAY;
    boolean state = false;
    public OR(int x, int y) {
        this(x, y, 2);
    }
    public OR(int x, int y, int n) {
        this.x = x;
        this.y = y;
        sekil = new orCiz(x, y);
        outputs.add(new IOBaglanti(x + 60, y + 33, TypeEnum.Yon.Horizontal, TypeEnum.InputOutput.Output, this));
        int number = n;
        int gap = 60 / (number + 1);
        int yt = y;
        for (int i = 0; i < number; i++) {
            yt += gap;
            inputs.add(new IOBaglanti(x + 5, yt, TypeEnum.Yon.Horizontal, TypeEnum.Konum.LEFT, TypeEnum.InputOutput.Input, this));
        } }
    Area getShape() {
        return sekil;
    }
    Rectangle getBounds() {
        return new Rectangle(x - 8, y - 8, 60 + 2 * 8, 60 + 2 * 8);
    }
    synchronized Boolean getState() {
        Thread stateUpdater = new Thread() {
            public void run() {
                updateState();
            }
        };
        try {
            return state;
        } finally {
            stateUpdater.start();
        } }
    void updateState() {
        state = LogicIslemler.OR(inputs);
    }
    void draw(Graphics2D g2d) {
        BasicStroke kalinlik = new BasicStroke(5f);
        g2d.translate(tx, ty);
        baglantilariCiz(g2d);
        g2d.setColor(color);
        g2d.setStroke(kalinlik);
        g2d.draw(sekil);
    }
    public static class orCiz extends Area {
        public orCiz(int x, int y) {
            Ellipse2D.Double ellipseShape = new Ellipse2D.Double(x, y, 65, 65);
            Rectangle rectShape = new Rectangle(x, y, 33, 65);
            Ellipse2D.Double ellipseShape2 = new Ellipse2D.Double(x - (65/ 4), y, (65 / 2), 65);
            this.add(new Area(ellipseShape));
            this.add(new Area(rectShape));
            this.subtract(new Area(ellipseShape2));
        }
    }

}
