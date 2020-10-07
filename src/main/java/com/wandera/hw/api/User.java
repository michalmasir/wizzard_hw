package com.wandera.hw.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String userGuid;

    public User(String userGuid) {
        this.userGuid = userGuid;
    }

    public User() {
    }

    @JsonProperty
    public String getUserGuid() {
        return userGuid;
    }
    @JsonProperty
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
