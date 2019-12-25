<template>
    <div>
    <b-modal id="commit-timeline" size="l" title="Projection of commits on timeline">
    <light-timeline :items='items'></light-timeline>
        </b-modal>
    </div>
</template>
<script>
    export default {
        name: 'CommitTimelineModal',
        props:{
            timelineData: Array
        },
        data () {
        return {
            items: this.timelineData
        }
    }
    }
</script>
