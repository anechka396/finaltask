package by.epam.likeit.entity;

import java.io.Serializable;

public class Topic implements Serializable{
    int id;
    String value;

    public Topic() {
        String empty = "";
        this.id = 0;
        this.value = empty;
    }

    public Topic(int id, String topic) {
        this.id = id;
        this.value = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic1 = (Topic) o;

        if (id != topic1.id) return false;
        return value != null ? value.equals(topic1.value) : topic1.value == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
