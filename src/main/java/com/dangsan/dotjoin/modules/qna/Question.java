package com.dangsan.dotjoin.modules.qna;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @JsonProperty
    private Account writer;

    @JsonProperty
    private String title;

    @Lob
    @JsonProperty
    private String contents;

    @OneToMany(mappedBy = "question")
    @OrderBy("id DESC")
    private List<Answer> answers;

    @JsonProperty
    private Integer countOfAnswer = 0;

    public Question() {
    }

    public Question(Account writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    // 로그인유저와 글작성자 비교
    public boolean isSameWriter(Account loginUser) {
        return this.writer.equals(loginUser);
    }

    // update 메서드
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 답변 수 증가 메서드
    public void addAnswer() {
        this.countOfAnswer += 1;
    }

    // 답변 수 감소 메서드
    public void deleteAnswer() {
        this.countOfAnswer -= 1;
    }

    @Override
    public String toString() {
        return "Question{" + super.toString() +
                "writer=" + writer +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", answers=" + answers +
                ", countOfAnswer=" + countOfAnswer +
                '}';
    }
}
