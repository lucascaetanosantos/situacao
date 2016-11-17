package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelJogos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlJogos {

    Connection connection;
    List<ModelJogos> listaJogos = new ArrayList<>();
    PreparedStatement stmt;
    ResultSet rs;

    public ControlJogos() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionar(ModelJogos jogos) {
        String sql = "INSERT INTO JOGOS(dia_jogo) VALUES (?);";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar dia: " + e);
        }
    }

    public List<ModelJogos> getLista() throws SQLException {

        String sql = "SELECT * FROM jogos";
        stmt = connection.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            ModelJogos modelJogos = new ModelJogos();
            modelJogos.setIdJogo(rs.getInt("id"));
            modelJogos.setDiaJogo(rs.getInt("dia_jogo"));
            listaJogos.add(modelJogos);
        }
        return listaJogos;
    }

    public void excluir(int id) {
        for (ModelJogos jogos : listaJogos) {
            if (id == jogos.getIdJogo()) {
                listaJogos.remove(jogos);
                break;
            }
        }
    }

}
