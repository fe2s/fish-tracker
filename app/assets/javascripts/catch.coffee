class Catch
  constructor: (json) ->
    @place = ko.observable(json.place)
    @fish = ko.observable(json.fish)

class CatchViewModel
  constructor: () ->
    @catches = ko.observableArray([])
    @place = ko.observable("")
    @fish = ko.observable("")
    # init calender
    $('.datepicker').datepicker(
      autoclose: true
      todayHighlight: true
    ).on('changeDate', (ev) -> alert(ev))

    @load()

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

  load: () ->
    self = @
    url = routes.controllers.CatchController.findAll().url
    $.getJSON(url, (json) ->
      loadedCatches = $.map(json, (catchJson) -> new Catch(catchJson))
      self.catches(loadedCatches)
    )

$(() ->
  ko.applyBindings(new CatchViewModel)
)

#routes.controllers.CatchController.test()