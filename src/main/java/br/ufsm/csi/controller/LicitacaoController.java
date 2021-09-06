package br.ufsm.csi.controller;

import br.ufsm.csi.dao.LicitacaoDAO;
import br.ufsm.csi.model.Licitacao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("licitacao-controller")
public class LicitacaoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LicitacaoDAO dao = new LicitacaoDAO();
        String retorno;
        String opcao = req.getParameter("opcao");
        req.setAttribute("licitacoes", new LicitacaoDAO().getLicitacoes());

        if (opcao != null) {
            if (opcao.equals("excluir")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Licitacao l = new LicitacaoDAO().getLicitacao(id);
                retorno = dao.excluir(l);
            } else {
                retorno = dao.cadastrar();
            }
        } else {
            retorno = null;
        }
        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/licitacoes.jsp");
        rd.forward(req, resp);
    }
}
