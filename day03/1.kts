val input = java.io.File("input.txt").readLines()
var (gamma, epsilon) = listOf("","")
var data = input[0].indices.map{ i ->
    input.map{ it.get(i) }.groupingBy{ it }.eachCount().entries.sortedBy{ it.value }
}.forEach{ (low, high) ->
    gamma += low.key
    epsilon += high.key
}
println("Answer is ${gamma.toInt(2) * epsilon.toInt(2)}")