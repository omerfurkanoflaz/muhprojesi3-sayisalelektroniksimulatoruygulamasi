package com.omerfurkanoflaz;

import javax.swing.*;
import java.awt.event.*;
//Üst Menü classı
public class Menu extends JToolBar {

    public Menu(){ //Constructor tanımlaması Menu classından nesne türetildiği an çalışır.
        ImageIcon kapat = new ImageIcon("images/kapat.png"); //Butona ikon eklemek için
        JButton exitButton = new JButton(kapat); //Exit butonu
        exitButton.addActionListener(new ActionListener() { //Butonu dinle.Event oluştuğunda uygulamayı kapat.
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

    });

        ImageIcon dosya = new ImageIcon("images/dosya.png");
        JButton yeniDosya = new JButton(dosya);// Yeni dosya açma Butonu
        yeniDosya.setToolTipText("Yeni Dosya"); //Butonun üzerine gelince bilgi mesaj kutusu (Tooltip)
        yeniDosya.addActionListener(new ActionListener() { //Butonu dinle.Event oluştuğunda sayfadaki tüm bağlantıları devreleri sil.
            public void actionPerformed(ActionEvent event) {
                Komponent.ciz.clear();
                BaglantiKablolari.baglantilar.clear();
                Dugum.dugumler.clear();
            }
        });
        //Butonları sayfa üzerine ekleyebilmek için.
        this.add(yeniDosya);
        this.add(exitButton);
        this.setFloatable(false);
    }
}
