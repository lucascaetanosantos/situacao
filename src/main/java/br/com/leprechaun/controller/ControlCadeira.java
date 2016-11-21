package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelCadeira;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlCadeira {

    Connection conexao;
    PreparedStatement stmt;
    ResultSet rs;
    public List<ModelCadeira> listaCadeira = new ArrayList<>();

    public ControlCadeira() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public boolean verificaCadeira(int cadeira, int fileira) throws SQLException {
        String sql = "SELECT * FROM CADEIRA;";
        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        stmt.close();
        return (rs.getInt("numero_cadeira") != cadeira
                && rs.getInt("numero_fileira") != fileira);
    }

    public void cadastrar(int cadeira, int fileira) throws SQLException {
        if (verificaCadeira(cadeira, fileira)) {
            String sql = ""
                    + "INSERT INTO CADEIRA(numero_cadeira, numero_fileira) "
                    + "values (?,?);";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cadeira);
            stmt.setInt(2, fileira);
            stmt.executeQuery();
            stmt.close();
        } else {
            System.out.println("Lugar ocupado, tente novamente");
        }
    }

    public List<ModelCadeira> getLista() throws SQLException {

        String sql = "SELECT * FROM cadeira";
        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            ModelCadeira modelCadeira = new ModelCadeira();
            modelCadeira.setIdCadeira(rs.getInt("id"));
            modelCadeira.setNumeroCadeira(rs.getInt("numero_cadeira"));
            modelCadeira.setNumeroFileira(rs.getInt("numero_fileira"));
            listaCadeira.add(modelCadeira);
        }
        return listaCadeira;
    }

}
