package com.twschool.practice;

import org.junit.Assert;
import org.junit.Test;

public class AnswerGeneratorTest {
    @Test
    public void should_generate_valid_answer() {
        AnswerGenerator answerGenerator = new AnswerGenerator();
        
        GameAnswer gameAnswer = answerGenerator.generateAnswer();

        Assert.assertTrue(gameAnswer.isValidFormat());
    }
}
