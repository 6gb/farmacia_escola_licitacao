package br.ufsm.csi.controller;

import br.ufsm.csi.dao.FornecedorDAO;
import br.ufsm.csi.model.Fornecedor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("fornecedor-controller")
public class FornecedorController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FornecedorDAO dao = new FornecedorDAO();
        String retorno;
        String opcao = req.getParameter("opcao");
        req.setAttribute("fornecedores", new FornecedorDAO().getFornecedores());

        if (opcao != null) {
            if (opcao.equals("excluir")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Fornecedor f = new FornecedorDAO().getFornecedor(id);
                retorno = dao.excluir(f);
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Fornecedor f = new FornecedorDAO().getFornecedor(id);
                req.setAttribute("fornecedor", f);
                retorno = dao.editar(f);
            } else {
                int id = Integer.parseInt(req.getParameter("id"));
                String cnpj = req.getParameter("cnpj");
                String nome = req.getParameter("nome");
                String endereco = req.getParameter("endereco");
                String telefone = req.getParameter("telefone");
                String email = req.getParameter("email");
                String sicaf = req.getParameter("sicaf");

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(cnpj);
                fornecedor.setNome(nome);
                fornecedor.setEndereco(endereco);
                fornecedor.setTelefone(telefone);
                fornecedor.setEmail(email);
                fornecedor.setSicaf(sicaf);

                if (id > 0) {
                    fornecedor.setId(id);
                    retorno = dao.editar(fornecedor);
                } else {
                    retorno = dao.cadastrar(fornecedor);
                }
            }
        } else {
            retorno = null;
        }

        req.setAttribute("retorno", retorno);
        RequestDispatcher rd = req.getRequestDispatcher("/fornecedores.jsp");
        rd.forward(req, resp);
    }
}
