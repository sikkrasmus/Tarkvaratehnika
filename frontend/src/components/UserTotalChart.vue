<template>
  <div id="chartdiv" style="width: 100%; height: 400px;"></div>
</template>

<script>
  export default {
    name: 'usertotalchart',
    data () {
      return {

      }
    },
    mounted () {
      var chart = AmCharts.makeChart("chartdiv", {
        "type": "serial",
        "theme": "light",
        "marginTop":0,
        "marginRight": 50,
        "dataProvider": [{
          "year": AmCharts.stringToDate("2018-04-08 17:34:12", "YYYY-MM-DD JJ-NN-SS"),
          "value": 500
        }, {
          "year": AmCharts.stringToDate("2018-04-09 17:34:12", "YYYY-MM-DD JJ-NN-SS"),
          "value": 600
        },{
          "year": AmCharts.stringToDate("2018-04-10 17:34:12", "YYYY-MM-DD JJ-NN-SS"),
          "value": 400
        },{
          "year": AmCharts.stringToDate("2018-04-11 06:34:12", "YYYY-MM-DD JJ-NN-SS"),
          "value": 300
        }],
        "valueAxes": [{
          "axisAlpha": 0,
          "position": "left"
        }],
        "graphs": [{
          "id":"g1",
          "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>",
          "bullet": "round",
          "bulletSize": 4,
          "lineColor": "#304FFE",
          "lineThickness": 3,
          "negativeLineColor": "#637bb6",
          "type": "smoothedLine",
          "valueField": "value"
        }],
        "chartScrollbar": {
          "graph":"g1",
          "gridAlpha":0,
          "color":"#888888",
          "scrollbarHeight":55,
          "backgroundAlpha":0,
          "selectedBackgroundAlpha":0.1,
          "selectedBackgroundColor":"#888888",
          "graphFillAlpha":0,
          "autoGridCount":true,
          "selectedGraphFillAlpha":0,
          "graphLineAlpha":0.2,
          "graphLineColor":"#304FFE",
          "selectedGraphLineColor":"#888888",
          "selectedGraphLineAlpha":1

        },
        "chartCursor": {
          "categoryBalloonDateFormat": "DD",
          "color": "#FFFFFF",
          "cursorColor": "#E91E63",
          "cursorAlpha": 0,
          "valueLineEnabled":true,
          "valueLineBalloonEnabled":true,
          "valueLineAlpha":0.5,
          "fullWidth":true
        },
        "dataDateFormat": "YYYY-MM-DD JJ-NN-SS",
        "categoryField": "year",
        "categoryAxis": {
          "minPeriod": "DD",
          "parseDates": true,
          "minorGridAlpha": 0.1,
          "minorGridEnabled": true
        },
        "export": {
          "enabled": true
        }
      });

      chart.addListener("rendered", zoomChart);
      if(chart.zoomChart){
        chart.zoomChart();
      }

      function zoomChart(){
        chart.zoomToIndexes(Math.round(chart.dataProvider.length * 0.4), Math.round(chart.dataProvider.length * 0.55));
      }
    }
  }
</script>

<style>
  #chartdiv {
    width	: 100%;
    height	: 500px;
  }
</style>
