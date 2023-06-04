package com.example.github.repolister.githubcaller;


import com.example.github.repolister.githubcaller.dto.BranchDTO;
import com.example.github.repolister.githubcaller.dto.UserRepoDTO;
import com.example.github.repolister.githubcaller.mapper.CleanResponseMapper;
import com.example.github.repolister.githubcaller.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class GitService {
    public GitService(CleanResponseMapper cleanResponseMapper) {
        this.cleanResponseMapper = cleanResponseMapper;
    }

    public static final String BASE_URL = "https://api.github.com/users/%s/repos";
    public static final String BRANCHES_URL = "https://api.github.com/repos/%s/%s/branches";
    private final CleanResponseMapper cleanResponseMapper;

    public ResponseEntity<?> getRepositories(String acceptHeader, String username) {
        String url = String.format(BASE_URL, username);
        RestTemplate restTemplate = new RestTemplate();
        UserRepoDTO[] repos = null;

        if (acceptHeader.equals("application/xml"))
            return getErrorResponse(HttpStatus.NOT_ACCEPTABLE);

        try {
            repos = restTemplate.getForObject(url, UserRepoDTO[].class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return getErrorResponse(HttpStatus.NOT_FOUND);
            }
        }

        for (UserRepoDTO userRepos : repos) {
            BranchDTO[] branches = restTemplate.getForObject(String.format(BRANCHES_URL, userRepos.getOwner().getLogin(), userRepos.getName()), BranchDTO[].class);
            userRepos.setBranches(branches);
        }

        return ResponseEntity.ok(Arrays.stream(repos).map(cleanResponseMapper::toCompleteResponse));
    }

    private static ResponseEntity<String> getErrorResponse(HttpStatus httpStatus) {
        return new ResponseEntity<>(new MessageResponse(httpStatus).toString(), httpStatus);
    }
}
