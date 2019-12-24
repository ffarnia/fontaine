import axios from "axios";

const SEARCH_ENDPOINT = "http://127.0.0.1:8082/search/query/";
const CONTRIBUTOR_ENDPOINT = "http://127.0.0.1:8082/search/contributors";
const COMMIT_NUMBER_ENDPOINT = "http://127.0.0.1:8082/search/commits/number";
const COMMIT_TIMELINE_ENDPOINT = "http://127.0.0.1:8082/search/commits/timeline";

class RepositorySearchService {

    searchRepository(name) {
        return axios.get(`${SEARCH_ENDPOINT}${name}`);
    }

    committersList(url) {
        return axios.get(`${CONTRIBUTOR_ENDPOINT}?url=${url}`);
    }

    numberOfCommits(url) {
        return axios.get(`${COMMIT_NUMBER_ENDPOINT}?url=${url}`);
    }

    commitTimeline(url) {
        return axios.get(`${COMMIT_TIMELINE_ENDPOINT}?url=${url}`);
    }

}

export default new RepositorySearchService();