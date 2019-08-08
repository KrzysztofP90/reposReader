package com.krzysztof.reposReader.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReposLinksService {

    private List<String> listOfUserReposLinks;

    public ReposLinksService() {
        this.listOfUserReposLinks = new ArrayList<>();
    }

    public List<String> getAllReposLinksForUser(String user) {
        getAllUserRepos(createURL(user), user);
        /// print to check
        Collections.singletonList(listOfUserReposLinks).forEach(System.out::println);
        System.out.println("Count of repos for " + user + ":" + listOfUserReposLinks.size());
        return this.listOfUserReposLinks;
    }

    private void getAllUserRepos(String websiteUrl, String user) {
        try {
            URL url = new URL(websiteUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines()
                    .filter(line -> line.contains("<a href=\"/" + user) || line.contains("tab=repositories\">Next"))
                    .forEach(link -> addLinkToListOrGetNextReposStream(link, user) );
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Please add protocol to web site url!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Please check web site url!");
        }
    }

    private String createURL(String owner) {
        return "https://github.com/" + owner + "?tab=repositories";
    }

    private void addLinkToListOrGetNextReposStream(String line, String user) {
        if (line.contains("tab=repositories\">Next")) {
            int previousIndex = line.indexOf("Previous");
            int hrefIndex = line.indexOf("href", previousIndex);
            int startIndex = line.indexOf("\"", hrefIndex) + 1;
            int endIndex = line.indexOf("\"", startIndex + 1);
            line = line.substring(startIndex, endIndex);
            getAllUserRepos(line, user);
        }
        else if(line.contains("<a href=\"/" + user)) {
            int startIndex = line.indexOf("\"") + 1;
            int endIndex = line.indexOf("\"", startIndex + 1);
            line = "https://github.com" + line.substring(startIndex, endIndex);
            listOfUserReposLinks.add(line);
        }
    }

}
