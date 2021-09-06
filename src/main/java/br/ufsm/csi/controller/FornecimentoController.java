package br.ufsm.csi.controller;

import br.ufsm.csi.dao.*;
import br.ufsm.csi.model.FornecimentoEmbalagem;
import br.ufsm.csi.model.FornecimentoMateriaPrima;
import br.ufsm.csi.model.Licitacao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("fornecimento-controller")
public class FornecimentoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FornecimentoEmbalagemDAO daofe = new FornecimentoEmbalagemDAO();
        FornecimentoMateriaPrimaDAO daofmp = new FornecimentoMateriaPrimaDAO();
        String retorno;
        String opcao = req.getParameter("opcao");
        int idL = Integer.parseInt(req.getParameter("idL"));
        req.setAttribute("fe", daofe.getFornecimentoEmbalagens(idL));
        req.setAttribute("fmp", daofmp.getFornecimentoMateriasPrimas(idL));

        req.setAttribute("fornecedores", new FornecedorDAO().getFornecedores());
        req.setAttribute("daof", new FornecedorDAO());
        req.setAttribute("materiasprimas", new MateriaPrimaDAO().getMateriasPrimas());
        req.setAttribute("daomp", new MateriaPrimaDAO());
        req.setAttribute("embalagens", new EmbalagemDAO().getEmbalagens());
        req.setAttribute("daoe", new EmbalagemDAO());

        if (opcao != null) {
            int idF = Integer.parseInt(req.getParameter("idF"));

            if (opcao.equals("excluirE")) {
                int idE = Integer.parseInt(req.getParameter("idE"));
                retorno = daofe.excluir(idL, idF, idE);
            } else if (opcao.equals("excluirMP")) {
                int idMP = Integer.parseInt(req.getParameter("idMP"));
                retorno = daofmp.excluir(idL, idF, idMP);
            } else if (opcao.equals("gravarE")) {
                FornecimentoEmbalagem fe = new FornecimentoEmbalagem();
                fe.setIdLicitacao(idL);
                fe.setIdFornecedor(idF);
                int idE = Integer.parseInt(req.getParameter("idE"));
                fe.setIdEmbalagem(idE);
                fe.setPreco(Double.parseDouble(req.getParameter("preco")));
                retorno = daofe.cadastrar(fe);
            } else if (opcao.equals("gravarMP")) {
                FornecimentoMateriaPrima fmp = new FornecimentoMateriaPrima();
                fmp.setIdLicitacao(idL);
                fmp.setIdFornecedor(idF);
                int idMP = Integer.parseInt(req.getParameter("idMP"));
                fmp.setIdMateriaPrima(idMP);
                fmp.setPreco(Double.parseDouble(req.getParameter("preco")));
                retorno = daofmp.cadastrar(fmp);
            } else {
                retorno = null;
            }
        } else {
            retorno = null;
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/fornecimentos.jsp");
        rd.forward(req, resp);
    }
}
