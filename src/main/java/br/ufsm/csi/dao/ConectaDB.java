package br.ufsm.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://postgres:5432/farmacia_escola_licitacao";
    private static final String USER = "java";
    private static final String PASSWORD = "pgjdbc";

    public Connection getConexao() {
        Connection conn = null;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
