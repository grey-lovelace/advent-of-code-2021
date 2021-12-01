import java.io.File

val input = File("input.txt").readLines()
val answer = input.indices
    .count{it > 0 && input[it].toInt() > input[it-1].toInt()}
println("Answer is $answer")