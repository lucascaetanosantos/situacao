package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelCliente;
import br.com.leprechaun.model.ModelIngressos;
import br.com.leprechaun.model.ModelJogos;
import br.com.leprechaun.model.ModelSetor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ControlIngressos {

    Connection connection;
    PreparedStatement stmt;

    public ControlIngressos() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void compra(ModelSetor setor, ModelCliente cliente, ModelJogos jogos) {
        String sql = ""
                + "INSERT INTO ingressos(id_setor, id_cliente, id_jogos)"
                + "VALUES (?,?,?);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, setor.getIdSetor());
            stmt.setInt(2, cliente.getIdCliente());
            stmt.setInt(3, jogos.getIdJogo());
            stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Erro sql ao comprar ingresso: " + ex);
        }

    }

}
