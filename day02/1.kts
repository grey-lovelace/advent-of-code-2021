val input = java.io.File("input.txt").readLines()
var x = 0
var y = 0
input.map{ it.split(" ") }
    .forEach{
        when (it[0]) {
            "forward" -> x += it[1].toInt()
            "up" -> y -= it[1].toInt()
            "down" -> y += it[1].toInt()
        }
    }
println("Answer is ${x*y}")
