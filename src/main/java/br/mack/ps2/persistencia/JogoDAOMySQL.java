package br.mack.ps2.persistencia;

import br.mack.ps2.entidades.Jogo;
import br.mack.ps2.persistencia.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogoDAOMySQL implements JogoDAO{
    String createSQL = "INSERT INTO jogo VALUES (?, ?, ?, ?, ?)";
    String readSQL = "SELECT * FROM jogo";
    String updateSQL = "UPDATE jogo SET nm_timeA = ?, nm_timeB = ?, pont_timeA = ?, pont_timeB = ? WHERE idjogo = ?";
    String deleteSQL = "DELETE FROM jogo WHERE idjogo = ?";

    private final MySQLConnection mysql = new MySQLConnection();

    @Override
    public boolean create(Jogo jogo) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setLong(1, jogo.getIdJogo());
            stm.setString(2, jogo.getNm_timeA());
            stm.setString(3, jogo.getNm_timeB());
            stm.setInt(4, jogo.getPont_timeA());
            stm.setInt(5, jogo.getPont_timeB());

            int registros = stm.executeUpdate();
            return registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }return false;

    }

    @Override
    public List<Jogo> read(){
        Connection conexao = mysql.getConnection();
        List<Jogo> jogos = new ArrayList();
        try{
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Jogo jogo = new Jogo();
                jogo.setIdJogo(rs.getLong("id_jogo"));
                jogo.setNm_timeA(rs.getString("nm_timeA"));
                jogo.setNm_timeB(rs.getString("nm_timeB"));
                jogo.setPont_timeA(rs.getInt("pont_timeA"));
                jogo.setPont_timeB(rs.getInt("pont_timeB"));
            }
            return jogos;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return jogos;
    }

    public boolean update (Jogo jogo){
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setString (1, jogo.getNm_timeA());
            stm.setString (2, jogo.getNm_timeB());
            stm.setInt (3, jogo.getPont_timeA());
            stm.setInt (4, jogo.getPont_timeB());

            stm.setLong (5, jogo.getIdJogo());

            int registros = stm.executeUpdate();
            return (registros > 0) ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(Jogo jogo){
        Connection conexao = mysql.getConnection();
        try{
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);

            stm.setLong (1, jogo.getIdJogo());

            int registros = stm.executeUpdate();
            return (registros > 0) ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
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