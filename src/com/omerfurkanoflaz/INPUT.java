package com.omerfurkanoflaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class INPUT extends Komponent {
    Ellipse2D.Double sekil;
    String deger = "0";
    boolean kosul = false;

    public INPUT(int x, int y) { //Constructor tanımlaması.
        this.x = x; //parametre olarak gelen x değerini bu sınıfa ait olan x değerine atadık.
        this.y = y; //parametre olarak gelen y değerini bu sınıfa ait oln y değerine atadık.
        sekil = new Ellipse2D.Double(x, y, 40, 40); //input için bir 40x40'lık bir daire çiz.
        outputs.add(new IOBaglanti(x + 40, y + 20, TypeEnum.Yon.Horizontal, TypeEnum.InputOutput.Output, this));//inputun çıkış bağlantısını çiz.
    }
    Color renk = Color.YELLOW; //Dairenin rengini sarı olarak belirledim.
    Color yaziRengi = Color.BLACK;//İçerisinde yazan değerin rengi siyah
    Font font = new Font("Arial", Font.BOLD, 28);// Yazı dairenin içerisinde küçük kaldığı için font tanımladım.

    Ellipse2D.Double getShape() {
        return sekil;
    }

    void degistir() { //Toggle mantığı ile input'a tıkladıkça değerinin değişmesi için fonksiyon tanımladım. kosul false ise değeri 0 yap.
        if (kosul) {
            yaziRengi = Color.BLUE;
            kosul = false;
            deger = "0";
        } else { //Koşul true ise değeri  1 yap ve yazı rengini kırmızı yap.
            yaziRengi = Color.RED;
            kosul = true;
            deger = "1";
        }
    }

    Boolean getState() {
        return kosul;
    }

    void onClick() { //onClick fonksiyonu çağırıldığında degistir fonksiyonunu çalıştır.
        degistir();
    }

    void draw(Graphics2D g2d) {
        g2d.translate(tx, ty);
        baglantilariCiz(g2d); //çıkış bağlantısını çiz.
        g2d.setColor(renk);
        g2d.fill(sekil);
        g2d.setColor(yaziRengi);
        g2d.setFont(font);
        g2d.drawString(deger, x + 13, y + 29); //İçeride yazan değeri ortalamak için
    }
}
