<template>
    <b-modal id="committers" size="xl" title="List of Committers" @shown="getData">
        <div class="wrapper">
            <b-table striped hover :items="committersData">
            </b-table>
        </div>
    </b-modal>
</template>

<script>
    import RepositorySearchService from '../service/RepositorySearchService.js';
    export default {
        name: 'CommittersModal',
        props: {
            committersUrl: String
        },
        data(){
        return {
            committersData:[]
        }
    },
    methods:{
        getData()
        {
            RepositorySearchService.committersList(this.committersUrl).then(response=>
            {
                this.committersData = response.data;
            }
        );
        }
    }
    }
</script>
