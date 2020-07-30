package com.telegram_bots.quiz_bot.bot.services.impl;

import com.telegram_bots.quiz_bot.bot.entity.Answer;
import com.telegram_bots.quiz_bot.bot.entity.Question;
import com.telegram_bots.quiz_bot.bot.repos.QuestionRepository;
import com.telegram_bots.quiz_bot.bot.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.getOne(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Long getQuestionsCount() {
        return questionRepository.count();
    }

    @Override
    public List<Question> getQuestionsByQuestionScore(Double score) {
        return questionRepository.findQuestionsByQuestionScore(score);
    }

    @Override
    public Question findQuestionByAnswers(List<Answer> answers) {
        Question question = answers.get(0).getQuestion();
        for (Answer answer : answers) {
            if (!answer.getQuestion().equals(question)) {
                return null;
            }
        }
        return question;
    }
}
