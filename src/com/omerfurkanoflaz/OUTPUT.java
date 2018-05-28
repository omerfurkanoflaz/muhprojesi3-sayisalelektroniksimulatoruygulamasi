package com.omerfurkanoflaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

public class OUTPUT extends Komponent { //Oluşturduğumuz komponent sınıfından extends ediyoruz.
    Font font = new Font("Serif", Font.PLAIN, 30); //Output içerisinde yazan değerin fontu küçük kaldığı için font tanımladım.
    String deger = "0"; //Output içerisinde değeri yazdırmak için string bir değişken tanımladım.
    //İlk başta dairenin renginin koyu gri yazının beyaz olması için renk tanımlaması yaptım.
    Color sekil = Color.DARK_GRAY;
    Color yaziRengi = Color.WHITE;
    //Graphics2D kütüphaneleri ile 2 boyutlu şekilleri çizebilir bunlarla işlemler yapabiliriz.Burada da daire çizmek için Ellipse2D kullandım.
    Ellipse2D daire;
    Boolean durum = null;
    public OUTPUT(int x, int y) { //Constructor tanımlaması.
        this.x = x; //Parametre olarak gelen x değerini bu sınıfa ait olan x değerine atadım.
        this.y = y; //Parametre olarak gelen y değerini bu sınıfa ait olan y değerine atadım.
        daire = new Ellipse2D.Double(x, y, 32, 32); //daire nesnesini new ile oluşturdum ve yükseklik genislik değerlerini aynı zamanda konumunun x ve y olacağını söyledim.
        inputs.add(new IOBaglanti(x, y + 16, TypeEnum.Yon.Horizontal, TypeEnum.Konum.LEFT, TypeEnum.InputOutput.Input, this)); //output'un giriş bağlantı çubuğunu çiz.Çubuk sol tarafta ve yatayda olacak.ve tipi input
    }
    Ellipse2D getShape() {
        return daire;
    }
    void guncelDeger() { //Baglantılar ve devre tasarımı değerler değiştikçe sonuçta değişecek bu yüzden fonksiyon tanımladım.
        durum = inputs.get(0).getState();
        if (durum == null) { // eğer null geliyorsa değeri ? yap.
            yaziRengi = Color.WHITE;
            durum = false;
            deger = "?";
        } else if (durum == true) { //Eğer true ise daireyi yeşil yap.değeri de 1 yap.
            sekil=Color.GREEN;
            yaziRengi = Color.WHITE;
            durum = true;
            deger = "1";
        } else { // eğer false ise daire kırmızı yazı da 0 olsun.
            sekil=Color.RED;
            yaziRengi = Color.BLACK;
            durum = false;
            deger = "0";
        } }
    void draw(Graphics2D g2d) {
        g2d.translate(tx, ty);
        baglantilariCiz(g2d);
        g2d.setColor(sekil);
        g2d.fill(daire);
        guncelDeger();
        g2d.setColor(yaziRengi);
        g2d.setFont(font);
        g2d.drawString(deger, x + 10, y + 25);//Dairenin içerisindeki değeri ortalamak için.
        g2d.translate(0, 0);
    }
}
