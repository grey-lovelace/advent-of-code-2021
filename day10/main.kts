check(26397 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(288957L == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines()
    val pairs = "({[<".zip(")}]>").toMap()
    val scores = ")]}>".toCharArray().zip(listOf(3,57,1197,25137)).toMap()
    var answer = 0
    input.forEach{ line ->
        var stack = mutableListOf<Char>()
        for(c in line){
            if(pairs.keys.contains(c)) stack.add(c)
            else if(c == pairs[stack.last()]) stack.removeLast()
            else {
                answer += scores[c]
                break
            }
        }
    }
    return answer
}

fun part2(file: java.io.File) : Long {
    val input = file.readLines()
    val pairs = "({[<".zip(")}]>").toMap()
    val scores = ")]}>".toCharArray().zip(listOf(1,2,3,4)).toMap()
    var answer = input.map{ line ->
        var stack = mutableListOf<Char>()
        var brokeEarly = false
        for(c in line){
            if(pairs.keys.contains(c)) stack.add(c)
            else if(c == pairs[stack.last()]) stack.removeLast()
            else { brokeEarly = true; break }
        }
        if(brokeEarly) "INVALID" else stack.reversed().map{ pairs[it] }.joinToString("")
    }.filter{ it != "INVALID" }.map{
        var x = 0L
        it.forEach{ c -> x = (x * 5) + scores[c]!!}
        x
    }
    return answer.sorted()[answer.size/2]
}