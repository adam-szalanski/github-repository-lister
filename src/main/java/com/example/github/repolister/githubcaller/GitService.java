package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.dto.Branch;
import com.example.github.repolister.githubcaller.dto.GitRepository;
import com.example.github.repolister.githubcaller.mapper.BranchesMapper;
import com.example.github.repolister.githubcaller.mapper.CleanResponseMapper;
import com.example.github.repolister.githubcaller.response.BranchResponse;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GitService {

    public static final String BASE_URL = "https://api.github.com";
    public static final String REPOS_URL = "/users/{username}/repos";
    public static final String BRANCHES_URL = "/repos/{login}/{repo_name}/branches";
    private final CleanResponseMapper cleanResponseMapper;
    private final BranchesMapper branchesMapper;
    private final WebClient webClient;

    public GitService(CleanResponseMapper cleanResponseMapper, WebClient.Builder webClientBuilder, BranchesMapper branchesMapper) {
        this.cleanResponseMapper = cleanResponseMapper;
        this.branchesMapper = branchesMapper;
        webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public Flux<UserRepoResponse> listRepositories(String username) {
        Flux<GitRepository> repositories = fetchUserRepositories(username);
        return repositories.flatMap(this::mapToResponse);
    }

    private Flux<GitRepository> fetchUserRepositories(String username) {
        return webClient.get().uri(REPOS_URL, username).retrieve().bodyToFlux(GitRepository.class);
    }

    private Mono<UserRepoResponse> mapToResponse(GitRepository gitRepository) {
        Flux<BranchResponse> foundBranches = fetchRepositoryBranches(gitRepository).map(branchesMapper::toResponse);
        return foundBranches.collectList()
                .map(branchResponses -> cleanResponseMapper.toCompleteResponse(gitRepository, branchResponses));
    }

    private Flux<Branch> fetchRepositoryBranches(GitRepository gitRepository) {
        return webClient.get().uri(BRANCHES_URL, gitRepository.owner().login(), gitRepository.name()).retrieve().bodyToFlux(Branch.class);
    }
}
