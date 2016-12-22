package com.soulraven.ccase.data;

import com.soulraven.ccase.domain.Clue;

import java.util.ArrayList;
import java.util.List;

public class CluesDao {

    public List<Clue> readAllClues() {
        List<Clue> clues = new ArrayList<Clue>();
        clues.add(new Clue(1, "Reloj"));
        clues.add(new Clue(1, "Impactos de bala"));
        clues.add(new Clue(1, "Soldado de juguete"));
        clues.add(new Clue(1, "Huella de llanta"));
        clues.add(new Clue(1, "Rosa"));
        clues.add(new Clue(1, "Gnomo de Jardín"));

        clues.add(new Clue(1, "Escalera de mano"));
        clues.add(new Clue(1, "Casco"));
        return clues;
    }
}
