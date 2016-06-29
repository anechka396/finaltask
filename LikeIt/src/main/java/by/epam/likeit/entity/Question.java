package by.epam.likeit.entity;

public class Question {

    private int id;
    private String text;
    private String author;
    private String topic;
    private int topicId;

    public Question() {
        String empty = "";
        this.text = empty;
        this.author = empty;
        this.topic = empty;
        this.topicId = 0;
    }

    public Question(int id, String text, String author, String topic, int topicId) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.topic = topic;
        this.topicId = topicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (text != null ? !text.equals(question.text) : question.text != null) return false;
        if (author != null ? !author.equals(question.author) : question.author != null) return false;
        return topic != null ? topic.equals(question.topic) : question.topic == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }
}
