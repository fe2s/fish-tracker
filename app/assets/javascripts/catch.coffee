class CatchModel
  constructor: () ->
    self = @
    alert("catch model constr")
    @place = ko.observable("abc")
    @fish = ko.observable("cde")

  submit: () ->
    alert("place is " + @place() + " fish is " + @fish())
    o =
      contentType: "application/json"
      type: "post"
      data: {}
      url: "/test"

    $.ajax(o)

$(() ->
  ko.applyBindings(new CatchModel)
)

#routes.controllers.CatchController.test()