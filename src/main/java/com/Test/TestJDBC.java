package com.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {

    public static void main(String argv[]) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionconcerts", "root", "");
            System.out.println("Connexion JDBC OK");

            Statement req = conn.createStatement();
            ResultSet res = req.executeQuery("select * from utilisateur");

            System.out.println("La liste des utilisateurs : ");
            while (res.next())
                System.out.println(" - " + res.getString(2));
        }
        catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace();
        }
    }
}