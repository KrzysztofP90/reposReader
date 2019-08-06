package com.krzysztof.reposReader.controller;

import com.krzysztof.reposReader.model.Repo;
import com.krzysztof.reposReader.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoController {


    private RepoService repoService;

    @Autowired
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/{owner}/{repoName}")
    public ResponseEntity<?> getRepo(@PathVariable("owner") String owner, @PathVariable("repoName") String repoName ) {
        Repo repo = this.repoService.getRepo(owner, repoName);
        return new ResponseEntity<>(repo, HttpStatus.OK);
    }
}
