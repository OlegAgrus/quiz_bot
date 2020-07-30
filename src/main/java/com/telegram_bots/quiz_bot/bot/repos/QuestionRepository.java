package com.telegram_bots.quiz_bot.bot.repos;

import com.telegram_bots.quiz_bot.bot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByQuestionScore(Double score);
}
