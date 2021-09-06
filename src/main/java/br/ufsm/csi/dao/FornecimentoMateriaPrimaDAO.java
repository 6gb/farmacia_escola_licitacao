package br.ufsm.csi.dao;

import br.ufsm.csi.model.FornecimentoMateriaPrima;

import java.sql.*;
import java.util.ArrayList;

public class FornecimentoMateriaPrimaDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<FornecimentoMateriaPrima> getFornecimentoMateriasPrimas(int id) {
        ArrayList<FornecimentoMateriaPrima> fornecimentomateriasprimas = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select *, avg(preco) over (partition by idmateriaprima)"+
                        "from fornecimentomateriaprima where idlicitacao=?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                FornecimentoMateriaPrima f = new FornecimentoMateriaPrima();
                f.setIdLicitacao(this.resultSet.getInt("idlicitacao"));
                f.setIdFornecedor(this.resultSet.getInt("idfornecedor"));
                f.setIdMateriaPrima(this.resultSet.getInt("idmateriaprima"));
                f.setPreco(this.resultSet.getDouble("preco"));
                f.setMedia(this.resultSet.getDouble("avg"));
                fornecimentomateriasprimas.add(f);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return fornecimentomateriasprimas;
    }

    public String cadastrar(FornecimentoMateriaPrima f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "insert into fornecimentomateriaprima(" +
                            "idlicitacao, idfornecedor, idmateriaprima, preco" +
                        ") values (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getIdLicitacao());
            this.preparedStatement.setInt(2, f.getIdFornecedor());
            this.preparedStatement.setInt(3, f.getIdMateriaPrima());
            this.preparedStatement.setDouble(4, f.getPreco());
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }

    public String excluir(int idL, int idF, int idMP) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "delete from fornecimentomateriaprima where " +
                    "idlicitacao=? and idfornecedor=? and idmateriaprima=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, idL);
            this.preparedStatement.setInt(2, idF);
            this.preparedStatement.setInt(3, idMP);
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
}
