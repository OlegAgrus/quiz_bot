package com.telegram_bots.quiz_bot.bot.services.impl;

import com.telegram_bots.quiz_bot.bot.entity.Question;
import com.telegram_bots.quiz_bot.bot.repos.QuestionRepository;
import com.telegram_bots.quiz_bot.bot.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public Question getById(Long id){
        return questionRepository.findQuestionByQuestionId(id);
    }
}
