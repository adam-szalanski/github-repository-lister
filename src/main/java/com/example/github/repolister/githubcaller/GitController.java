package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitController {

    private final GitService gitService;

    @GetMapping(value = "/{username}",produces = "application/json")
    public Flux<UserRepoResponse> getRepositories(@PathVariable("username") String username) {
        return gitService.getRepositories(username);
    }
}
