val input = java.io.File("input.txt").readLines().map{ it.toInt() }
val answer = (1..input.lastIndex).count{ input[it] > input[it-1] }
println("Answer is $answer")