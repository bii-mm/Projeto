package br.mack.ps2;

import br.mack.ps2.persistencia.ContaBancariaDAOMySQL;
import br.mack.ps2.persistencia.JogoDAOMySQL;
import br.mack.ps2.persistencia.EmpregadoDAOMySQL;
import java.sql.*;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        int opc = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("\n======== Menu ========");
            System.out.println("\t1. Acessar Banco de Dados de Conta Bancária");
            System.out.println("\t2. Acessar Banco de Dados de Empregado");
            System.out.println("\t3. Acessar Banco de Dados de Jogo");
            System.out.println("\t4. Sair");
            System.out.print("Escolha uma opção:_ ");
            opc = in.nextInt();

            in.nextLine();

            switch (opc) {
                case 1:
                    ContaBancariaDAOMySQL mysqlDAO = new ContaBancariaDAOMySQL();
                    InterfaceUsuario interfaceUsuario = new InterfaceUsuario(mysqlDAO);
                    interfaceUsuario.iniciar();
                    break;
                case 2:
                    EmpregadoDAOMySQL mySQL = new EmpregadoDAOMySQL();
                    InterfaceUsuarioEmpregado interfaceUsuarioEmpregado = new InterfaceUsuarioEmpregado();
                    interfaceUsuarioEmpregado.iniciar();
                    break;
                case 3:
                    JogoDAOMySQL mysqlDAOJogo = new JogoDAOMySQL();
                    InterfaceUsuarioJogo interfaceUsuarioJogo = new InterfaceUsuarioJogo(mysqlDAOJogo);
                    interfaceUsuarioJogo.iniciar();
                    break;
                case 4:
                    System.out.println("Operação finalizada");
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }

        }while (opc != 4);

    }
}
