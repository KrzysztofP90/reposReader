package com.krzysztof.reposReader;

import com.krzysztof.reposReader.service.ReposLinksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepoLinksServiceTest {

    private List<String> actualList;

    @BeforeEach
    public void init() {
        ReposLinksService service = new ReposLinksService();
        this.actualList = service.getAllReposLinksForUser("KrzysztofP90");
    }

    @Test
    public void testIfFirstMiddleAndLastRepoLinksStartFromHttps() {
        int firstIndex = 0;
        int middleIndex = actualList.size() / 2;
        int lastIndex = actualList.size() - 1;
        String expectedHttps = "https://";
        String actualFirstHttps = actualList.get(firstIndex).substring(0,8);
        String actualMiddleHttps = actualList.get(middleIndex).substring(0,8);
        String actualLastHttps = actualList.get(lastIndex).substring(0,8);

        assertEquals(actualFirstHttps, expectedHttps);
        assertEquals(actualMiddleHttps, expectedHttps);
        assertEquals(actualLastHttps, expectedHttps);
    }

    @Test
    public void checkIfFirstMiddleAndLastRepoLinksContainCorrectGitHubAddressWithOwner() {
        String owner = "KrzysztofP90";
        int firstIndex = 0;
        int middleIndex = actualList.size() / 2;
        int lastIndex = actualList.size() - 1;
        String githubAddressWithOwner = "https://github.com/" + owner;

        assertTrue(actualList.get(firstIndex).contains(githubAddressWithOwner));
        assertTrue(actualList.get(middleIndex).contains(githubAddressWithOwner));
        assertTrue(actualList.get(lastIndex).contains(githubAddressWithOwner));
    }

}
