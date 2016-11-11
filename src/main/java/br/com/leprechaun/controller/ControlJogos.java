
package br.com.leprechaun.controller;

import br.com.leprechaun.connection.ConnectionFactory;
import br.com.leprechaun.model.ModelJogos;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ControlJogos {
    
    Connection connection;
    List<ModelJogos> listaJogos = new ArrayList<>();
    
    public ControlJogos(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionar(){
        
    }
    
    public List<ModelJogos> listar(){
        for (ModelJogos jogos : listaJogos) {
            
        }
    }
    
    public void excluir(){
        
    }
    
}
