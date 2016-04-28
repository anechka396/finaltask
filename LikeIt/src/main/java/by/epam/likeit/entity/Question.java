package by.epam.likeit.entity;

public class Question {
    private int id;
    private String text;
    private String author;
    private String topic;

    public Question() {
        String empty = "";
        this.text = empty;
        this.author = empty;
        this.topic = empty;
    }

    public Question(int id, String text, String author, String topic) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.topic = topic;
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

    public String getTopic() {
        return topic;
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

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
