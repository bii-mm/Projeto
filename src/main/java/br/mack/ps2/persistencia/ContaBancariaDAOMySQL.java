package br.mack.ps2.persistencia;

import br.mack.ps2.entidades.ContaBancaria;

import java.util.*;
import java.sql.*;


public class ContaBancariaDAOMySQL implements ContaBancariaDAO {
    private String createSQL = "INSERT INTO conta_bancaria(nome_titular, saldo, n_agencia) VALUES (?, ?, ?)";
    private String readSQL = "SELECT * FROM conta_bancaria";
    private String updateSQL = "UPDATE conta_bancaria SET nome_titular= ?, saldo=?, n_agencia = ? WHERE id_conta=?";
    private String deleteSQL = "DELETE FROM conta_bancaria WHERE id_conta=?";

    private final MySQLConnection mysql = new MySQLConnection();

    @Override
    public boolean create(ContaBancaria conta) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);
            stm.setString(1, conta.getNome_titular());
            stm.setDouble(2, conta.getSaldo());
            stm.setInt(3, conta.getN_agencia());


            int registros = stm.executeUpdate();

            return  registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<ContaBancaria> read() {
        Connection conexao = mysql.getConnection();
        List<ContaBancaria> contas = new ArrayList();

        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ContaBancaria conta = new ContaBancaria();
                conta.setId_conta(rs.getLong("id_conta"));
                conta.setNome_titular(rs.getString("nome_titular"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setN_agencia(rs.getInt("n_agencia"));
                contas.add(conta);
            }

            return contas;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return contas;
    }

    @Override
    public boolean update(ContaBancaria conta) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setString(1, conta.getNome_titular());
            stm.setDouble(2, conta.getSaldo());
            stm.setInt(3, conta.getN_agencia());
            stm.setLong(4, conta.getId_conta());

            int registros = stm.executeUpdate();

            return  registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(ContaBancaria conta) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);


            stm.setLong(1, conta.getId_conta());

            int registros = stm.executeUpdate();

            return  registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
