val input = java.io.File("input.txt").readLines()
var x = 0
var y = 0
var aim = 0
input.map{ it.split(" ") }
    .forEach{
        when (it[0]) {
            "forward" -> {
                x += it[1].toInt()
                y += aim*it[1].toInt()
            }
            "up" -> aim -= it[1].toInt()
            "down" -> aim += it[1].toInt()
        }
    }
println("Answer is ${x*y}")
