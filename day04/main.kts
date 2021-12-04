check(4512 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(1924 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    return BingoGame(fileName).cards.minByOrNull{ it.winningRound }!!.score()
}

fun part2(fileName: String) : Int {
    return BingoGame(fileName).cards.maxByOrNull{ it.winningRound }!!.score()
}

class BingoGame(fileName : String) {
    val input = java.io.File(fileName).readText().split(Regex("(\r?\n){2}"))
    val calls = input[0].split(",").map{ it.toInt() }
    val cards = (1..input.lastIndex).map{ Card(input[it], calls) }
}

class Card(rawString : String, calls: List<Int>) {
    var rowsRaw = rawString.lines().map{ line -> line.trim().split(Regex("\\s+")).map{ it.toInt() } }
    // Rows and columns hold the round the number is called. rowsRaw holds the real numbers.
    var rows = this.rowsRaw.map{ row -> row.map{ num -> calls.indexOf(num) } }
    var columns = this.rows.indices.map{ i -> rows.map{ it[i] } }
    var winningRound = minOf(rows.minOf{ it.maxOrNull()!! },columns.minOf{ it.maxOrNull()!! })
    var winningCall = calls[this.winningRound]
    fun score() : Int = this.winningCall * this.rows.indices.map{ i ->
        this.rows[i].indices.map{ if(this.rows[i][it] > this.winningRound) this.rowsRaw[i][it] else 0 }.sum()
    }.sum()
}