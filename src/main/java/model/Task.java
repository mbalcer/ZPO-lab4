package model;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date date;
    private Color colorEnum;

    public Task(int id, String name, String description, Date date, Color colorEnum) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.colorEnum = colorEnum;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Color getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(Color colorEnum) {
        this.colorEnum = colorEnum;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", colorEnum=" + colorEnum +
                '}';
    }

    public void showName() {
        System.out.println(this.name);
    }

    public void showDescription() {
        System.out.println(this.description);
    }

    public void showDate() {
        System.out.println(this.date);
    }

    public void showColor() {
        System.out.println(this.colorEnum);
    }
}
