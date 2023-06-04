package com.example.github.repolister.githubcaller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponse {
    private String name;
    private String lastCommitSha;
}
