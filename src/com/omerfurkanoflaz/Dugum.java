package com.omerfurkanoflaz;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

//Komponentlerin bağlantı uçları için
public class Dugum extends Komponent {
    Rectangle dugum;
    static ArrayList<Dugum> dugumler = new ArrayList<>();
    static int cap = 10;
    static Color color = Color.RED;
    Ellipse2D.Double sekil;
    int x,y,xt,yt;
    ArrayList<Dugum> connect = new ArrayList<>();
    TypeEnum.InputOutput type;
    IOBaglanti baglanti;
    Boolean state;
    public Dugum(int x1, int y1, IOBaglanti con, TypeEnum.InputOutput type) {
        xt = x1;
        yt = y1;
        this.x = x1 - cap / 2;
        this.y = y1 - cap / 2;
        sekil = new Ellipse2D.Double(x, y, cap, cap);
        dugum = new Rectangle(x - 3, y - 3, 12, 12);
        this.type = type;
        this.baglanti = con;
        dugumler.add(this);
    }
    public void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.MouseLocation)) {
            drawOutline(g2d);
        }
        g2d.setColor(color);
        g2d.fill(sekil);
    }
    public void drawOutline(Graphics2D g2d) {
        Stroke kalinlik = new BasicStroke(2.2f);
        g2d.setColor(Color.GREEN);
        g2d.setStroke(kalinlik);
        g2d.draw(dugum);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, cap, cap);
    }

    public Boolean getState() {
        updateState();
        return state;
    }
    void updateState() {
        if (type == TypeEnum.InputOutput.Output) {
            state = baglanti.getState();
            return;
        }
        ArrayList<Boolean> shortTest = new ArrayList<>();
        for (Dugum n : connect) {
            if (n.type == TypeEnum.InputOutput.Output) {
                state = n.getState();
                shortTest.add(state);
            }
        }
        for (Dugum n : connect) {
            if (n.type == TypeEnum.InputOutput.Input) {
                state = n.state;
                return;
            }
        }
    }
}
