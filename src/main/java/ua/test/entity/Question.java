package ua.test.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String text;
    boolean multChoice;
    List<Answer> answers;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isMultChoice() {
        return multChoice;
    }

    public void setMultChoice(boolean multChoice) {
        this.multChoice = multChoice;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswers(List<Answer> answers ) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' + "answers" + answers +
                '}';
    }
}
