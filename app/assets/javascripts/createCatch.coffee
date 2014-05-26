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
    @date = ""

    # init calender
    $('.datepicker').datepicker(
      autoclose: true
      todayHighlight: true
    ).on('changeDate', (event) ->
      self.date = event.date.getTime()
    )

#  onChangeDate: (event) ->
#    self = @
#    time = event.date.getTime()
#    url = routes.controllers.WeatherController.findByDate(time).url
#    $.getJSON(url, (json) ->
#      loadedWeatherList = $.map(json, (weatherJson) ->
#        new Weather(weatherJson))
#      self.weatherList(loadedWeatherList)
#      alert(json)
#    )

  submit: () ->
    catchObj =
      fish: @fish()
      place: @place()
      date: @date

    $.ajax(
      contentType: "application/json"
      type: "post"
      data: JSON.stringify(catchObj)
      url: routes.controllers.CatchController.create().url
    ).success( () ->
      alert("done")
    )

$(() ->
  ko.applyBindings(new CatchViewModel)
)