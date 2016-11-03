package br.com.leprechaun.util;

import br.com.leprechaun.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteDB {

    public static void main(String[] args) throws SQLException {
        Connection conn = new ConnectionFactory().getConnection();
        String sql = "CREATE TABLE \"tabela\" ( \n"
                + "\"coluna1\" varchar(40), \n"
                + "\"coluna2\" varchar(40) \n"
                + ") ; ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.execute();
    }
}
