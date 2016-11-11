
package br.com.leprechaun.model;

public class ModelSetor {
    
    String corSetor;
    ModelCadeira modelCadeira;
    int idSetor;

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getCorSetor() {
        return corSetor;
    }

    public void setCorSetor(String corSetor) {
        this.corSetor = corSetor;
    }

    public ModelCadeira getModelCadeira() {
        return modelCadeira;
    }

    public void setModelCadeira(ModelCadeira modelCadeira) {
        this.modelCadeira = modelCadeira;
    }
    
}
