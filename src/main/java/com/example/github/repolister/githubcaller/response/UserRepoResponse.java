package com.example.github.repolister.githubcaller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRepoResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchResponse> branches;
}
