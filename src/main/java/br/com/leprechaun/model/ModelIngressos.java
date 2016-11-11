
package br.com.leprechaun.model;

import java.util.Date;

public class ModelIngressos {
    
    Date dataHora;
    ModelSetor modelSetor;
    ModelCliente modelCliente;
    ModelJogos modelJogos;
    int idIngressos;

    public int getIdIngressos() {
        return idIngressos;
    }

    public void setIdIngressos(int idIngressos) {
        this.idIngressos = idIngressos;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public ModelSetor getModelSetor() {
        return modelSetor;
    }

    public void setModelSetor(ModelSetor modelSetor) {
        this.modelSetor = modelSetor;
    }

    public ModelCliente getModelCliente() {
        return modelCliente;
    }

    public void setModelCliente(ModelCliente modelCliente) {
        this.modelCliente = modelCliente;
    }

    public ModelJogos getModelJogos() {
        return modelJogos;
    }

    public void setModelJogos(ModelJogos modelJogos) {
        this.modelJogos = modelJogos;
    }
    
}
