check(4140 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(0 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
//    val input = file.readLines()
//    println(input)
    val lines = listOf(
        Pair(Pair(Pair(0,Pair(5,8)),Pair(Pair(1,7),Pair(9,6))),Pair(Pair(4,Pair(1,2)),Pair(Pair(1,4),2))),
        Pair(Pair(Pair(5,Pair(2,8)),4),Pair(5,Pair(Pair(9,9),0))),
        Pair(6,Pair(Pair(Pair(6,2),Pair(5,6)),Pair(Pair(7,6),Pair(4,7)))),
        Pair(Pair(Pair(6,Pair(0,7)),Pair(0,9)),Pair(4,Pair(9,Pair(9,0)))),
        Pair(Pair(Pair(7,Pair(6,4)),Pair(3,Pair(1,3))),Pair(Pair(Pair(5,5),1),9)),
        Pair(Pair(6,Pair(Pair(7,3),Pair(3,2))),Pair(Pair(Pair(3,8),Pair(5,7)),4)),
        Pair(Pair(Pair(Pair(5,4),Pair(7,7)),8),Pair(Pair(8,3),8)),
        Pair(Pair(9,3),Pair(Pair(9,9),Pair(6,Pair(4,9)))),
        Pair(Pair(2,Pair(Pair(7,7),7)),Pair(Pair(5,8),Pair(Pair(9,3),Pair(0,2)))),
        Pair(Pair(Pair(Pair(5,2),5),Pair(8,Pair(3,7))),Pair(Pair(5,Pair(7,5)),Pair(4,4)))
    )
    println(lines)
    lines.map{

    }
    return 0
}

fun part2(file: java.io.File) : Int {
    val input : String = file.readText()
    println(input)
    return 0
}

fun Pair<Any,Any>.reduce() : Boolean {
    if(this.left is Int && this.left >= 10){
        this.left = this.left.split()
    } else if(this.left is Pair<Any,Any>){

    }
}

fun Int.split(): Pair<Int,Int> {
    return Pair(0,0)
}