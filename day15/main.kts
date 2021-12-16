import kotlin.math.min

check(40 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(315 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines().map{ line -> line.map{ it.toString().toInt() }}
    return input.findShortestPath()
}

fun part2(file: java.io.File) : Int {
    val input = file.readLines().map{ line -> line.map{ it.toString().toInt() }.toMutableList() }.toMutableList()
    var expanded = input.map{ line ->
        (0 until 5).map{ i -> line.map{ it + i }.map{ if(it > 9) it-9 else it } }.flatten()
    }
    expanded = (0 until 5).map{ i ->
        expanded.map { line ->
            line.map{ it + i }.map{ if(it > 9) it-9 else it }
        }
    }.flatten()
    return expanded.findShortestPath()
}

fun List<List<Int>>.findShortestPath(): Int{
    // Prepare our points
    val pairsToPoints = this.indices
        .flatMap{ x -> this[x].indices.map{ y -> Pair(x,y) } }
        .associateWith { (x,y) -> Point(x,y,this) }
    val allPoints = pairsToPoints.values
    allPoints.forEach{
        it.adjPoints = listOf(it.x-1,it.x+1,it.x,it.x).zip(listOf(it.y,it.y,it.y+1,it.y-1))
            .filter{ this.has(it) }
            .map{ pair -> pairsToPoints[pair]!! }
    }
    // Set initial point
    pairsToPoints[Pair(0,0)]!!.distance = 0
    var visited = 1
    // Dijkstra time!
    while(visited != allPoints.size){
        println("${visited}/${allPoints.size}")
        val low = allPoints.filter{ !it.visited }.minByOrNull{ it.distance }!!
        low.adjPoints.forEach{ adj ->
            val toAdj = (low.distance + adj.value)
            if(toAdj < adj.distance){
                adj.distance = toAdj
                adj.previous = low
            }
        }
        low.visited = true
        visited++
    }
    return pairsToPoints[Pair(this.lastIndex, this[0].lastIndex)]!!.distance
}

class Point(x: Int, y: Int, grid: List<List<Int>>){
    val x = x
    val y = y
    val value = grid[x][y]
    var adjPoints: List<Point> = listOf()
    var distance = Int.MAX_VALUE
    var previous: Point? = null
    var visited = false
}

fun List<List<Int>>.has(point: Pair<Int,Int>) =  this.indices.contains(point.first)
        && this[0].indices.contains(point.second)