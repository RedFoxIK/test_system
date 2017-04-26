package ua.test.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private int id;
    int size;
    boolean activated;
    private String caption;
    private String description;
    private User author;
    private List<Question> questions;

    public Test() {
        questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                '}';
    }
}
