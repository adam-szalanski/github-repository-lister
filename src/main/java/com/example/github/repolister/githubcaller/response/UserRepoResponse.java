package com.example.github.repolister.githubcaller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRepoResponse {
    private String repositoryName;
    private String ownerLogin;
    private BranchResponse[] branches;
}
