class BaitViewModel
  constructor: () ->
    self = @
    @brand = ko.observable("")
    @availableBrands = ko.observableArray(["Bait Breath", "Sawamura", "Keitech"])

  submit: () ->
    baitObj =
      brand: @brand()

    $.ajax(
      contentType: "application/json"
      type: "post"
      data: JSON.stringify(baitObj)
      url: routes.controllers.BaitController.create().url
    ).success( () ->
      alert("done")
    )

$(() ->
  ko.applyBindings(new BaitViewModel)
)