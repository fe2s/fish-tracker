class Catch
  constructor: (json) ->
    @place = json.place
    @fish = json.fish
    @date = new Date(json.date).toLocaleFormat('%d-%b-%Y');


class CatchViewModel
  constructor: () ->
    self = @
    @catches = ko.observableArray([])

    # load data
    @load()

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

# temp 1
# master here