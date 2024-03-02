package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class  Person {
    private final IntegerProperty number;
    private final StringProperty name;

    public Person(int number, String name) {
        this.number = new SimpleIntegerProperty(number);
        this.name = new SimpleStringProperty(name);
    }

    // Méthodes getter pour accéder aux propriétés
    public IntegerProperty numberProperty() {
        return number;
    }

    public StringProperty nameProperty() {
        return name;
    }
}
