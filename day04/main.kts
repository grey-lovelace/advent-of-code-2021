check(4512 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(1924 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    val input = java.io.File(fileName).readText().split(Regex("(\r?\n){2}"))
    val calls = input[0].split(",").map{ it.toInt() }
    val cards = (1..input.lastIndex).map{ Card(input[it]) }
    calls.forEach{ call -> cards.forEach{ if(it.callNum(call)) return it.score() * call }}
    return 0
}

fun part2(fileName: String) : Int {
    val input = java.io.File(fileName).readText().split(Regex("(\r?\n){2}"))
    val calls = input[0].split(",").map{ it.toInt() }
    var cards = (1..input.lastIndex).map{ Card(input[it]) }
    calls.forEach{ call ->
        cards.forEach{ if(it.callNum(call) && cards.size == 1) return it.score() * call }
        cards = cards.filter{ !it.hasBingo() }
    }
    return 0
}

class Card(rawString : String) {
    var rows: List<List<Int>> = rawString.split(Regex("\r?\n")).map{
            line -> line.trim().split(Regex("\\s+")).map{ num -> num.toInt() }
    }
    var columns: List<List<Int>> = rows.indices.map{ i -> rows.map{ it[i] } }
    fun hasBingo() : Boolean { return this.rows.any{ it.isEmpty() } || this.columns.any{ it.isEmpty() } }
    fun score() : Int { return this.rows.map{ it.sum() }.sum() }
    fun callNum(call: Int) : Boolean {
        this.rows = this.rows.map{ row -> row.filter{ it!=call }}
        this.columns = this.columns.map{ col -> col.filter{ it!=call }}
        return this.hasBingo()
    }
}