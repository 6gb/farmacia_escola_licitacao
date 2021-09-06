package br.ufsm.csi.dao;

import br.ufsm.csi.model.FornecimentoEmbalagem;

import java.sql.*;
import java.util.ArrayList;

public class FornecimentoEmbalagemDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<FornecimentoEmbalagem> getFornecimentoEmbalagens(int id) {
        ArrayList<FornecimentoEmbalagem> fornecimentoembalagens = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select *, avg(preco) over (partition by idembalagem)"+
                        "from fornecimentoembalagem where idlicitacao=?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                FornecimentoEmbalagem f = new FornecimentoEmbalagem();
                f.setIdLicitacao(this.resultSet.getInt("idlicitacao"));
                f.setIdFornecedor(this.resultSet.getInt("idfornecedor"));
                f.setIdEmbalagem(this.resultSet.getInt("idembalagem"));
                f.setPreco(this.resultSet.getDouble("preco"));
                f.setMedia(this.resultSet.getDouble("avg"));
                fornecimentoembalagens.add(f);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return fornecimentoembalagens;
    }

    public String cadastrar(FornecimentoEmbalagem f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "insert into fornecimentoembalagem(" +
                            "idlicitacao, idfornecedor, idembalagem, preco" +
                        ") values (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getIdLicitacao());
            this.preparedStatement.setInt(2, f.getIdFornecedor());
            this.preparedStatement.setInt(3, f.getIdEmbalagem());
            this.preparedStatement.setDouble(4, f.getPreco());
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }

    public String excluir(int idL, int idF, int idE) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "delete from fornecimentoembalagem where " +
                    "idlicitacao=? and idfornecedor=? and idembalagem=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, idL);
            this.preparedStatement.setInt(2, idF);
            this.preparedStatement.setInt(3, idE);
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
}
