package com.example.github.repolister.githubcaller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitController {

    private final GitService gitService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable("username") String username, @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        return gitService.getRepositories(acceptHeader, username);
    }
}
