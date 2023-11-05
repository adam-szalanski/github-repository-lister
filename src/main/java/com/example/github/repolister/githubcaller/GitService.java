package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.dto.Branch;
import com.example.github.repolister.githubcaller.dto.GitRepository;
import com.example.github.repolister.githubcaller.mapper.BranchesMapper;
import com.example.github.repolister.githubcaller.mapper.CleanResponseMapper;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
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
        List<GitRepository> repositories = fetchUserRepositories(username).collectList().block();
        List<UserRepoResponse> responses = repositories.stream().map(
                gitRepository -> fetchRepositoryBranches(gitRepository)
                        .map(branchesMapper::toResponse)
                        .collectList()
                        .map(branchResponses -> cleanResponseMapper.toCompleteResponse(gitRepository, branchResponses))
                        .block()
        ).toList();
        return Flux.fromIterable(responses);
    }

    private Flux<Branch> fetchRepositoryBranches(GitRepository gitRepository) {
        return webClient.get().uri(BRANCHES_URL, gitRepository.owner().login(), gitRepository.name()).retrieve().bodyToFlux(Branch.class);
    }

    private Flux<GitRepository> fetchUserRepositories(String username) {
        return webClient.get().uri(REPOS_URL, username).retrieve().bodyToFlux(GitRepository.class);
    }
}
