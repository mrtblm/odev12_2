package org.example;
import java.sql.*;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;

public class Main {
    public static void main(String[] args) {

        // MySQL bağlantı bilgileri
        String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10696015";
        String kullaniciAdi = "sql10696015";
        String sifre = "zXJrHTNYj8";

        // JDBI
        Jdbi jdbi = Jdbi.create(url, kullaniciAdi, sifre);
        try (Handle handle = jdbi.open()) {
            handle.execute("INSERT INTO ogrenciler (ad,soyad) VALUES (?,?)", "Murat","BILIM");
            handle.execute("DELETE FROM ogrenciler (ogrenciID) VALUES (?)", 1);

            String result = handle.createQuery("SELECT * FROM ogrenciler WHERE ogrenciID = :id")
                    .bind("id", 1)
                    .mapTo(String.class)
                    .one();
            System.out.println("Result: " + result);
        } catch (StatementException e) {
            e.printStackTrace();
        }
        }


    }
}