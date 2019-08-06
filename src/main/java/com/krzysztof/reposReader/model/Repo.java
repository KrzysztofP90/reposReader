package com.krzysztof.reposReader.model;

public class Repo {

    private String name;
    private String owner;
    private String description;
    private String cloneURL;

    public Repo() {
    }

    public Repo(String name, String owner, String description, String cloneURL) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.cloneURL = cloneURL;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneURL() {
        return cloneURL;
    }
}
