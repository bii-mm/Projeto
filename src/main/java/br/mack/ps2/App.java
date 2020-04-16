package br.mack.ps2;

import br.mack.ps2.persistencia.ContaBancariaDAOMySQL;

import java.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        ContaBancariaDAOMySQL mysqlDAO = new ContaBancariaDAOMySQL();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(mysqlDAO);
        interfaceUsuario.iniciar();
    }
}
