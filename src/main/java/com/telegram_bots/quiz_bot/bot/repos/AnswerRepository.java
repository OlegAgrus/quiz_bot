package com.telegram_bots.quiz_bot.bot.repos;

import com.telegram_bots.quiz_bot.bot.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAnswersByAnswerText(String text);
}
