check(15 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(1134 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines().map{ it.map{l -> l.toString().toInt()}}
    return findLowPoints(input).map{ input.find(it) + 1 }.sum()
}

fun part2(file: java.io.File) : Int {
    val input = file.readLines().map{ it.map{l -> l.toString().toInt()}}
    val basins = findLowPoints(input).map{
        var pointsFound = mutableListOf<Pair<Int,Int>>()
        checkSurrounding(it, input, pointsFound)
        pointsFound.size
    }
    return basins.sortedDescending().take(3).reduce{ first, it -> first * it }
}

fun findLowPoints(input: List<List<Int>>) : List<Pair<Int,Int>> {
    val allPoints = input.indices.flatMap{ x -> input[x].indices.map{ y -> Pair(x,y) }}
    return allPoints.filter{ it.adjPoints().all{ adj -> input.find(adj) > input.find(it) }}
}

fun List<List<Int>>.find(point: Pair<Int,Int>) = (this.getOrNull(point.first) ?: listOf()).getOrNull(point.second) ?: 9

fun Pair<Int,Int>.adjPoints() = listOf(Pair(this.first-1,this.second), Pair(this.first+1,this.second),
        Pair(this.first,this.second-1), Pair(this.first,this.second+1))

fun checkSurrounding(point: Pair<Int,Int>, input: List<List<Int>>, pointsFound: MutableList<Pair<Int,Int>>) {
    if(input.find(point) != 9 && !pointsFound.contains(point)) pointsFound.add(point)
    point.adjPoints().filter{ input.find(it) > input.find(point) }.forEach{ checkSurrounding(it, input, pointsFound) }
}