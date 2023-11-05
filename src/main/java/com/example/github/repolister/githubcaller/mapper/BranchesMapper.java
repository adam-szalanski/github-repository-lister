package com.example.github.repolister.githubcaller.mapper;

import com.example.github.repolister.githubcaller.dto.Branch;
import com.example.github.repolister.githubcaller.response.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface BranchesMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastCommitSha", source = "commit.sha")
    BranchResponse toResponse(Branch branch);
}
