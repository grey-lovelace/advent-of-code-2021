val input = java.io.File("input.txt").readLines()
var (x,y,aim) = listOf(0,0,0)
input.map{ it.split(" ") }.forEach{ (command, amount) ->
    when (command) {
        "up" -> aim -= amount.toInt()
        "down" -> aim += amount.toInt()
        "forward" -> {
            x += amount.toInt()
            y += aim * amount.toInt()
}}}
println("Answer is ${x*y}")
