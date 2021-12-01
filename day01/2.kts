import java.io.File

val input : List<String> = File("input.txt").readLines()
val slides = (0..input.size-3).map {
    (it..it+2).map{ x -> input[x].toInt()}.sum()
}
val answer = slides.indices.count{it > 0 && slides[it] > slides[it-1]}
println("Answer is $answer")