package com.omerfurkanoflaz;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
public class Simulasyon extends JPanel {

    Komponent c = null;
    Graphics2D g2d;
    BaglantiKablolari kablo = null;
    int indis = 1;
    int[] xl = new int[50];
    int[] yl = new int[50];
    Point mouseLoc = new Point(20, 20);
    Dugum baslangicDugumu = null;
    Dugum bitisDugumu = null;
    static Simulasyon ref;
    void addPoint() {
        try {
            xl[indis - 1] = Mouse.getLocX();
            yl[indis - 1] = Mouse.getLocY();
            indis++;
            xl[indis - 1] = Mouse.getLocX();
            yl[indis - 1] = Mouse.getLocY();
        } catch (Exception e) {
        }
    }

    void resetLine() {
        indis = 1;
    }

    public Simulasyon(int w, int h) {
        ref = this;

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        setPreferredSize(new Dimension(w, h));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Mouse.setClockLocation(e.getPoint());
                checkClick();
                switch (Mouse.secim) {
                    case Komponent:
                        component(e);
                        break;
                    case Baglanti:
                        baglan(e);
                        break;
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseLoc.x = e.getX();
                mouseLoc.y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (Mouse.durum == Mouse.durum.KomponentYerlestir) {
                    if (c != null) {
                        c.tx = e.getX() - Mouse.getClickX();
                        c.ty = e.getY() - Mouse.getClickY();
                    }
                }
                xl[indis - 1] = e.getX();
                yl[indis - 1] = e.getY();

                mouseLoc.setLocation(e.getX(), e.getY());
                Mouse.setMouseLocation(e.getX(), e.getY());
            }
        });

        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    void checkClick() {
        if (Mouse.durum == Mouse.Durum.KomponentYerlestir) {
            return;
        }
        for (Komponent i : Komponent.ciz) {
            if(i==null) return;
            if (i.getShape().intersects(new Rectangle(mouseLoc.x, mouseLoc.y, 2, 2))) {
                i.onClick();
            }
        }
    }

    void component(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && Mouse.durum == Mouse.Durum.KomponentYerlestir) {
            Mouse.durum = Mouse.Durum.sürükle;
            c = null;
            return;
        }
        if (Mouse.selectedComponent() != null) {
            String seciliKapi = Mouse.selectedComponent();
            c = DosyaOku.komponentSecimi(seciliKapi, mouseLoc.x, mouseLoc.y);

            if (Mouse.durum == Mouse.Durum.sürükle) {
                Mouse.durum = Mouse.Durum.KomponentYerlestir;
            } else if (Mouse.durum == Mouse.Durum.KomponentYerlestir) {
                Komponent.ciz.add(c);
                c = null;
                Mouse.durum = Mouse.Durum.sürükle;
            }
        }
    }

    void baglan(MouseEvent e) {
        if (baslangicDugumu != null) {
            addPoint();
        }
        for (Dugum node : Dugum.dugumler) {
            if (node.getBounds().contains(mouseLoc)) {
                if (baslangicDugumu == null) {
                    baslangicDugumu = node;
                    addPoint();
                } else {
                    bitisDugumu = node;
                    kablo = new BaglantiKablolari(xl, yl, indis, baslangicDugumu, bitisDugumu);
                    indis = 1;
                    baslangicDugumu = null;
                    resetLine();
                }
                break;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        gridCiz(g);
        sekilCiz(g);
        g2d = (Graphics2D) g;
        BaglantiKablolari.DrawAll(g2d);
        if (c != null) {
            c.draw(g2d);
        }
        GeneralPath cizgi = new GeneralPath(GeneralPath.WIND_EVEN_ODD, indis);
        cizgi.moveTo(xl[0], yl[0]);
        for (int index = 1; index < indis; index++) {
            cizgi.lineTo(xl[index], yl[index]);
        };
        g2d.draw(cizgi);
    }

    public void sekilCiz(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Komponent i : Komponent.ciz) {
            i.draw(g2d);
        }
    }

    public void gridCiz(Graphics g) {
        g.setColor(Color.gray);
        int h = this.getHeight();
        int w = this.getWidth();
            for (int x = 0; x < w; x += 24) {
                for (int y = 0; y < h; y += 24) {
                    g.drawRect(x, y, 24, 24);
            }
        }
    }
}

