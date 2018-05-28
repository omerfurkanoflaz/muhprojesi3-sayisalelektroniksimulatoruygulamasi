package com.omerfurkanoflaz;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DosyaOku {

    static String[] dizi; //String dizi tanımlaması

    public static void main(String[] args) {
        devreElemanlari(); //devreElemanlari() fonk. çağırma
        Main main = new Main(); //Main classından nesne oluştuğu an constructorları çalışacaktır.
    }
    public static void devreElemanlari() {
        try {
            File file = new File("Kapılar.txt"); //Kapılar.txt dosyasını bul.
            BufferedReader br = new BufferedReader(new FileReader(file)); //Dosyayı oku.
            String satir;
            List liste = new ArrayList<String>(); //txt dosyasındaki satırları saklamak için string türünde liste
            while ((satir = br.readLine()) != null) { //txt dosyasını boş satıra gelene dek oku ve satir değişkenine at.
                liste.add(satir);//satir değişkenindeki değeri listeye ekle.
            }
            ;
            int row = liste.size(); //Listenin uzunluğunu al ve row değişkenine at.
            dizi = new String[row]; //diziyi new anahtar kelimesiyle oluştur ve dizi uzunluğunu belirle.
            for (int i = 0; i < row; i++) {
                dizi[i] = liste.get(i).toString();//listedeki devre elemanlarını dizinin indislerine ekle.
            }
            br.close(); //BufferedReader'ı kapat.
        } catch (Exception e) { //Okuma sırasında hata ile karşılaşırsan hata mesajı göster.
            System.out.println("Dosya okunurken hata oluştu.");
        }
    }
    //switch case ile seçili olana göre classı çağır.
    static Komponent komponentSecimi(String seciliKapi, int x, int y) {

        Komponent k = null;
        switch (seciliKapi) {
            case "2 Girisli AND Kapisi":
                k = new AND(x, y);
                break;
            case "3 Girisli AND Kapisi":
                k = new AND(x, y, 3);
                break;
            case "2 Girisli OR Kapisi":
                k = new OR(x, y);
                break;
            case "3 Girisli OR Kapisi":
                k = new OR(x, y, 3);
                break;
            case "NOT":
                k = new NOT(x, y);
                break;
            case "2 Girisli XOR Kapisi":
                k = new XOR(x, y);
                break;
            case "INPUT":
                k = new INPUT(x, y);
                break;
            case "OUTPUT":
                k = new OUTPUT(x, y);
                break;
        }
        return k;
    }
}

