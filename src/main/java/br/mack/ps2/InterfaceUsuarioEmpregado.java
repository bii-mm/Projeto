package br.mack.ps2;
import br.mack.ps2.entidades.ContaBancaria;
import br.mack.ps2.entidades.Empregado;
import br.mack.ps2.persistencia.EmpregadoDAO;
import java.sql.*;
import java.util.*;
public class InterfaceUsuarioEmpregado {
    EmpregadoDAO dao;
    Scanner in;

    public InterfaceUsuario(EmpregadoDAO dao){
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
            System.out.println("\t1. Criar Empregado");
            System.out.println("\t2. Consultar Empregado");
            System.out.println("\t3. Atualizar Empregado");
            System.out.println("\t4. Deletar Empregado");
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
        Empregado empregado = new Empregado();

        System.out.println("\n--------- Novo Empregado ---------");
        System.out.print("\nInforme o Nome do Empregado: ");
        empregado.setNome_emp(in.nextLine());
        in.nextLine();

        System.out.print("Informe o Cargo do Empregado: ");
        empregado.setCargo(in.nextLine());

        System.out.print("Informe o Salário do Empregado: ");
        empregado.setSalario(in.nextLong());

        if (dao.create(empregado)) {
            System.out.println("Empregado adicionado com sucesso!");
        } else {
            System.out.println("Puts... tivemos um problema");
        }
    }

    private void read() {
        List<Empregado> empregados = dao.read();

        System.out.println("\n--------- Lista de Empregados Cadastrados ---------");
        for(Empregado empregado : empregados) {
            System.out.println(empregado);
        }
    }

    public void update(){
        Empregado empregado = new Empregado();

        while(true){
            read();
            System.out.println("\nPara cancelar a operação, digite 1: ");
            int x = in.nextInt();
            if (x == 1) {
                break;
            }

            System.out.print("\nDigite o ID do Empregado: ");
            empregado.setId_empregado(in.nextLong());
            in.nextLine();

            System.out.print("Digite o Nome do Empregado: ");
            empregado.setNome_emp(in.nextLine());

            System.out.print("Digite o Cargo do Empregado: ");
            empregado.setCargo(in.nextLine());

            System.out.print("Digite o Salário do Empregado: ");
            empregado.setSalario(in.nextLong());

            if (dao.update(empregado)) {
                System.out.println("\nEmpregado atualizado com sucesso!");
            } else {
                System.out.println("\nOcorreu um problema ao atualizar o Empregado!");
            }
        }

    }

    private void delete() {
        List<Empregado> empregados = dao.read();

        while (true) {
            System.out.println("\n--------- Lista de Empregados Cadastrados: ---------");
            int i = 0;
            for (Empregado empregado : empregados) {
                System.out.println(i + " - " + empregado);
                i++;
            }
            System.out.println(i + " - Cancelar Operação");

            System.out.print("Qual Empregado deseja remover? ");
            int opc = in.nextInt();
            in.nextLine();

            if (opc==i) {
                break;
            }

            if (opc >= empregados.size() || opc < 0) {
                System.out.println("Esta opção não é válida");
            } else {
                if (dao.delete(empregados.get(opc))) {
                    System.out.println("Empregado removido com sucesso!");
                } else {
                    System.out.println("Vish, falha ao tentar remover");
                }
                break;
            }
        }
    }
}
