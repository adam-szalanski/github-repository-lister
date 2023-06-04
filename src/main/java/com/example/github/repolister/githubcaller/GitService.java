package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.dto.BranchDTO;
import com.example.github.repolister.githubcaller.dto.UserRepoDTO;
import com.example.github.repolister.githubcaller.mapper.CleanResponseMapper;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Service
public class GitService {
    public GitService(CleanResponseMapper cleanResponseMapper, RestTemplateBuilder restTemplateBuilder) {
        this.cleanResponseMapper = cleanResponseMapper;
        this.restTemplate = restTemplateBuilder.build();
    }

    public static final String BASE_URL = "https://api.github.com/users/%s/repos";
    public static final String BRANCHES_URL = "https://api.github.com/repos/%s/%s/branches";
    private final CleanResponseMapper cleanResponseMapper;
    private final RestTemplate restTemplate;

    //TODO: tests
    public Flux<UserRepoResponse> getRepositories(String username) {
        String url = String.format(BASE_URL, username);
        UserRepoDTO[] repos = restTemplate.getForObject(url, UserRepoDTO[].class);

        //TODO: exception handling
//        if (acceptHeader.equals("application/xml"))
//            return getErrorResponse(HttpStatus.NOT_ACCEPTABLE);
//

        for (UserRepoDTO userRepos : repos) {
            BranchDTO[] branches = restTemplate.getForObject(String.format(BRANCHES_URL, userRepos.getOwner().login(), userRepos.getName()), BranchDTO[].class);
            userRepos.setBranches(branches);
        }

        return Flux.fromArray(Arrays.stream(repos).map(cleanResponseMapper::toCompleteResponse).toArray(UserRepoResponse[]::new));
    }
}
