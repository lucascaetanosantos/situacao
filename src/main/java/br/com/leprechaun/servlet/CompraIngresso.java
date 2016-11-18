package br.com.leprechaun.servlet;

import br.com.leprechaun.controller.ControlCadeira;
import br.com.leprechaun.controller.ControlCliente;
import br.com.leprechaun.controller.ControlIngressos;
import br.com.leprechaun.controller.ControlJogos;
import br.com.leprechaun.controller.ControlSetor;
import br.com.leprechaun.model.ModelCadeira;
import br.com.leprechaun.model.ModelCliente;
import br.com.leprechaun.model.ModelJogos;
import br.com.leprechaun.model.ModelSetor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompraIngresso", urlPatterns = {"/CompraIngresso"})
public class CompraIngresso extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        int cadeira = Integer.parseInt(request.getParameter("cadeira"));
        int fileira = Integer.parseInt(request.getParameter("fileira"));
        String nomeCliente = request.getParameter("nomeCliente");
        String emailCliente = request.getParameter("emailCliente");
        int diaJogo = Integer.parseInt(request.getParameter("diaJogo"));
        String corSetor = request.getParameter("setor");

        ModelCadeira cadeiraModelSetor = null;
        ModelCliente clienteModelSetor = null;
        ModelJogos jogosModelSetor = null;
        ModelSetor setorModelSetor = null;

        try {
            //SETOR -----------------------------------------------------------
            ControlCadeira controlCadeira = new ControlCadeira();
            controlCadeira.cadastrar(cadeira, fileira);
            for (ModelCadeira modelCadeira : controlCadeira.listaCadeira) {
                if (modelCadeira.getNumeroCadeira() == cadeira
                        && modelCadeira.getNumeroFileira() == fileira) {
                    cadeiraModelSetor = modelCadeira;
                }
            }
            ControlSetor controlSetor = new ControlSetor();
            controlSetor.adicionar(corSetor, cadeiraModelSetor);
            for (ModelSetor modelSetor : controlSetor.listaSetor) {
                if (modelSetor.getCorSetor().equals(corSetor)) {
                    setorModelSetor = modelSetor;
                }
            }
            //CLIENTE ---------------------------------------------------------
            ControlCliente controlCliente = new ControlCliente();
            ModelCliente modelCliente = new ModelCliente();
            modelCliente.setNomeCliente(nomeCliente);
            modelCliente.setEmailCliente(emailCliente);
            controlCliente.cadastrar(modelCliente);
            for (ModelCliente clienteModel : controlCliente.listaCliente) {
                if (clienteModel.getNomeCliente().equals(nomeCliente)
                        && clienteModel.getEmailCliente().equals(emailCliente)) {
                    clienteModelSetor = clienteModel;
                }
            }
            //JOGOS -----------------------------------------------------------
            ModelJogos modelJogos = new ModelJogos();
            modelJogos.setDiaJogo(diaJogo);
            ControlJogos controlJogos = new ControlJogos();
            for (ModelJogos jogosControl : controlJogos.listaJogos) {
                if (jogosControl.getDiaJogo() == diaJogo) {
                    jogosModelSetor = jogosControl;
                }
            }
            //FINALIZANDO COMPRA ----------------------------------------------
            ControlIngressos controlIngressos = new ControlIngressos();
            controlIngressos.compra(setorModelSetor, clienteModelSetor, jogosModelSetor);

        } catch (SQLException ex) {
            System.out.println("Erro ao comprar ingresso: " + ex);
        }

    }

}
