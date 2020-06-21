package com.twschool.practice.repository;

import com.twschool.practice.domain.GuessNumberGame;

public interface GuessNumberGameRepository {

    GuessNumberGame createBy(String userId);

    GuessNumberGame findBy(String userId);

    void deleteBy(String userId);
}
