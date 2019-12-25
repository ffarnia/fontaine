<template>
    <b-modal id="commit-numbers" size="xl" title="Impact of committers base on number of commits">

        <div>
            <v-chart :options="pie"
                     autoresize />
        </div>
    </b-modal>

</template>

<script>
    import 'echarts/lib/chart/pie'

    export default {
        name: 'CommitNumbersModal',
        props: {
            commitOptions: Array,
            commitNumbers: Array,
            pieData: Array
        },
        components: {
//            'v-chart': ECharts
        },
        data () {

        return {
            pie: {
                title: {
                    text: "TEXT",
                    subtext: "SUBTEXT",
                    x:"center"
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: "vertical",
                    left: "left",
                    data:this.pieData
                },
                series: [
                    {
                        name: 'NAME',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:this.pieData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            }
        }
    }
    }
</script>

<style>
    /**
     * The default size is 600px×400px, for responsive charts
     * you may need to set percentage values as follows (also
     * don't forget to provide a size for the container).
     */
    .echarts {
        width: 100%;
        height: 100%;
    }
</style>