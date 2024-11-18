package model;

import java.time.LocalDateTime;

public class Currency {

    private String code;
    private String name;
    private LocalDateTime timestamp;

    //constructor
    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
