package com.example.github.repolister.githubcaller.mapper;

import com.example.github.repolister.githubcaller.dto.BranchDTO;
import com.example.github.repolister.githubcaller.response.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class BranchesMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastCommitSha", source = "commit.sha")
    public abstract BranchResponse toResponse(BranchDTO branchDTO);
}
