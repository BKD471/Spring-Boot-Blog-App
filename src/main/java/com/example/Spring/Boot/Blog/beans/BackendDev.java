package com.example.Spring.Boot.Blog.beans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackendDev {
    private final String name;
    private final String framework;
    private final long salary;

    public BackendDev(String name, String framework, long salary) {
        this.name = name;
        this.framework = framework;
        this.salary = salary;
    }
}
