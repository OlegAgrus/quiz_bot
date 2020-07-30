package com.telegram_bots.quiz_bot.bot.services;

import com.telegram_bots.quiz_bot.bot.entity.Answer;
import com.telegram_bots.quiz_bot.bot.entity.Question;

import java.util.List;

public interface AnswerService {
    Answer getAnswerById(Long id);

    List<Answer> getAllAnswers();

    Long getAnswersCount();

    List<Answer> getAnswersByQuestion(Question question);

    List<Answer> getAnswerByText(String text);
}
