package br.mack.ps2;

import br.mack.ps2.entidades.ContaBancaria;
import br.mack.ps2.entidades.Jogo;
import br.mack.ps2.persistencia.JogoDAO;

import java.util.List;
import java.util.Scanner;

public class InterfaceUsuarioJogo {
    JogoDAO dao;
    Scanner input;

    public InterfaceUsuarioJogo(JogoDAO dao) {
        this.dao = dao;
        this.input = new Scanner(System.in);
    }

    public void iniciar() {
        imprimirMenu();
    }

    private void imprimirMenu() {
        int opc = 0;
        do {
            System.out.println("\n==============");
            System.out.println("==== Menu ====");
            System.out.println("==============");
            System.out.println("\t1. Create");
            System.out.println("\t2. Read");
            System.out.println("\t3. Update");
            System.out.println("\t4. Delete");
            System.out.println("\t5. sair");
            System.out.print("Escolha uma opção: ");
            opc = input.nextInt();

            //necessário para ler a quebra de linha (enter)
            input.nextLine();

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
                    System.out.println("Fim do programa!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

        } while (opc != 5);
    }

    private void create() {
        Jogo jogos = new Jogo();

        System.out.println("==== Inserir ====");
        System.out.print("\nInforme o id: ");
        jogos.setIdJogo(input.nextLong());
        //necessário para ler o \n da entrada (enter)
        input.nextLine();

        System.out.print("Informe o nome do Primeiro time: ");
        jogos.setNm_timeA(input.nextLine());

        System.out.print("Informe o nome do Segundo time: ");
        jogos.setNm_timeB(input.nextLine());

        System.out.println("Pontuação do Primeiro time: ");
        jogos.setPont_timeA(input.nextInt());

        System.out.println("Pontuação do Segundo time: ");
        jogos.setPont_timeB(input.nextInt());

        if (dao.create(jogos)) {
            System.out.println("Dados inseridos na tabela Jogo com sucesso");
        } else {
            System.out.println("Problema ao adicionar dados na tabela Jogo");
        }
    }

    private void read() {
        List<Jogo> jogos = dao.read();

        System.out.println("==== Ler dados ====");
        for (Jogo jogo : jogos) {
            System.out.println(jogo);
        }
    }

    public void update(){
        Jogo jogo = new Jogo();

        while(true){
            System.out.println("==== Alterar ====");
            read();
            System.out.println("\nPara cancelar a operação, digite 1: ");
            int cnl = input.nextInt();
            if (cnl == 1) {
                break;
            }
            System.out.print("Informe o nome do Primeiro Time: ");
            jogo.setNm_timeA(input.next());

            System.out.print("Informe o nome do Segundo Time: ");
            jogo.setNm_timeB(input.next());

            System.out.print("Informe a pontuação do Primeiro time: ");
            jogo.setPont_timeA(input.nextInt());

            System.out.print("Informe a pontuação do Primeiro time: ");
            jogo.setPont_timeB(input.nextInt());

            System.out.print("\nInforme o ID da Conta Bancária: ");
            jogo.setIdJogo(input.nextLong());
            input.nextLine();

            if (dao.update(jogo)) {
                System.out.println("\nConta Bancária atualizada com sucesso!");
            } else {
                System.out.println("\nOcorreu um problema ao atualizar a Conta Bancária");
            }
        }

    }

    private void delete() {
        List<Jogo> jogos = dao.read();

        while (true) {

            System.out.println("==== Deletar ====");
            int i = 0;
            for (Jogo jogo : jogos) {
                System.out.println(i + " - " + jogo);
                i++;
            }
            System.out.println(i + " - Cancelar operação");

            System.out.print("Qual id deseja remover? ");
            int opc = input.nextInt();
            //Necessário para ler a quebra de linha (enter)
            input.nextLine();

            if (opc == i) {
                // Cancelar operação
                break;
            }

            if (opc >= jogos.size() || opc < 0) {
                System.out.println("Esta opção não é válida");
            } else {
                if (dao.delete(jogos.get(opc))) {
                    System.out.println("Id " + jogos.get(opc).getIdJogo() +
                            " removido com sucesso");
                } else {
                    System.out.println("Falha ao tentar remover");
                }
                //Isso para o while infinito
                break;
            }
        }
    }
}
