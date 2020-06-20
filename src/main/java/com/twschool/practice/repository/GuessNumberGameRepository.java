package com.twschool.practice.repository;

import com.twschool.practice.domain.GuessNumberGame;

public interface GuessNumberGameRepository {

    GuessNumberGame create(String userId);

    GuessNumberGame find(String userId);
}
