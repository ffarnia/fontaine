<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container">
        <h3>GitHub Repository Search</h3>
        <div>
            <b-form-input
                    @input="searchRepo()"
                    v-model="search.text"
                    type="text"
                    placeholder="Search by Name"
                    >
            </b-form-input>
        <span class="search-icon">
          <i class="fas fa-search"></i>
        </span>
        </div>
        <div>
            <CommittersModal :committersUrl="committersUrl" />
            <CommitNubmersModal :pieData="pieData"/>
            <CommitTimelineModal  :timelineData="timelineData"/>

            <b-table striped hover :items="repositories" :fields="fields" caption-top>

                    <template v-slot:table-caption>Total count of search result: {{total_repo}}
                        <b-row >
                            <b-col md="8"></b-col>
                        <b-col align-self="end" md="4" offset-md="4">
                            <div>
                            <b-button @click="saveBookmark">bookmark</b-button>
                                &nbsp;
                                <b-button @click="loadLastBookmark">load latest bookmark</b-button>
                            </div>
                        </b-col>
                        </b-row>
                    </template>

                <template v-slot:cell(show_details)="row">
                    <div class="mt-3">
                        <b-button-group>
                            <b-button variant="success" @click="openCommittersModal(row.item.contributors_url)">Committers</b-button>
                            <b-button variant="info" @click="openCommitNumbers(row.item.commits_url)">Numbers</b-button>
                            <b-button variant="warning" @click="openCommitTimeline(row.item.commits_url)">Timeline</b-button>
                        </b-button-group>
                    </div>

                </template>

            </b-table>
        </div>
    </div>
</template>

<script>
    import RepositorySearchService from '../service/RepositorySearchService.js';
    import CommittersModal from './CommittersModal.vue';
    import CommitNubmersModal from './CommitNubmersModal.vue';
    import CommitTimelineModal from './CommitTimelineModal.vue';

    export default {
        name: 'RepositoriesList',
        components: {
            CommittersModal: CommittersModal,
            CommitNubmersModal: CommitNubmersModal,
            CommitTimelineModal: CommitTimelineModal
        },
        data()
    {
        return {
            repositories: [],
            total_repo: 0,
            search: {text:""},
            fields: ['id','full_name','description','show_details'],
            pieData: [],
            committersUrl: '',
            timelineData: [{tag:'',content:''}]
        }
    }
    ,
    methods:{
        searchRepo()
        {
            RepositorySearchService.searchRepository(this.search.text).then(response=>
            {
                this.repositories = response.data.items;
                this.total_repo = response.data.total_count;
            }
        );

        },
        openCommittersModal(url)
        {
            this.committersUrl = url;
            this.$bvModal.show('committers');
        },

        openCommitNumbers(url)
        {

            RepositorySearchService.numberOfCommits(url.replace("{/sha}","")).then(response=>
            {
                var map = new Map();
                map = response.data;
                Object.keys(map).map( k => {
                let pair={};
                pair.name = k;
                pair.value = map[k];
                this.pieData.push(pair);
            });
            });

            this.$bvModal.show('commit-numbers');
        },

        openCommitTimeline(url)
        {
            RepositorySearchService.commitTimeline(url.replace("{/sha}","")).then(response=>
            {
                let commitList = response.data;
                commitList.forEach(e => {
                let timeObj = {};
                timeObj.tag = new Date(e.date).toLocaleString();
                timeObj.content = e.email;
                this.timelineData.push(timeObj);
            });
            });
            this.$bvModal.show('commit-timeline');
        },

        saveBookmark(){
            localStorage.setItem("searchrepo", JSON.stringify(this.repositories));
            localStorage.setItem("totalrepo", JSON.stringify(this.total_repo));
        },
        loadLastBookmark(){
            this.repositories=JSON.parse(localStorage.getItem("searchrepo"));
            this.total_repo=JSON.parse(localStorage.getItem("totalrepo"));
        }
    },
    }
</script>

<style>
</style>