package com.omerfurkanoflaz;
import java.awt.*;
import java.awt.geom.*; //Line2D için


import java.util.ArrayList;


public class IOBaglanti extends Komponent { //Komponent sınıfından extends edildi.
    int x,y;
    static Color color = Color.DARK_GRAY; //Baglantı çubuklarının rengi
    TypeEnum.InputOutput type;
    Line2D.Double sekil; //Bağlantı çubuklarını çizebilmek için Line2D sınıfından bir değişken
    Dugum node;
    Boolean state = null;
    Komponent comp;
    public IOBaglanti(int x, int y, TypeEnum.Yon yon, TypeEnum.InputOutput type, Komponent c) { //Consturctor tanımlaması
        comp = c;
        this.x = x; //parametre olarak gelen x değişkenini bu sınıfa ait olan x' e ata.
        this.y = y;//parametre olarak gelen y değişkenini bu sınıfa ait olan y'e ata.
        node = new Dugum(x + 20, y, this, type); //Dugum sınıfından nesne türet.(Bağlantı düğümünü çizmek için)
        this.type = type; //Input mu Output mu onun bilgisini ata.
        sekil = new Line2D.Double(x, y, x+20 , y);
    }
    public IOBaglanti(int x, int y, TypeEnum.Yon yon, TypeEnum.Konum konum, TypeEnum.InputOutput type, Komponent c) { //İkinci constructor tanımlasması
        comp = c;
        this.x = x; //parametre olarak gelen x değişkenini bu sınıfa ait olan x' e ata.
        this.y = y;//parametre olarak gelen y değişkenini bu sınıfa ait olan y'e ata.
        this.type = type;  //Input mu Output mu onun bilgisini ata.
        switch (konum) { //Parametre olarak gelen konum bilgisine göre
            case LEFT: //Left ise yani girişler ise o zaman bağlantı çubuğunun ve düğümün x değerini x-20 yap.
              sekil = new Line2D.Double(x-20, y, x, y);
              node = new Dugum(x-20, y, this, type);
              break;
            case RIGHT: //Right ise (çıkışlar için) x değerlerini x+20 yap.
                sekil = new Line2D.Double(x, y, x + 20, y);
                node = new Dugum(x + 20, y, this, type);
                break; } }
    void draw(Graphics2D g2d) { //draw() fonksiyonu ile bağlantı çubuklarını ve düğümleri çiz.
        BasicStroke kalinlik=new BasicStroke(3);
        g2d.setColor(color);
        g2d.setStroke(kalinlik);
        g2d.draw(sekil);
        node.draw(g2d);
    }
    Boolean getState() { //girişlerin aldığı değerleri update edebilek için updateState() fonksiyonunu çağır.
        if (type == TypeEnum.InputOutput.Input) {
            updateState();
            return state;
        } else {
            return comp.getState();
        }
    }


    void updateState() {
        state = node.getState();
    }
}
