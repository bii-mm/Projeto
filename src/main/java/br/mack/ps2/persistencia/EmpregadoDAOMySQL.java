package br.mack.ps2.persistencia;
import br.mack.ps2.entidades.Empregado;
import java.util.*;
import java.sql.*;

public class EmpregadoDAOMySQL implements EmpregadoDAO{
    private String createSQL = "INSERT INTO empregado(nome_emp, cargo, salario) VALUES (?, ?, ?)";
    private String readSQL = "SELECT * FROM empregado";
    private String updateSQL = "UPDATE empregado SET nome_emp= ?, cargo=?, salario = ? WHERE id_empregado=?";
    private String deleteSQL = "DELETE FROM empregado WHERE id_empregado=?";

    private final MySQLConnection mysql = new MySQLConnection();

    @Override
    public boolean create(Empregado empregado) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);
            stm.setString(1, empregado.getNome_emp());
            stm.setString(2, empregado.getCargo());
            stm.setLong(3, empregado.getSalario());


            int registros = stm.executeUpdate();

            return  registros > 0;

        } catch (final SQLException ex) {
            System.out.println("Falha na conexão com o Banco de Dados!");
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
    public List<Empregado> read() {
        Connection conexao = mysql.getConnection();
        List<Empregado> empregados = new ArrayList();

        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Empregado empregado = new Empregado();
                empregado.setId_empregado(rs.getLong("id_empregado"));
                empregado.setNome_emp(rs.getString("nome_emp"));
                empregado.setCargo(rs.getString("cargo"));
                empregado.setSalario(rs.getLong("salario"));
                empregados.add(empregado);
            }

            return empregados;

        } catch (final SQLException ex) {
            System.out.println("Falha na conexão com o Banco de Dados!");
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
        return empregados;
    }

    @Override
    public boolean update(Empregado empregado) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setString(1, empregado.getNome_emp());
            stm.setString(2, empregado.getCargo());
            stm.setLong(3, empregado.getSalario() );
            stm.setLong(4, empregado.getId_empregado(in.nextLong()));

            int registros = stm.executeUpdate();

            return  registros > 0;

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
    public boolean delete(Empregado empregado) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);


            stm.setLong(1, empregado.getId_empregado(in.nextLong()));

            int registros = stm.executeUpdate();

            return  registros > 0;

        } catch (final SQLException ex) {
            System.out.println("Falha na conexão com o Banco de Dados!");
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
