package com.fontaine.model;

import java.util.List;

/**
 * Created by Fazel on 12/22/2019.
 */
public class SearchResponse {

    private Integer total_count;
    private Boolean incomplete_results;
    private List<Item> items;

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
