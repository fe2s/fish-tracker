require(["webjars!knockout.js", 'webjars!jquery.js', "/routes.js", "webjars!bootstrap.js" ], (ko) ->

#  class Test
#    constructor: ->
#      alert("constr")

#  test = ->
#    t = new Test

  class CatchModel
    constructor: () ->
      alert("catch model constr")
      @place = ko.observable("abc")
      @fish = ko.observable("cde")

    submit: () ->
      alert("place is " + @place() + " fish is " + @fish())


  ko.applyBindings(new CatchModel)

#  $("#submitCatch").click( ->
#    alert(ko)
#    alert("submit catch3")
#  )

)