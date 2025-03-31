package com.example.ns_travels.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    USER, ADMIN; // Add other roles if needed

    @JsonCreator
    public static Role fromString(String value) {
        return valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}