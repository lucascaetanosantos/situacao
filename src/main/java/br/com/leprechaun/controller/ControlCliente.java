package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlCliente {

    Connection connection;
    PreparedStatement stmt;
    ResultSet rs;
    List<ModelCliente> listaCliente = new ArrayList<>();

    public ControlCliente() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public boolean verificaCliente(ModelCliente cliente) throws SQLException {
        String sql = "SELECT * FROM CLIENTE";
        stmt = connection.prepareStatement(sql);
        rs = stmt.executeQuery();
        stmt.close();
        return (!(rs.getString("nome_cliente").equals(cliente.getNomeCliente()))
                && rs.getInt("id") != cliente.getIdCliente());
    }

    public void cadastrar(ModelCliente cliente) throws SQLException {

        String sql = ""
                + "INSERT INTO CLIENTE(nome_cliente, email_cliente)"
                + "VALUES (?,?);";

        if (verificaCliente(cliente)) {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEmailCliente());
            stmt.executeQuery();
            stmt.close();
        } else {
            System.out.println("Cliente já existe");
        }

    }

    public List<ModelCliente> listar() {
        for (ModelCliente modelCliente : listaCliente) {
            ModelCliente cliente = new ModelCliente();
            cliente.setIdCliente(modelCliente.getIdCliente());
            cliente.setNomeCliente(modelCliente.getNomeCliente());
            cliente.setEmailCliente(modelCliente.getEmailCliente());
            listaCliente.add(cliente);
        }
        return listaCliente;
    }

    public void alterar(int id, String nome, String email) {
        String sql = ""
                + "UPDATE CLIENTE "
                + "SET "
                + "nome_cliente = ?, email_cliente = ?"
                + "WHERE id = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Algum erro na sintaxe update-Cliente: " + e);
        }

    }

    public void excluir(int id) {
        String sql = "DELETE FROM AUTOMOVEL WHERE AUT_ID = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro na instrução sql: " + e);
        }
    }

}
