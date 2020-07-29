package com.telegram_bots.quiz_bot.bot.services;

import com.telegram_bots.quiz_bot.bot.entity.Question;

public interface QuestionService {
    Question getById(Long id);
}
