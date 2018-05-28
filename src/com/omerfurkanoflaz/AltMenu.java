package com.omerfurkanoflaz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Kullanıcının işlem seçme menüsü
public class AltMenu extends JPanel { //AltMenu isminde class oluşturup bunu JPanel classından türettim.

    JButton baglanti; //Kullanicinin bağlantı seçeneğine geçebilmesi için bir buton oluşturdum.
    JButton component; //Kullanıcının komponent seçmesi için buton oluşturdum.
    static AltMenu instance; //AltMenu classından bir örnek oluşturdum.

    public AltMenu() {
        instance = this;
        this.setLayout(new BorderLayout()); //Bir border layout oluşturdum.Sağ tarafa konumlandırabileceğim bir araç kutusu tasarlamak için.
        JToolBar toolbox = new JToolBar(JToolBar.HORIZONTAL); //Yatay Menü çubuğu
        //Toolbar'da yer alacak butonları new ile oluşturup bunlara ikonlar ekledim.
        ImageIcon komponent = new ImageIcon("images/devre.png");
        component = new JButton("Komponent seç",komponent);
        ImageIcon baglantiicon=new ImageIcon("images/baglanti2.png");
        baglanti=new JButton("Bağlantıları Gerçekleştir",baglantiicon);
        component.addActionListener(new ActionListener() { //Komponent butonunda event gerçekleşirse
            public void actionPerformed(ActionEvent e) {
                Mouse.secim = Mouse.Secim.Komponent;  //Seçili olanın Komponent olduğunu bildirdim.Artık komponent seçme işlemine geçebiliriz.
                KomponentAktif();//KomponentAktif fonksiyonunu çağırarak butonun arka plan rengini değiştirdim.
            }
        });
        baglanti.addActionListener(new ActionListener() { //Bağlantı gerçekleştirme butonuna event gerçekleşirse
            public void actionPerformed(ActionEvent e) {
                Mouse.secim = Mouse.Secim.Baglanti;  //Seçili olanın bağlantı olduğunu bildirdim.Artık bğlantıları gerçekleştirebiliriz.
                BaglantiAktif(); //BaglantiAktif() fonksiyonunu çağırarak bağlantı butonunu aktif hale getirdim.
            }
        });
        //toolbox menü çubuğuna butonlarımı ekliyorum.
        toolbox.add(component);
        toolbox.add(baglanti);

        //toolbox'ı alt kısma sabitliyorum.
        this.add(toolbox,BorderLayout.SOUTH);
        this.add(new Toolbox());


    }
    Color aktifButon = new Color(182,220,234); //Seçilen butonun arkaplan rengini belirledim.
    Color pasifButon = new Color(49, 109, 194); //Pasif kalan butonun arka plan rengi.
    void KomponentAktif() {
        component.setBackground(aktifButon);
        baglanti.setBackground(pasifButon);
    }

    void BaglantiAktif(){
        component.setBackground(pasifButon);
        baglanti.setBackground(aktifButon);
    }
}
