package br.com.leprechaun.connection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionFactory {

    public Connection getConnection(){
        try {
            Connection conn;
            /*Class.forName("org.postgresql.Driver");
            String host = "ec2-54-243-212-72.compute-1.amazonaws.com";
            String port = "5432";
            String database = "d80krriuo99dnj";
            String url = "jdbc:postgresql://"+host+":"+port+"/"+database;
            String user = "rerogngiyenabv";
            String pass = "ALbvA72Ni9BSJir5dx6l5UiMTR";
            conn = DriverManager.getConnection(url, user, pass );*/
            
            Class.forName("com.mysql.jdbc.Driver");
            String host = "192.68.0.62";
            String port = "3306";
            String database = "estadio";
            String url = "jdbc:mysql://"+host+":"+port+"/"+database;
            String user = "root";
            String pass = "123456";
            conn = DriverManager.getConnection(url, user, pass );
            return conn;
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro no driver!\n"+ex);
            return null;
        } catch (SQLException ex){
            System.err.println("Erro no banco de dados!\n"+ex);
            return null;
        }
        
    }
}
