<template>
    <b-modal id="committers" size="xl" title="List of Committers">
        <div class="wrapper">
            <b-table striped hover :items="committersData">
            </b-table>
        </div>
    </b-modal>
</template>

<script>

    export default {
        name: 'CommittersModal',
        props: {
            committersData: Array
        }
    }
</script>
