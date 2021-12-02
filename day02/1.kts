val input = java.io.File("input.txt").readLines()
var (x,y) = listOf(0,0)
input.map{ it.split(" ") }.forEach{ (command, amount) ->
    when (command) {
        "forward" -> x += amount.toInt()
        "up" -> y -= amount.toInt()
        "down" -> y += amount.toInt()
}}
println("Answer is ${x*y}")
