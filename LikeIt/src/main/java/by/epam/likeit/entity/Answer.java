package by.epam.likeit.entity;

import java.io.Serializable;

public class Answer implements Serializable {
    private int id;
    private int qId;
    private int mark;
    private String text;
    private String author;

    public Answer() {
        String empty = "";
        this.id = 0;
        this.qId = 0;
        this.text = empty;
        this.author = empty;
        this.mark = 0;
    }

    public Answer(int id, int question_id, String text, String author, int mark) {
        this.id = id;
        this.qId = question_id;
        this.text = text;
        this.author = author;
        this.mark = mark;
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int question_id) {
        this.qId = question_id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (text != null ? !text.equals(answer.text) : answer.text != null) return false;
        return author != null ? author.equals(answer.author) : answer.author == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
