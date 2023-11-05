package com.example.github.repolister.githubcaller.mapper;

import com.example.github.repolister.githubcaller.dto.GitRepository;
import com.example.github.repolister.githubcaller.response.BranchResponse;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface CleanResponseMapper {

    @Mapping(target = "ownerLogin", source = "gitRepository.owner.login")
    @Mapping(target = "repositoryName", source = "gitRepository.name")
    UserRepoResponse toCompleteResponse(GitRepository gitRepository, List<BranchResponse> branches);

}
