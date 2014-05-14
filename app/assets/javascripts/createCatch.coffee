class Catch
  constructor: (json) ->
    @place = json.place
    @fish = json.fish

class Weather
  constructor: (json) ->
    @time = json.time
    @condition = json.condition
    @temp = json.temp
    @pressure = json.pressure
    @humidity = json.humidity

class CatchViewModel
  constructor: () ->
    self = @
    @place = ko.observable("")
    @fish = ko.observable("")
    @weatherList = ko.observableArray([])

    # init calender
    $('.datepicker').datepicker(
      autoclose: true
      todayHighlight: true
    ).on('changeDate', (event) ->
      self.onChangeDate(event)
    )

    # init canvas
    @initCanvas()

  onChangeDate: (event) ->
    self = @
    time = event.date.getTime()
    url = routes.controllers.WeatherController.findByDate(time).url
    $.getJSON(url, (json) ->
      loadedWeatherList = $.map(json, (weatherJson) ->
        new Weather(weatherJson))
      self.weatherList(loadedWeatherList)
      alert(json)
    )

  submit: () ->
    catchObj =
      fish: @fish()
      place: @place()

    @catches.push(catchObj)

    $.ajax(
      contentType: "application/json"
      type: "post"
      data: JSON.stringify(catchObj)
      url: routes.controllers.CatchController.create().url
    )

  initCanvas: () ->
    self = @
    dps = [
      { x: new Date(2012, 1, 1, 1,0), y: 1 },
      { x: new Date(2012, 1, 1, 2,0), y: 2}
    ]

    year = 2012
    month = 1
    date = 1

    chart = new CanvasJS.Chart("chartContainer", {
      theme: "theme2",
      title: {
        text: "Earthquakes - per month"
      },
      axisX: {
        title: "time",
        valueFormatString: "H:mm",
        maximum: new Date(year, month, date, 24, 0)
        interval: 1,
        intervalType: "hour"
      },
      axisY: {
        title: "fish activity"
        includeZero: true
        interval: 1
        maximum: 5
      },
      data: [
        {
          type: "line",
        #lineThickness: 3,
          dataPoints: dps,
          click: (e) ->
            alert("clicked" + e)
        }
      ]
    });
    chart.render()
    $("canvas")[1].addEventListener("click", (e) ->
      newDataPoint = {
        x: self.getXValueAt(chart, e, year, month, date)
        y: self.getYValueAt(chart, e)
      }
#      alert(newDataPoint.x + " " + newDataPoint.y)
      dps.push(newDataPoint)
      chart.render()
    );
#    setInterval( ->
##      dps[0].y = dps[0].y + 5
##      chart.render();
#      1
#    , 1000)

  getYValueAt: (chart, e) ->
    c = chart._chart
    (c.axisY.maximum - c.axisY.minimum) / c.axisY.lineCoordinates.height * (c.axisY.lineCoordinates.y2 - e.layerY) + c.axisY.minimum

  getXValueAt: (chart, e, year, mont, date) ->
    absTimeInMs = chart._chart.axisX.getXValueAt({x: e.layerX, y: e.layerY})
    absTimeInMs
#    oneHourInMs = new Date(2012, 1, 1, 1, 0).getTime() - new Date(2012, 1, 1, 0, 0).getTime()
#    (absTimeInMs - new Date(year, mont, date, 0, 0).getTime()) / oneHourInMs

#  getYValueAt:

$(() ->
  ko.applyBindings(new CatchViewModel)
)