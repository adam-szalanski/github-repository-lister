package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.dto.BranchDTO;
import com.example.github.repolister.githubcaller.dto.UserRepoDTO;
import com.example.github.repolister.githubcaller.mapper.CleanResponseMapper;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitService {
    public GitService(CleanResponseMapper cleanResponseMapper, WebClient.Builder webClientBuilder) {
        this.cleanResponseMapper = cleanResponseMapper;
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public static final String BASE_URL = "https://api.github.com";
    public static final String REPOS_URL = "/users/{username}/repos";
    public static final String BRANCHES_URL = "/repos/{login}/{repo_name}/branches";
    private final CleanResponseMapper cleanResponseMapper;
    private final WebClient webClient;
    public Flux<UserRepoResponse> getRepositories(String username) {
        Flux<UserRepoDTO> reposRetrieved = webClient.get().uri(REPOS_URL,username).retrieve().bodyToFlux(UserRepoDTO.class);
        List<UserRepoDTO> repos = reposRetrieved.collectList().block();

        for (UserRepoDTO userRepos : repos) {
            Flux<BranchDTO> branchesRetrieved = webClient.get().uri(BRANCHES_URL, userRepos.getOwner().login(), userRepos.getName()).retrieve().bodyToFlux(BranchDTO.class);
            List<BranchDTO> branches = branchesRetrieved.collectList().block();
            userRepos.setBranches(branches);
        }
        return Flux.fromIterable(repos.stream().map(cleanResponseMapper::toCompleteResponse).collect(Collectors.toList()));
    }
}
