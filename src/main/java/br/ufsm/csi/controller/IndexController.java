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

@WebServlet("")
public class IndexController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("licitacoes", new LicitacaoDAO().getLicitacoes());
        RequestDispatcher rd = req.getRequestDispatcher("/licitacoes.jsp");
        rd.forward(req, resp);
    }
}
