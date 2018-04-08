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
    created () {
      var chart = AmCharts.makeChart("chartdiv", {
        "type": "serial",
        "theme": "light",
        "marginTop":0,
        "marginRight": 80,
        "dataProvider": [{
          "year": "2222",
          "value": -0.307
        }, {
          "year": "2223",
          "value": -0.168
        },{
          "year": "2224",
          "value": -0.2
        },{
          "year": "2225",
          "value": -0.3
        }],
        "valueAxes": [{
          "axisAlpha": 0,
          "position": "left"
        }],
        "graphs": [{
          "id":"g1",
          "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>",
          "bullet": "round",
          "bulletSize": 8,
          "lineColor": "#d1655d",
          "lineThickness": 2,
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
          "graphLineColor":"#c2c2c2",
          "selectedGraphLineColor":"#888888",
          "selectedGraphLineAlpha":1

        },
        "chartCursor": {
          "categoryBalloonDateFormat": "YYYY",
          "cursorAlpha": 0,
          "valueLineEnabled":true,
          "valueLineBalloonEnabled":true,
          "valueLineAlpha":0.5,
          "fullWidth":true
        },
        "dataDateFormat": "YYYY",
        "categoryField": "year",
        "categoryAxis": {
          "minPeriod": "YYYY",
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
