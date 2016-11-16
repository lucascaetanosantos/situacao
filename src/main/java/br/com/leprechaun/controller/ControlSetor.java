package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelCadeira;
import br.com.leprechaun.model.ModelSetor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlSetor {

    Connection connection;
    List<ModelSetor> listaSetor = new ArrayList<>();
    PreparedStatement stmt;
    ResultSet rs;

    public ControlSetor() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionar(String corNovoSetor, ModelCadeira cadeira) {

        String sql = "INSERT INTO SETOR(cor_setor, id_cadeira) VALUES (?,?);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, corNovoSetor);
            stmt.setInt(2, cadeira.getIdCadeira());
            stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar novo Setor: " + e);
        }

    }

    public List<ModelSetor> listar() throws SQLException {

        String sql = "SELECT * FROM SETOR"
                + "INNER JOIN CADEIRA";
        stmt = connection.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            ModelSetor modelSetor = new ModelSetor();
            modelSetor.setIdSetor(rs.getInt("id"));
            modelSetor.setCorSetor(rs.getString("cor_setor"));

            ModelCadeira modelCadeira = new ModelCadeira();
            modelCadeira.setIdCadeira(rs.getInt("id_cadeira"));
            modelCadeira.setNumeroCadeira(rs.getInt("numero_cadeira"));
            modelCadeira.setNumeroFileira(rs.getInt("numero_fileira"));
            modelSetor.setModelCadeira(modelCadeira);
            listaSetor.add(modelSetor);
        }
        return listaSetor;
    }

    public void excluir(int id) {
        for (ModelSetor modelSetor : listaSetor) {
            if (modelSetor.getIdSetor() == id) {
                listaSetor.remove(modelSetor);
                break;
            }
        }
    }

}
