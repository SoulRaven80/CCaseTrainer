package com.soulraven.ccase.data;

import com.soulraven.ccase.domain.Clue;

import java.util.ArrayList;
import java.util.List;

public interface CluesDao {

    void saveClue(Clue clue);
    List<Clue> getAllClues();
    Clue getClueById(long clueId);
    void deleteClue(Clue clue);
}
