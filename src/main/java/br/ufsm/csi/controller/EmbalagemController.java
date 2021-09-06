package br.ufsm.csi.controller;

import br.ufsm.csi.dao.EmbalagemDAO;
import br.ufsm.csi.model.Embalagem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("embalagem-controller")
public class EmbalagemController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmbalagemDAO dao = new EmbalagemDAO();
        String retorno;
        String opcao = req.getParameter("opcao");
        req.setAttribute("embalagens", new EmbalagemDAO().getEmbalagens());

        if (opcao != null) {
            if (opcao.equals("excluir")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Embalagem e = new EmbalagemDAO().getEmbalagem(id);
                retorno = dao.excluir(e);
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Embalagem e = new EmbalagemDAO().getEmbalagem(id);
                req.setAttribute("embalagem", e);
                retorno = dao.editar(e);
            } else {
                int id = Integer.parseInt(req.getParameter("id"));
                String descricao = req.getParameter("descricao");
                String material = req.getParameter("material");
                int capacidade = Integer.parseInt(req.getParameter("capacidade"));

                Embalagem embalagem = new Embalagem();
                embalagem.setDescricao(descricao);
                embalagem.setMaterial(material);
                embalagem.setCapacidade(capacidade);

                if (id > 0) {
                    embalagem.setId(id);
                    retorno = dao.editar(embalagem);
                } else {
                    retorno = dao.cadastrar(embalagem);
                }
            }
        } else {
            retorno = null;
        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/embalagens.jsp");
        rd.forward(req, resp);
    }
}
