package com.example.github.repolister.githubcaller.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BranchDTO {
    private String name;
    private CommitDTO commit;
}
