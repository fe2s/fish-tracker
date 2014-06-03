class Catch
  constructor: (json) ->
    @place = json.place
    @fish = json.fish

class CatchViewModel
  constructor: () ->
    self = @
    @place = ko.observable("")
    @date = ""
    @fish = ko.observable("")
    @availableFishes = ko.observableArray(["perch", "pike", "zander"])
    @dayRating = ko.observable("3")
    @availableDayRatings = ko.observableArray(['1', '2', '3', '4', '5'])
    @availableHourRatings = ko.observableArray(["", "1", "2", "3", "4", "5"])

    @availableHours = ko.observableArray(["", "00", "01", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"])

    @hourRatingStart1 = ko.observable("")
    @hourRatingEnd1 = ko.observable("")
    @hourRating1 = ko.observable("")

    @hourRatingStart2 = ko.observable("")
    @hourRatingEnd2 = ko.observable("")
    @hourRating2 = ko.observable("")


    # init calender
    $('.datepicker').datepicker(
      autoclose: true
      todayHighlight: true
    ).on('changeDate', (event) ->
      self.date = event.date.getTime()
    )

  submit: () ->
    catchObj =
      fish: @fish()
      place: @place()
      date: @date
      dayRating: @dayRating()
      hourRatingStart1: @hourRatingStart1()
      hourRatingEnd1: @hourRatingEnd1()
      hourRating1: @hourRating1()
      hourRatingStart2: @hourRatingStart2()
      hourRatingEnd2: @hourRatingEnd2()
      hourRating2: @hourRating2()



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