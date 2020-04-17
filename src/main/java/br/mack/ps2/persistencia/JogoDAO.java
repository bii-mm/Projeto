package br.mack.ps2.persistencia;

import br.mack.ps2.entidades.Jogo;
import java.util.List;

public interface JogoDAO {
    boolean create (Jogo jogo);
    List<Jogo> read ();
    boolean update (Jogo jogo);
    boolean delete (Jogo jogo);
}

