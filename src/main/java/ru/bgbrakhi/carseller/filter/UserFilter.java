package ru.bgbrakhi.carseller;

public class UserFilter {
    private String mark;
    private Boolean today = false;
    private Boolean withphoto = false;

    public Boolean getToday() {
        return today;
    }

    public void setToday(Boolean today) {
        this.today = today;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Boolean getWithphoto() {
        return withphoto;
    }

    public void setWithphoto(Boolean withphoto) {
        this.withphoto = withphoto;
    }
}
