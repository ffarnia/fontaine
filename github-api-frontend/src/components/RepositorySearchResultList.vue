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
            <b-table striped hover :items="repositories" :fields="fields" caption-top>
                <template v-slot:table-caption>Total count of search result: {{total_repo}}</template>

                <template v-slot:cell(show_details)="row">
                    <b-form-checkbox v-model="row.detailsShowing" @change="row.toggleDetails">
                        Details via check
                    </b-form-checkbox>

                    <b-button @click="onClick(row.item.contributors_url)">
                        Committers
                    </b-button>
                </template>

                <template v-slot:row-details="row">
                    <b-card>
                        <b-row class="mb-2">
                            <b-col sm="3" class="text-sm-right"><b>List of committers:</b></b-col>
                        </b-row>

                        <b-row class="mb-2">
                            <b-col sm="3" class="text-sm-right"><b>Is Active:</b></b-col>
                        </b-row>

                    </b-card>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>
    import RepositorySearchService from '../service/RepositorySearchService.js';
    import CommittersModal from './CommittersModal.vue';

    export default {
        name: 'RepositoriesList',
        components: {
            CommittersModal: CommittersModal
        },
        data()
    {
        return {
            repositories: [],
            total_repo: 0,
            search: {text:""},
            fields: ['id','full_name','description','show_details'],
            committersData: []
        }
    }
    ,
    methods:{
        searchRepo()
        {
            RepositorySearchService.searchRepository(this.search.text).then(response =>
            {
//                console.log(response.data);
                this.repositories = response.data.items;
                this.total_repo = response.data.total_count;
            }
        )
            ;
        },
        onClick(url) {
            RepositorySearchService.committersList(url).then(response =>
            {
//                console.log(response.data);
                this.committersData = response.data;
            });
            this.$bvModal.show('committers');
        }

    }
    ,
    created()
    {
//        this.searchRepo();
    }
    }
</script>

<style>
</style>