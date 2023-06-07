package com.example.github.repolister.githubcaller;

import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitControllerTest {
    @Mock
    private GitService gitService;

    @InjectMocks
    private GitController gitController;

    private static Flux<UserRepoResponse> expectedResponse;
    private static UserRepoResponse[] response;
    private static String TEST_USERNAME_1 = "test";

    @BeforeEach
    void setup() {
        UserRepoResponse repo1 = new UserRepoResponse();
        repo1.setRepositoryName("repo1");
        repo1.setOwnerLogin("johndoe");

        UserRepoResponse repo2 = new UserRepoResponse();
        repo2.setRepositoryName("repo2");
        repo2.setOwnerLogin("johndoe");

        response = new UserRepoResponse[]{repo1, repo2};
        expectedResponse = Flux.fromArray(response);
    }

    @Test
    void getRepositories_correctly() {
        //given
        given(gitService.getRepositories(TEST_USERNAME_1)).willReturn(expectedResponse);

        //when
        Flux<UserRepoResponse> responseFromController = gitController.getRepositories(TEST_USERNAME_1);

        //then
        assertThat(responseFromController).isEqualTo(expectedResponse);
        verify(gitService, times(1)).getRepositories(TEST_USERNAME_1);
    }
}