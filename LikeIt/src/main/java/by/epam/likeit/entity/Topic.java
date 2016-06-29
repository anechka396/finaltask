package by.epam.likeit.entity;

public class Topic {
    int id;
    String topic;

    public Topic() {
        String empty = "";
        this.id = 0;
        this.topic = empty;
    }

    public Topic(int id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic1 = (Topic) o;

        if (id != topic1.id) return false;
        return topic != null ? topic.equals(topic1.topic) : topic1.topic == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }
}
