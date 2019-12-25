package com.fontaine.service;

import com.fontaine.model.Author;
import com.fontaine.model.Contributor;
import com.fontaine.model.SearchResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Fazel on 12/22/2019.
 */
public interface SearchRepositoryService {
    SearchResponse searchPublicRepo(String name);

    List<Contributor> getCommitters(String contributor);

    Map<String, Long> committerActivity(String commit);

    List<Author> projectionTimline(String commit);

}
