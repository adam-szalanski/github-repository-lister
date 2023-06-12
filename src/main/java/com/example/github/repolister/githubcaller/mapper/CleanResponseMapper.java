package com.example.github.repolister.githubcaller.mapper;

import com.example.github.repolister.githubcaller.dto.UserRepoDTO;
import com.example.github.repolister.githubcaller.response.UserRepoResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CleanResponseMapper {

    @Autowired
    private BranchesMapper branchesMapper;

    @Mapping(target = "repositoryName", source = "name")
    @Mapping(target = "ownerLogin", source = "owner.login")
    public abstract UserRepoResponse toCompleteResponse(UserRepoDTO userRepoDTO);

    @AfterMapping
    protected void branchMapping(@MappingTarget UserRepoResponse userRepoResponse, UserRepoDTO userRepoDTO) {
        userRepoResponse.setBranches(userRepoDTO.getBranches().stream().map(branchesMapper::toResponse).toList());
    }
}
