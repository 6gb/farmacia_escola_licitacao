package br.ufsm.csi.dao;

import br.ufsm.csi.model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Fornecedor> getFornecedores() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from fornecedor";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(this.resultSet.getInt("id"));
                f.setCnpj(this.resultSet.getString("cnpj"));
                f.setNome(this.resultSet.getString("nome"));
                f.setEndereco(this.resultSet.getString("endereco"));
                f.setTelefone(this.resultSet.getString("telefone"));
                f.setEmail(this.resultSet.getString("email"));
                f.setSicaf(this.resultSet.getString("sicaf"));
                fornecedores.add(f);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return fornecedores;
    }

    public String cadastrar(Fornecedor f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "insert into fornecedor(" +
                            "cnpj," +
                            "nome," +
                            "endereco," +
                            "telefone," +
                            "email," +
                            "sicaf" +
                        ") values (?, ?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, f.getCnpj());
            this.preparedStatement.setString(2, f.getNome());
            this.preparedStatement.setString(3, f.getEndereco());
            this.preparedStatement.setString(4, f.getTelefone());
            this.preparedStatement.setString(5, f.getEmail());
            this.preparedStatement.setString(6, f.getSicaf());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }

    public Fornecedor getFornecedor(int id) {
        Fornecedor fornecedor = new Fornecedor();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from fornecedor where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                fornecedor.setId(this.resultSet.getInt("id"));
                fornecedor.setCnpj(this.resultSet.getString("cnpj"));
                fornecedor.setNome(this.resultSet.getString("nome"));
                fornecedor.setEndereco(this.resultSet.getString("endereco"));
                fornecedor.setTelefone(this.resultSet.getString("telefone"));
                fornecedor.setEmail(this.resultSet.getString("email"));
                fornecedor.setSicaf(this.resultSet.getString("sicaf"));
            }
        } catch (SQLException e) { e.printStackTrace();}

        return fornecedor;
    }

    public String editar(Fornecedor fornecedor) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update fornecedor set cnpj=?, nome=?, endereco=?, telefone=?, email=?, sicaf=? where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, fornecedor.getCnpj());
            this.preparedStatement.setString(2, fornecedor.getNome());
            this.preparedStatement.setString(3, fornecedor.getEndereco());
            this.preparedStatement.setString(4, fornecedor.getTelefone());
            this.preparedStatement.setString(5, fornecedor.getEmail());
            this.preparedStatement.setString(6, fornecedor.getSicaf());
            this.preparedStatement.setInt(7, fornecedor.getId());
            this.preparedStatement.execute();

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = "ok";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
    public String excluir(Fornecedor fornecedor) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "begin;" +
                        "delete from fornecimentoembalagem WHERE idfornecedor=?;" +
                        "delete from fornecimentomateriaprima WHERE idfornecedor=?;" +
                        "delete from fornecedor WHERE id=?;" +
                        "commit;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, fornecedor.getId());
            this.preparedStatement.setInt(2, fornecedor.getId());
            this.preparedStatement.setInt(3, fornecedor.getId());
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "Erro";
        }
        return this.status;
    }
}
