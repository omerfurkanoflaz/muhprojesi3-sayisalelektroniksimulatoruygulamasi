package com.omerfurkanoflaz;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//Komponent listesini eklemek ve seçilen komponentin önizlemesini göstermek için
public class Toolbox extends JPanel {
    static JPanel onIzleme; //Komponentleri göstermek için.
    static Vector<String> KomponentListesi = new Vector<>(0);
    static JList list; //Kompomentleri listelemek için.
    public Toolbox() {
        onIzleme = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                String devre = Mouse.selectedComponent();
                if (devre != null) {//bir komponent seçildiyse
                    DosyaOku.komponentSecimi(devre, 130, 105).draw(g2d); //Seçili olan devreyi DosyaOku sınıfından çağırıp onunda o devrenin sınıfını çağırmasını sağladım.
                } }
        };
        onIzleme.setBackground(Color.white);
        this.add(onIzleme);
        list = new JList(KomponentListesi);
        JScrollPane pane = new JScrollPane(list);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String name = (String) list.getSelectedValue();
                    Mouse.setComponent(name);
                    Mouse.secim = Mouse.secim.Komponent;
                    AltMenu.instance.BaglantiAktif();
                     onIzleme.repaint();
                } }
        });
        listele();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(80, 40)));
        this.add(pane);
        this.setBackground(Color.white); }
    JScrollPane listele() {//komponentleri listeleme
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        for(int i=0;i<8;i++){
            Toolbox.listeyeEkle((String) DosyaOku.dizi[i]);
        }
        return scrollPane;
    }


    static void listeyeEkle(String s) {
        KomponentListesi.add(s);

    }
}
