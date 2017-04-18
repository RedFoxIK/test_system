package ua.test.entity;

public class Answer {
    private int id;
    private String text;
    boolean rigth;
    Question question;

    public Answer() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRigth() {
        return rigth;
    }

    public void setRigth(boolean rigth) {
        this.rigth = rigth;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rigth=" + rigth +
                ", question=" + question +
                '}';
    }
}
