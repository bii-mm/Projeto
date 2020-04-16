package br.mack.ps2;

import br.mack.ps2.entidades.ContaBancaria;
import br.mack.ps2.persistencia.ContaBancariaDAO;

import java.sql.*;
import java.util.*;

public class InterfaceUsuario {
    ContaBancariaDAO dao;
    Scanner in;

    public InterfaceUsuario(ContaBancariaDAO dao) {
        this.dao = dao;
        this.in = new Scanner(System.in);
    }

    public void iniciar() {
        imprimirMenu();
    }

    private void imprimirMenu() {
        int opc = 0;
        do {
            System.out.println("\n======== Menu ========");
            System.out.println("\t1. Criar Conta Bancária");
            System.out.println("\t2. Consultar Conta Bancária");
            System.out.println("\t3. Atualizar Conta Bancária");
            System.out.println("\t4. Deletar Conta Bancária");
            System.out.println("\t5. Sair");
            System.out.print("Escolha uma opção:_ ");
            opc = in.nextInt();

            in.nextLine();

            switch (opc) {
                case 1:
                    this.create();
                    break;
                case 2:
                    this.read();
                    break;
                case 3:
                    this.update();
                    break;
                case 4:
                    this.delete();
                    break;
                case 5:
                    System.out.println("Operação finalizada");
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }

        }while (opc != 5);
    }

    private void create() {
        ContaBancaria conta = new ContaBancaria();

        System.out.println("\n--------- Nova Conta Bancária ---------");
        System.out.print("\nInforme o Nome do Titular: ");
        conta.setNome_titular(in.next());
        in.nextLine();

        System.out.print("Informe o Saldo: ");
        conta.setSaldo(in.nextDouble());

        System.out.print("Informe o Número da Agência: ");
        conta.setN_agencia(in.nextInt());

        if (dao.create(conta)) {
            System.out.println("Conta Bancária adicionada ao banco de Dados");
        } else {
            System.out.println("Ops: problema ao adicionar a Conta Bancária");
        }
    }

    private void read() {
        List<ContaBancaria> contas = dao.read();

        System.out.println("\n--------- Lista de Contas Cadastradas ---------");
        for(ContaBancaria conta : contas) {
            System.out.println(conta);
        }
    }

    public void update(){
        ContaBancaria conta = new ContaBancaria();

        while(true){
             read();
             System.out.println("\nPara cancelar a operação, digite 1: ");
             int cnl = in.nextInt();
             if (cnl == 1) {
                 break;
             }

            System.out.print("\nInforme o ID da Conta Bancária: ");
            conta.setId_conta(in.nextLong());
             in.nextLine();

            System.out.print("Informe o Nome do Titular: ");
            conta.setNome_titular(in.next());

             System.out.print("Informe o Saldo: ");
             conta.setSaldo(in.nextDouble());

             System.out.print("Informe o Número da Conta: ");
             conta.setN_agencia(in.nextInt());

             if (dao.update(conta)) {
                System.out.println("\nConta Bancária atualizada com sucesso!");
             } else {
                System.out.println("\nOcorreu um problema ao atualizar a Conta Bancária");
             }
        }

    }

    private void delete() {
        List<ContaBancaria> contas = dao.read();

        while (true) {
            System.out.println("\n--------- Lista de Contas Cadastradas ---------");
            int i = 0;
            for (ContaBancaria conta : contas) {
                System.out.println(i + " - " + conta);
                i++;
            }
            System.out.println(i + " - Cancelar Operação");

            System.out.print("Qual Conta Bancária deseja remover? ");
            int opc = in.nextInt();
            in.nextLine();

            if (opc==i) {
                break;
            }

            if (opc >= contas.size() || opc < 0) {
                System.out.println("Esta opção não é válida");
            } else {
                if (dao.delete(contas.get(opc))) {
                    System.out.println("Conta Bancária removida com sucesso");
                } else {
                    System.out.println("OPS: falar ao tentar remover");
                }
                break;
            }
        }
    }
}
