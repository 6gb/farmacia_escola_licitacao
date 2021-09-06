package br.ufsm.csi.dao;

import br.ufsm.csi.model.Embalagem;

import java.sql.*;
import java.util.ArrayList;

public class EmbalagemDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Embalagem> getEmbalagens() {
        ArrayList<Embalagem> embalagens = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from embalagem";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Embalagem e = new Embalagem();
                e.setId(this.resultSet.getInt("id"));
                e.setDescricao(this.resultSet.getString("descricao"));
                e.setMaterial(this.resultSet.getString("material"));
                e.setCapacidade(this.resultSet.getInt("capacidade"));
                embalagens.add(e);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return embalagens;
    }

    public String cadastrar(Embalagem e) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "insert into embalagem(" +
                            "descricao," +
                            "material," +
                            "capacidade" +
                        ") values (?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, e.getDescricao());
            this.preparedStatement.setString(2, e.getMaterial());
            this.preparedStatement.setInt(3, e.getCapacidade());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }

    public Embalagem getEmbalagem(int id) {
        Embalagem embalagem = new Embalagem();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from embalagem where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                embalagem.setId(this.resultSet.getInt("id"));
                embalagem.setDescricao(this.resultSet.getString("descricao"));
                embalagem.setMaterial(this.resultSet.getString("material"));
                embalagem.setCapacidade(this.resultSet.getInt("capacidade"));
            }
        } catch (SQLException e) { e.printStackTrace();}

        return embalagem;
    }

    public String editar(Embalagem embalagem) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update embalagem set descricao=?, material=?, capacidade=? where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, embalagem.getDescricao());
            this.preparedStatement.setString(2, embalagem.getMaterial());
            this.preparedStatement.setInt(3, embalagem.getCapacidade());
            this.preparedStatement.setInt(4, embalagem.getId());
            this.preparedStatement.execute();

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = "ok";
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
    public String excluir(Embalagem embalagem) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "begin;" +
                        "delete from fornecimentoembalagem WHERE idembalagem=?;" +
                        "delete from embalagem WHERE id=?;" +
                        "commit;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, embalagem.getId());
            this.preparedStatement.setInt(2, embalagem.getId());
            this.preparedStatement.execute();
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
}
