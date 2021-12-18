import kotlin.math.max
import kotlin.math.roundToInt

check(45 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(112 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input : String = file.readText()
    val (y1) = Regex("^.*x=-?\\d+\\.\\.-?\\d+, y=(-?\\d+)\\.\\.-?\\d+\$")
        .find(input)!!.destructured
    val possibleY = Math.abs(y1.toInt()) - 1
    return (possibleY*(possibleY+1)/2)
}

fun part2(file: java.io.File) : Int {
    val input : String = file.readText()
    val (x1, x2, y1, y2) = Regex("^.*x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)\$")
        .find(input)!!.destructured
    val xRange = (x1.toInt()..x2.toInt())
    val yRange = (y1.toInt()..y2.toInt())
    //  Find possible Xs
    var lowX = Math.sqrt(xRange.first.toDouble()).roundToInt()
    while (!xRange.contains(lowX*(lowX+1)/2)) lowX++
    val xVals = (lowX..xRange.last)
    // Find possible Ys
    val maxY = Math.abs(yRange.first) - 1
    val yVals = (yRange.first..maxY)
    // Execute on all pairs
    val pairs: MutableList<Pair<Int,Int>> = mutableListOf()
    xVals.forEach{ x ->
        yVals.forEach { y ->
            var step = 0
            var xTotal = 0
            var yTotal = 0
            while(xTotal <= xRange.last && yTotal >= yRange.first){
                xTotal += max(0, x-step)
                yTotal += y-step
                if(xRange.contains(xTotal) && yRange.contains(yTotal)){
                    pairs.add(x to y)
                    break
                }
                step++
            }
        }
    }
    return pairs.size
}