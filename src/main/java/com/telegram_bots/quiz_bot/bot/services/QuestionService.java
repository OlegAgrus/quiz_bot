package com.telegram_bots.quiz_bot.bot.services;

import com.telegram_bots.quiz_bot.bot.entity.Answer;
import com.telegram_bots.quiz_bot.bot.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(Long id);

    List<Question> getAllQuestions();

    Long getQuestionsCount();

    List<Question> getQuestionsByQuestionScore(Double score);

    Question getQuestionByAnswers(List<Answer> answers);

    Question getQuestionByText(String text);

    Question addQuestion(Question question);

    List<Question> addQuestions(List<Question> questions);

    void deleteQuestion(Question question);

    void deleteQuestionById(Long id);
}
