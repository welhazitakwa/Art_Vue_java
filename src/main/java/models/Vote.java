package models;

import java.util.Date;

public class Vote {

    private static int id;
    private static int note;

    public Vote(int id,int note) {
        this.id = id;
        this.note =note;
    }

    public Vote(int note) {

        this.note =note;
    }

    public Vote() {

    }

    public static int getId() {
        return id;
    }



    public static void setId(int id) {
        Vote.id = id;
    }

    public static int getNote() {
        return note;
    }

    public static void setNote(int note) {
        Vote.note = note;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", note='" + note+ '\'' +
                '}';
    }
}