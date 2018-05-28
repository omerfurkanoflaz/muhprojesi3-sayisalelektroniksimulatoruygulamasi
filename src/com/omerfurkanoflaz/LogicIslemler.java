package com.omerfurkanoflaz;

import java.util.ArrayList;

public class LogicIslemler {

    public static Boolean AND(ArrayList<IOBaglanti> inputs) { // IOBaglanti classı tipinde liste bekleyen AND fonksiyonu  girilen input değerlerini alıp bunlara göre çıkış değeri üretmek için
        Boolean kosul;
        for (IOBaglanti degisken : inputs) { //Listedeki input değerlerini alıp IOBaglanti tipindeki degisken e tek tek atıyorum.
            kosul = degisken.getState(); //degiskenin değerini alıp kosul değikenine atıyorum.
            if (kosul == null) { //Eğer deger null ise true döndürüyorum. Çünkü zaten benim için önemli olan false değeri bulmak. Bir tane false değeri sonucu false yapmaya yeter.
                kosul = true;
            }
            if (kosul == false) { //Eğer kosul değişkeni false ise false döndür.
                return false;
            }
        }
        return true;
    }

    public static Boolean OR(ArrayList<IOBaglanti> inputs) { // IOBaglanti classı tipinde liste bekleyen OR fonksiyonu.  Girilen input değerlerini alıp bunlara göre çıkış değeri üretmek için
        Boolean kosul;
        for (IOBaglanti degisken : inputs) { //Listedeki inputları tek tek con değişenine atıyorum.
            kosul = degisken.getState(); //degisken değişkeni IOBaglanti tipinde olduğu için getState() fonksiyonuyla değeri alıp koşul değişkenine atıyorum.
            if (kosul == null) { //Eğer koşul değişkeni null değer döndürüyorsa false döndür. Amacımız true bulmak.
                kosul = false;
            }
            if (kosul == true) { //Eğer kosul değişkeninin değeri true ise true döndür.
                return true;
            }
        }
        return false;
    }
    public static Boolean NOT(Boolean giris) { //Boolean parametre alan NOT classı
        if (giris == null) return null;
        if (giris == false) { //İnput değeri 0 girilmişse 1 döndür.
            return true;
        } else { //İnput değeri 1 girilmişse 0 döndür.
            return false;
        }
    }

    public static Boolean XOR(ArrayList<IOBaglanti> inputs) {
        Boolean kosul;
        int sayac = 0;
        for (IOBaglanti con : inputs) {
            kosul = con.getState();
            if (kosul == null) {
                kosul = false;
            }
            if (kosul == true) { //Burada önemli olan iki girişinde aynı değer olup olmadığını kontrol etmek. İkisi de aynı değer ise false değilse true döndürmesi gerekir. O yüzden true değeri sayısını alıp
                sayac++;        //Eğer değer 2 çıkarsa o zaman false döndürmeli. Bunu fale eğerlerinin sayısını hesaplayarak da yapabiliriz.
            }
        }
        if (sayac % 2 == 0) { //Sayının iki olup olmadığını mod alarak kontrol ettim.
            return false;
        }
        return true;
    }
}