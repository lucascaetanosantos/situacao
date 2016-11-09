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
    List<ModelCadeira> listaCadeira = new ArrayList<>();

    public ControlCadeira() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public boolean verificaCadeira(int cadeira, int fileira) throws SQLException {
        String sql = "SELECT * FROM CADEIRA;";
        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        return (rs.getInt("") == cadeira && rs.getInt("") == fileira);
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
        } else {
            System.out.println("Lugar ocupado, tente novamente");
        }
    }

    public List<ModelCadeira> listar() {
        for (ModelCadeira cadeira : listaCadeira) {
            ModelCadeira modelCadeira = new ModelCadeira();
            cadeira.setNumeroCadeira(modelCadeira.getNumeroCadeira());
            cadeira.setNumeroFileira(modelCadeira.getNumeroFileira());
            listaCadeira.add(cadeira);
        }
        return listaCadeira;
    }

    public void excluir(int idCadeira) {
        for (ModelCadeira modelCadeira : listaCadeira) {
            if (modelCadeira.getIdCadeira() == idCadeira) {
                listaCadeira.remove(rs);
            }
        }
    }

}
