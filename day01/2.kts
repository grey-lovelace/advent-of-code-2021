import java.io.File

val input : List<Int> = File("input.txt").readLines().map{ it.toInt() }
val slides = input.windowed(3,1).map{ it.sum() }
val answer = (1..slides.lastIndex).count{slides[it] > slides[it-1] }
println("Answer is $answer")