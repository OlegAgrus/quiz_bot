package com.telegram_bots.quiz_bot.bot.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "question")
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private Boolean isRightAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    public Answer(String text, Question question, Boolean right_answer) {
        this.answerText = text;
        this.question = question;
        this.isRightAnswer = right_answer;
    }

    public Answer() {
    }
}
