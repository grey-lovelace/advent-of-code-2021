check(1656 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(195 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines().map{ line ->
        line.map{ it.toString().toInt() }.toMutableList()}.toMutableList()
    val allPoints = input.indices.flatMap{ x -> input[x].indices.map{ y -> Pair(x,y) }}
    return (0 until 100).map{ _ ->
        var flashed = mutableListOf<Pair<Int,Int>>()
        allPoints.forEach{ (x,y) -> input[x][y]++ }
        allPoints.forEach{ processFlash(input, it, flashed) }
        flashed.forEach{ (x,y) -> input[x][y] = 0 }
        flashed.size
    }.sum()
}

fun part2(file: java.io.File) : Int {
    val input = file.readLines().map{ line ->
        line.map{ it.toString().toInt() }.toMutableList()}.toMutableList()
    val allPoints = input.indices.flatMap{ x -> input[x].indices.map{ y -> Pair(x,y) }}
    var allFlashed = false
    var step = 0
    while(!allFlashed){
        step++
        var flashed = mutableListOf<Pair<Int,Int>>()
        allPoints.forEach{ (x,y) -> input[x][y]++ }
        allPoints.forEach{ processFlash(input, it, flashed) }
        flashed.forEach{ (x,y) -> input[x][y] = 0 }
        allFlashed = flashed.size == allPoints.size
    }
    return step
}

fun Pair<Int,Int>.adjPoints() = listOf(
    Pair(this.first-1,this.second), Pair(this.first+1,this.second),
    Pair(this.first,this.second-1), Pair(this.first,this.second+1),
    Pair(this.first-1,this.second-1), Pair(this.first+1,this.second+1),
    Pair(this.first-1,this.second+1), Pair(this.first+1,this.second-1)
)

fun processFlash(input: MutableList<MutableList<Int>>, point: Pair<Int,Int>, flashed: MutableList<Pair<Int,Int>>){
    if(!flashed.contains(point) && input[point.first][point.second] > 9){
        flashed.add(point)
        point.adjPoints().filter{ input.has(it) }.forEach{ (x,y) ->
            input[x][y]++
            processFlash(input, Pair(x,y), flashed)
        }
    }
}

fun List<List<Int>>.has(point: Pair<Int,Int>) = (0..this.lastIndex).contains(point.first)
        && (0..this[0].lastIndex).contains(point.second)
