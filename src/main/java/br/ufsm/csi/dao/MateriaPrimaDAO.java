package br.ufsm.csi.dao;

import br.ufsm.csi.model.MateriaPrima;

import java.sql.*;
import java.util.ArrayList;

    public class MateriaPrimaDAO {
        private String sql;
        private Statement statement;
        private ResultSet resultSet;
        private PreparedStatement preparedStatement;
        private String status;
        
        public ArrayList<MateriaPrima> getMateriasPrimas() {
            ArrayList<MateriaPrima> materiasprimas = new ArrayList<>();

            try (Connection connection = new ConectaDB().getConexao()) {
                this.sql =  "select * from materiaprima";
                this.statement = connection.createStatement();
                this.resultSet = this.statement.executeQuery(sql);

                while (this.resultSet.next()) {
                    MateriaPrima mp = new MateriaPrima();
                    mp.setId(this.resultSet.getInt("id"));
                    mp.setDescricao(this.resultSet.getString("descricao"));
                    mp.setCas(this.resultSet.getString("cas"));
                    mp.setDcb(this.resultSet.getString("dcb"));
                    mp.setDci(this.resultSet.getString("dci"));
                    materiasprimas.add(mp);
                }
            } catch (SQLException e) { e.printStackTrace();}

            return materiasprimas;
        }

        public String cadastrar(MateriaPrima mp) {
            try(Connection connection = new ConectaDB().getConexao()) {
                this.sql =  "insert into materiaprima(" +
                                "descricao," +
                                "cas," +
                                "dcb," +
                                "dci" +
                            ") values (?, ?, ?, ?)";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, mp.getDescricao());
                this.preparedStatement.setString(2, mp.getCas());
                this.preparedStatement.setString(3, mp.getDcb());
                this.preparedStatement.setString(4, mp.getDci());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
            } catch (SQLException exc) {
                exc.printStackTrace();
                this.status = "Erro";
            }
            return this.status;
        }

        public MateriaPrima getMateriaPrima(int id) {
            MateriaPrima materiaprima = new MateriaPrima();

            try (Connection connection = new ConectaDB().getConexao()) {
                this.sql =  "select * from materiaprima where id=?";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setInt(1, id);
                this.resultSet = this.preparedStatement.executeQuery();

                while (this.resultSet.next()) {
                    materiaprima.setId(this.resultSet.getInt("id"));
                    materiaprima.setDescricao(this.resultSet.getString("descricao"));
                    materiaprima.setCas(this.resultSet.getString("cas"));
                    materiaprima.setDcb(this.resultSet.getString("dcb"));
                    materiaprima.setDci(this.resultSet.getString("dci"));
                }
            } catch (SQLException e) { e.printStackTrace();}

            return materiaprima;
        }

        public String editar(MateriaPrima materiaprima) {
            try(Connection connection = new ConectaDB().getConexao()) {
                this.sql = "update materiaprima set descricao=?, cas=?, dcb=?, dci=? where id=?";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, materiaprima.getDescricao());
                this.preparedStatement.setString(2, materiaprima.getCas());
                this.preparedStatement.setString(3, materiaprima.getDcb());
                this.preparedStatement.setString(4, materiaprima.getDci());
                this.preparedStatement.setInt(5, materiaprima.getId());
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
        public String excluir(MateriaPrima materiaprima) {
            try(Connection connection = new ConectaDB().getConexao()) {
                this.sql =  "begin;" +
                            "delete from fornecimentomateriaprima WHERE idmateriaprima=?;" +
                            "delete from materiaprima WHERE id=?;" +
                            "commit;";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setInt(1, materiaprima.getId());
                this.preparedStatement.setInt(2, materiaprima.getId());
                this.preparedStatement.execute();
            } catch (SQLException exc) {
                exc.printStackTrace();
                this.status = "Erro";
            }
            return this.status;
        }
    }
