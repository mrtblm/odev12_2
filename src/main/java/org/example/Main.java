package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Main {
    public static void main(String[] args) {
        try {
            Connection baglanti = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10690672", "sql10690672"
                    , "m3cJaCCjtr");

            String ekleSorgu = "INSERT INTO ogrenciler (ad, soyad) VALUES (?, ?)";
            PreparedStatement ekleStatement = baglanti.prepareStatement(ekleSorgu);
            ekleStatement.setString(1,"Lale");
            ekleStatement.setString(2,"Fatma");


            int etkilenenSatirSayisi = ekleStatement.executeUpdate();
            System.out.println("Eklendi: " + etkilenenSatirSayisi + " satır");

            String sorgu = "SELECT * FROM ogrenciler";
            PreparedStatement preparedStatement = baglanti.prepareStatement(sorgu);

            ResultSet sonuclar = preparedStatement.executeQuery();


            while (sonuclar.next()) {
                int ogrenciID = sonuclar.getInt("ogrenciID");
                String ad = sonuclar.getString("ad");
                String soyad = sonuclar.getString("soyad");

                System.out.println("Öğrenci ID: " + ogrenciID + ", Ad: " + ad + ", Soyad: " + soyad);
            }


            //3 adet veri girişi sonrası 4.veriyi silmek için eklenen kod bloğu
            String silSorgu = "DELETE FROM ogrenciler WHERE ogrenciID = ?";
            PreparedStatement silStatement = baglanti.prepareStatement(silSorgu);
            silStatement.setInt(1, 4);
            int silinenSatirSayisi = silStatement.executeUpdate();
            System.out.println("Silindi: " + silinenSatirSayisi + " satır");


            ekleStatement.close();
            silStatement.close();
            baglanti.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } }
}