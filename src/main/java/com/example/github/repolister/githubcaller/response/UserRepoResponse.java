package com.example.github.repolister.githubcaller.response;

import java.util.List;

public record UserRepoResponse(String repositoryName, String ownerLogin, List<BranchResponse> branches) {
}
