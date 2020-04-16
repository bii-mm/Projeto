package br.mack.ps2.entidades;

public class ContaBancaria {
    private long id_conta;
    private String nome_titular;
    private double saldo;
    private int n_agencia;

    public ContaBancaria() {
    }

    public ContaBancaria(long id_conta, String nome_titular, double saldo, int n_agencia){
        this.id_conta = id_conta;
        this.nome_titular = nome_titular;
        this.saldo = saldo;
        this.n_agencia = n_agencia;
    }

    public void setId_conta(long id_conta) {
        this.id_conta = id_conta;
    }

    public long getId_conta() {
        return id_conta;
    }

    public void setNome_titular(String nome_titular) {
        this.nome_titular = nome_titular;
    }

    public String getNome_titular() {
        return nome_titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setN_agencia(int n_agencia) {
        this.n_agencia = n_agencia;
    }

    public int getN_agencia() {
        return n_agencia;
    }

    @Override
    public String toString() {
        return "Conta Bancaria " +
                "\n\tID da conta: " + id_conta +
                "\n\tNome do Titular: " + nome_titular +
                "\n\tSaldo: " + saldo +
                "\n\tNúmero da Agencia: " + n_agencia;
    }
}
