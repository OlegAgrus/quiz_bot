package com.telegram_bots.quiz_bot.bot.services;

import com.telegram_bots.quiz_bot.bot.entity.Answer;
import com.telegram_bots.quiz_bot.bot.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(Long id);

    List<Question> getAllQuestions();

    Long getQuestionsCount();

    List<Question> getQuestionsByQuestionScore(Double score);

    Question findQuestionByAnswers(List<Answer> answers);
}
