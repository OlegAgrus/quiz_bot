package com.telegram_bots.quiz_bot.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = "answers")
@EqualsAndHashCode(exclude = "answers")
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "question_score", precision = 2, scale = 1, nullable = false)
    private Double questionScore;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public Question(String text, Double score) {
        this.questionText = text;
        this.questionScore = score;
    }

    public Question() {
    }
}
