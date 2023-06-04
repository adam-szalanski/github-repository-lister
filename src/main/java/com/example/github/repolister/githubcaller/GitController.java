package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitController {

    private final GitService gitService;

    @GetMapping("/{username}")
    public Flux<UserRepoResponse> getRepositories(@PathVariable("username") String username, @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        return gitService.getRepositories(username);
    }
}
