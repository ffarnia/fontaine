package com.fontaine.rest;

import com.fontaine.model.Author;
import com.fontaine.model.Contributor;
import com.fontaine.model.SearchResponse;
import com.fontaine.service.SearchRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Fazel on 12/22/2019.
 */


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchRepositoryServiceImpl repositoryService;

    @GetMapping(value = "/query/{query}")
    public SearchResponse SearchByNameInPublicRepository(@PathVariable(name = "query") String query) {
        return repositoryService.searchPublicRepo(query);
    }

    @GetMapping(value = "/contributors")
    public List<Contributor> getAllContributor(@RequestParam(name = "url") String contributorUrl) {
        return repositoryService.getCommitters(contributorUrl);
    }

    @GetMapping(value = "/commits/number")
    public Map<String, Long> committerActivity(@RequestParam(name = "url") String commitUrl) {
        return repositoryService.committerActivity(commitUrl.replace("{/sha}", ""));
    }

    @GetMapping(value = "/commits/timeline")
    public List<Author> committerTimeline(@RequestParam(name = "url") String commitUrl) {
        return repositoryService.projectionTimline(commitUrl.replace("{/sha}", ""));
    }
}
