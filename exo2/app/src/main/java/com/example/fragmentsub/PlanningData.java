package com.example.fragmentsub;

public class PlanningData {
    private final String slot0810;
    private final String slot1012;
    private final String slot1416;
    private final String slot1618;

    public PlanningData(String slot0810, String slot1012, String slot1416, String slot1618) {
        this.slot0810 = slot0810;
        this.slot1012 = slot1012;
        this.slot1416 = slot1416;
        this.slot1618 = slot1618;
    }

    public String getSlot0810() {
        return slot0810;
    }

    public String getSlot1012() {
        return slot1012;
    }

    public String getSlot1416() {
        return slot1416;
    }

    public String getSlot1618() {
        return slot1618;
    }
}

