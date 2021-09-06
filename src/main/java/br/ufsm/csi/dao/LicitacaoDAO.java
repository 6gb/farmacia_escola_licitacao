package br.ufsm.csi.dao;

import br.ufsm.csi.model.Licitacao;

import java.sql.*;
import java.util.ArrayList;

public class LicitacaoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Licitacao> getLicitacoes() {
        ArrayList<Licitacao> licitacoes = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from licitacao";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Licitacao e = new Licitacao();
                e.setId(this.resultSet.getInt("id"));
                e.setDataCriacao(this.resultSet.getDate("dataCriacao"));
                licitacoes.add(e);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return licitacoes;
    }

    public String cadastrar() {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "insert into licitacao(datacriacao) values (CURRENT_DATE)";
            this.statement = connection.createStatement();
            this.statement.execute(sql);
            this.status = "ok";
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }

    public Licitacao getLicitacao(int id) {
        Licitacao licitacao = new Licitacao();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from licitacao where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                licitacao.setId(this.resultSet.getInt("id"));
                licitacao.setDataCriacao(this.resultSet.getDate("dataCriacao"));
            }
        } catch (SQLException e) { e.printStackTrace();}

        return licitacao;
    }

    public String excluir(Licitacao licitacao) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "begin;" +
                        "delete from fornecimentoembalagem WHERE idlicitacao=?;" +
                        "delete from fornecimentomateriaprima WHERE idlicitacao=?;" +
                        "delete from licitacao WHERE id=?;" +
                        "commit;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, licitacao.getId());
            this.preparedStatement.setInt(2, licitacao.getId());
            this.preparedStatement.setInt(3, licitacao.getId());
            this.preparedStatement.execute();
            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
}
