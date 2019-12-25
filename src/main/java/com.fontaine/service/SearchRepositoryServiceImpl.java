package com.fontaine.service;

import com.fontaine.model.Author;
import com.fontaine.model.CommitResponse;
import com.fontaine.model.Contributor;
import com.fontaine.model.SearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Fazel on 12/22/2019.
 */
@Service
public class SearchRepositoryServiceImpl implements SearchRepositoryService {

    @Value("${github.search.endpoint}")
    private String search_endpoint;

    @Override
    public SearchResponse searchPublicRepo(String name) {
        ResponseEntity<SearchResponse> responseEntity = null;
        try {
            responseEntity = new RestTemplate().getForEntity(search_endpoint + "?q={query}", SearchResponse.class, name);
        } catch (RestClientException e) {
            throw new RuntimeException("Error in connecting github api");
        }
        return responseEntity.getBody();
    }

    @Override
    public List<Contributor> getCommitters(String contributor) {
        ResponseEntity<List<Contributor>> responseEntity = null;
        try {
            responseEntity = new RestTemplate().exchange(contributor, HttpMethod.GET, null, new ParameterizedTypeReference<List<Contributor>>() {
            });
        } catch (RestClientException e) {
            throw new RuntimeException("Error in connecting github api");
        }
        return responseEntity.getBody();
    }

    @Override
    public Map<String, Long> committerActivity(String commit) {
        Map<String, Long> map = getCommitResponse(commit).stream().filter(e -> e.getCommit().getVerification().getVerified()).collect(Collectors.groupingBy(e -> e.getAuthor().getLogin(), Collectors.counting()));
        return map;
    }

    @Override
    public List<Author> projectionTimline(String commit) {
        List<Author> commits = getCommitResponse(commit).stream().filter(e -> e.getCommit().getVerification().getVerified()).map(e -> e.getCommit().getAuthor()).collect(Collectors.toList());
        return commits;
    }

    private List<CommitResponse> getCommitResponse(String commit) {
        ResponseEntity<List<CommitResponse>> listResponseEntity = null;
        try {
            listResponseEntity = new RestTemplate().exchange(commit, HttpMethod.GET, null, new ParameterizedTypeReference<List<CommitResponse>>() {
            });
        } catch (RestClientException e) {
            throw new RuntimeException("Error in connecting github api");
        }
        return listResponseEntity.getBody();
    }

}
