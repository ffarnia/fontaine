package com.fontaine.service;

import com.fontaine.model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Fazel on 12/22/2019.
 */
@Service
public class SearchRepositoryServiceImpl implements SearchRepositoryService {

    private String search_endpoint = "https://api.github.com/search/repositories";

    @Override
    public SearchResponse searchPublicRepo(String name) {
        ResponseEntity<SearchResponse> responseEntity = new RestTemplate().getForEntity(search_endpoint + "?q={query}", SearchResponse.class, name);
        return responseEntity.getBody();
    }

    @Override
    public List<Contributor> getCommitters(String contributor) {
        ResponseEntity<List<Contributor>> responseEntity = new RestTemplate().exchange(contributor, HttpMethod.GET, null, new ParameterizedTypeReference<List<Contributor>>() {
        });
        return responseEntity.getBody();
    }

    @Override
    public Map<String, Long> committerActivity(String commit) {
        ResponseEntity<List<CommitResponse>> listResponseEntity = new RestTemplate().exchange(commit, HttpMethod.GET, null, new ParameterizedTypeReference<List<CommitResponse>>() {
        });

        List<CommitResponse> commitResponses = listResponseEntity.getBody();
        Map<String, Long> map = commitResponses.stream().filter(e -> e.getCommit().getVerification().getVerified()).collect(Collectors.groupingBy(e -> e.getAuthor().getLogin(), Collectors.counting()));
        return map;
    }


    @Override
    public List<Author> projectionTimline(String commit) {
        ResponseEntity<List<CommitResponse>> listResponseEntity = new RestTemplate().exchange(commit, HttpMethod.GET, null, new ParameterizedTypeReference<List<CommitResponse>>() {
        });

        List<CommitResponse> commitResponses = listResponseEntity.getBody();
        List<Author> commits = commitResponses.stream().filter(e -> e.getCommit().getVerification().getVerified()).map(e -> e.getCommit().getAuthor()).collect(Collectors.toList());
        return commits;
    }


}
