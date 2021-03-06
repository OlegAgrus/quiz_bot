package com.telegram_bots.quiz_bot.bot.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "answers")
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String questionText;

    @Column(precision = 2, scale = 1, nullable = false)
    private Double questionScore;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    public Question(String text, Double score) {
        this.questionText = text;
        this.questionScore = score;
    }

    public Question() {
    }
}
