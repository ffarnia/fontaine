<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container">
        <h3>All Courses</h3>
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
            <CommittersModal :committersData="committersData"/>
            <CommitNubmersModal :commitNumbers="commitNumbers"  :commitOptions="commitOptions" :pieData="pieData"/>
            <CommitTimelineModal  :timelineData="timelineData"/>

            <b-table striped hover :items="repositories" :fields="fields" caption-top>
                <template v-slot:table-caption>Total count of search result: {{total_repo}}</template>

                <template v-slot:cell(show_details)="row">
                    <b-button @click="onClick(row.item.contributors_url)">
                        Committers
                    </b-button>
                        <b-button @click="getCommitNumbers(row.item.commits_url)">
                            Commit Numbers
                        </b-button>
                    <b-button @click="commitTimeline(row.item.commits_url)">
                        Timeline
                    </b-button>

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
            committersData: [],
            commitNumbers:[],
            commitOptions:[],
            pieData: [],
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
        )
            ;
        }
    ,
        onClick(url)
        {
            RepositorySearchService.committersList(url).then(response=>
            {
                this.committersData = response.data;
            }
        )
            ;
            this.$bvModal.show('committers');
        },

        getCommitNumbers(url)
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
            }
        )
            ;
            this.$bvModal.show('commit-numbers');
        },
        commitTimeline(url)
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

            }
        )
            ;
            this.$bvModal.show('commit-timeline');
        }

    }
    }
</script>

<style>
</style>