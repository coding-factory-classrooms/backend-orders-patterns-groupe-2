package org.example;

import org.example.models.Clothes;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum State {
        NEW,
        IN_PROGRESS,
        PROCESSED,
        CANCELLED,
    }
    private final List<Clothes> clothesList;
    private State state;

    public List<Clothes> getClothesList() {
        return clothesList;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Order() {
        state = State.NEW;
        clothesList = new ArrayList<>();
    }

    public void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }
}
