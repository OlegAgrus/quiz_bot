package com.telegram_bots.quiz_bot.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "question")
@EqualsAndHashCode(exclude = "question")
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @Column(name = "is_right_answer", nullable = false)
    private Boolean isRightAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String text, Question question, Boolean isRightAnswer) {
        this.answerText = text;
        this.question = question;
        this.isRightAnswer = isRightAnswer;
    }

    public Answer() {
    }
}
