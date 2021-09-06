package br.ufsm.csi.controller;

import br.ufsm.csi.dao.MateriaPrimaDAO;
import br.ufsm.csi.model.MateriaPrima;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("materiaprima-controller")
public class MateriaPrimaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MateriaPrimaDAO dao = new MateriaPrimaDAO();
        String retorno;
        String opcao = req.getParameter("opcao");
        req.setAttribute("materiasprimas", new MateriaPrimaDAO().getMateriasPrimas());

        if (opcao != null) {
            if (opcao.equals("excluir")) {
                int id = Integer.parseInt(req.getParameter("id"));
                MateriaPrima mp = new MateriaPrimaDAO().getMateriaPrima(id);
                retorno = dao.excluir(mp);
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(req.getParameter("id"));
                MateriaPrima mp = new MateriaPrimaDAO().getMateriaPrima(id);
                req.setAttribute("materiaprima", mp);
                retorno = dao.editar(mp);
            } else {
                int id = Integer.parseInt(req.getParameter("id"));
                String descricao = req.getParameter("descricao");
                String cas = req.getParameter("cas");
                String dcb = req.getParameter("dcb");
                String dci = req.getParameter("dci");

                MateriaPrima materiaprima = new MateriaPrima();
                materiaprima.setDescricao(descricao);
                materiaprima.setCas(cas);
                materiaprima.setDcb(dcb);
                materiaprima.setDci(dci);

                if (id > 0) {
                    materiaprima.setId(id);
                    retorno = dao.editar(materiaprima);
                } else {
                    retorno = dao.cadastrar(materiaprima);
                }
            }
        } else {
            retorno = null;
        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/materiasprimas.jsp");
        rd.forward(req, resp);
    }
}
