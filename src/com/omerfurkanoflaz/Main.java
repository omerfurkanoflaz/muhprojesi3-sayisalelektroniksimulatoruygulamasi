package com.omerfurkanoflaz;

import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {

    public Main(){
        //Arayüz Sınıfı
        this.setTitle("Sayısal Elektronik Simülasyonu"); //Uygulama başlığı
        this.setSize(new Dimension(1900, 1300)); //Uygulamanın genişlik ve yüksekliğini belirledim.
        this.add(new Menu(), BorderLayout.NORTH); //Menüyü üst tarafa konumla.
        this.add(new AltMenu(), BorderLayout.LINE_END);//İşlem menüsünü sağ tarafa konumla.
        Simulasyon sheet = new Simulasyon(1100,1000); //Devrelerinin tasarlandığı kısmın genişli ve yüksekliği
        JScrollPane thePane = new JScrollPane(sheet);
        this.add(thePane, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);//Uygualamanın ekranın ortasında açılması için.
        this.setVisible(true); //Görünür olması için.
    }

}

