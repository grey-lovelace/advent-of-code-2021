check(17 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
println("Part2SAMPLE = ${part2(java.io.File("sample.txt"))}")
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines()
    val indexOfBlank = input.indexOf("")
    val points = input.take(indexOfBlank).map{ it.split(",") }.map{ Pair(it[0].toInt(),it[1].toInt())}
    val (dir,lineNumString) = Regex("fold along ([xy])=(\\d+)").find(input[indexOfBlank+1])!!.destructured
    val lineNum = lineNumString.toInt()
    var newPlot: List<Pair<Int,Int>> = listOf()
    if(dir == "x"){
        newPlot = points.filter{ it.first < lineNum } + points.filter{ it.first > lineNum }
            .map{ Pair(lineNum - (it.first - lineNum),it.second) }
    }
    if(dir == "y"){
        newPlot = points.filter{ it.second < lineNum } + points.filter{ it.second > lineNum }
            .map{ Pair(it.first,lineNum - (it.second - lineNum)) }
    }
    return newPlot.distinct().size
}

fun part2(file: java.io.File) : Int {
    val input = file.readLines()
    val indexOfBlank = input.indexOf("")
    var points = input.take(indexOfBlank).map{ it.split(",") }.map{ Pair(it[0].toInt(),it[1].toInt())}
    val commands = input.subList(indexOfBlank+1,input.size)
    println(commands)
    commands.forEach{
        val (dir,lineNumString) = Regex("fold along ([xy])=(\\d+)").find(it)!!.destructured
        val lineNum = lineNumString.toInt()
        if(dir == "x"){
            points = points.filter{ it.first < lineNum } + points.filter{ it.first > lineNum }
                .map{ Pair(lineNum - (it.first - lineNum),it.second) }
        }
        if(dir == "y"){
            points = points.filter{ it.second < lineNum } + points.filter{ it.second > lineNum }
                .map{ Pair(it.first,lineNum - (it.second - lineNum)) }
        }
    }
    println(points)
    (0..40).forEach { y ->
        println((0..40).map{ x -> if(points.contains(Pair(x,y))) '#' else ' ' }.joinToString(""))
    }
    return points.distinct().size
}