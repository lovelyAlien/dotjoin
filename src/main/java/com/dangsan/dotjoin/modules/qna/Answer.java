package com.dangsan.dotjoin.modules.qna;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity{

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private Account writer;

    @JsonProperty
    private Long countOfLike=0L;

    @Lob // 255자가 넘는 String 타입일 경우 애노테이션 추가
    @JsonProperty
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
    @JsonProperty
    private Question question;

    // 기본 생성자
    public Answer() {
    }

    // 생성자
    public Answer(Account writer, Question question, String contents) {
        this.writer = writer;
        this.contents = contents;
        this.question = question;
    }

    public boolean isSameWriter(Account loginUser) {
        return loginUser.equals(this.writer);
    }

    // 답변 수 증가 메서드
    public void addLike() {
        this.countOfLike += 1;
    }

    // 답변 수 감소 메서드
    public void deleteLike() {
        this.countOfLike -= 1;
    }

    @Override
    public String toString() {
        return "Answer{" + super.toString() +
                "writer=" + writer +
                ", contents='" + contents + '\'' +
                ", question=" + question +
                '}';
    }
}
