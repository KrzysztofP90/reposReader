package com.krzysztof.reposReader.service;

import com.krzysztof.reposReader.model.Repo;
import org.springframework.stereotype.Service;

@Service
public class RepoService {

    public Repo getRepo(String owner, String repoName) {
        return new Repo(repoName,owner, "ExampleDescription", "ExampleCloneUrl");
    }
}
