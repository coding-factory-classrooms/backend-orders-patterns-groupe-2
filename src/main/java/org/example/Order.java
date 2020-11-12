package org.example;

import org.example.models.Clothes;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public interface onStateOrderChangedListener {
        void onStateChanged(Order order);
    }

    private onStateOrderChangedListener stateOrderChangedListener;

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
        if(stateOrderChangedListener != null){
            stateOrderChangedListener.onStateChanged(this);
        }
    }

    public void setStateOrderChangedListener(onStateOrderChangedListener stateOrderChangedListener){
        this.stateOrderChangedListener = stateOrderChangedListener;
    }

    public Order() {
        state = State.NEW;
        clothesList = new ArrayList<>();
    }

    public void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }

    @Override
    public String toString() {
        return "Order{" +
                "clothesList=" + clothesList +
                ", state=" + state +
                '}';
    }
}
