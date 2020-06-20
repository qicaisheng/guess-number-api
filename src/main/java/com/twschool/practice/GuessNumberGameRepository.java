package com.twschool.practice;

import com.twschool.practice.domain.GuessNumberGame;

public interface GuessNumberGameRepository {

    GuessNumberGame create();

    GuessNumberGame find();
}
