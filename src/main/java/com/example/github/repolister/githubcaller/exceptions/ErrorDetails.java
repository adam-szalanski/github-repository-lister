package com.example.github.repolister.githubcaller.exceptions;

public record ErrorDetails(Integer status, String Message) {
    @Override
    public String toString() {
        return "{\"status\":" + status + ",\"Message\":\"" + Message + "\"}";
    }
}
