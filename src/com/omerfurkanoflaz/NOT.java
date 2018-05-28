package com.omerfurkanoflaz;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class NOT extends Komponent {
    Ellipse2D.Double daire;
    Area sekil;
    public NOT(int x, int y) {
        this.x = x;
        this.y = y;
        sekil = new notCiz(x, y);
        daire = new Ellipse2D.Double(x + 50, y + 20, 10, 10);
        sekil.add(new Area(daire));
        inputs.add(new IOBaglanti(x + 10 - 8, y + 25, TypeEnum.Yon.Horizontal, TypeEnum.Konum.LEFT, TypeEnum.InputOutput.Input, this));
        outputs.add(new IOBaglanti(x + 60, y + 25, TypeEnum.Yon.Horizontal, TypeEnum.InputOutput.Output, this));
    }
    Color renk = Color.DARK_GRAY;
    static BasicStroke kalinlik = new BasicStroke(5f);
    Area getShape() {
        return sekil;
    }

    Rectangle getBounds() {
        return new Rectangle(x - 8, y - 8, 66, 41);
    }

    Color getShapeColor() {
        return renk;
    }

    Boolean getState() {
        return LogicIslemler.NOT(inputs.get(0).getState());
    }
    void draw(Graphics2D g2d) {
        g2d.translate(tx, ty);
        g2d.setColor(renk);
        g2d.setStroke(kalinlik);
        g2d.draw(sekil);
        baglantilariCiz(g2d);
    }
    public class notCiz extends Area {
        int x, y;
        public notCiz(int x, int y) {
            this.x = x;
            this.y = y;
           Polygon ucgen = new Polygon();
            ucgen.addPoint(x, y + 2 * 25);
            ucgen.addPoint(x, y);
            ucgen.addPoint(x + 50, y + 25);
            this.add(new Area(ucgen));
        }
    }
}
