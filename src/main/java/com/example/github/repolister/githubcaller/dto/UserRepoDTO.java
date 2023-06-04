package com.example.github.repolister.githubcaller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRepoDTO {
    private String name;
    private OwnerDTO owner;
    private BranchDTO[] branches;
}
