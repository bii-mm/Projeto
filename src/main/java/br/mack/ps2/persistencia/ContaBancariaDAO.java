package br.mack.ps2.persistencia;
import br.mack.ps2.entidades.ContaBancaria;

import java.util.*;

public interface ContaBancariaDAO {
    boolean create (ContaBancaria conta);
    List<ContaBancaria> read ();
    boolean update(ContaBancaria conta);
    boolean delete(ContaBancaria conta);
}
