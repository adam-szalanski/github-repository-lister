package com.example.github.repolister.githubcaller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRepoDTO {
    private String name;
    private OwnerDTO owner;
    private List<BranchDTO> branches;
}
