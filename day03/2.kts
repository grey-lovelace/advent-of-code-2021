val input = java.io.File("input.txt").readLines()
fun List<String>.filterByBit(i : Int, useLow: Boolean) : List<String> {
    if(this.size > 1) {
        val (low, high) = this.map { it.get(i) }.groupingBy { it }.eachCount().entries.sortedBy { it.value }
        val (default, sortedVal) = if(useLow) listOf('0', low.key) else listOf('1', high.key)
        val keep = if (low.value == high.value) default else sortedVal
        return this.filter { it.get(i) == keep }
    } else return this
}
var (oxy, co2) = listOf(input.toList(),input.toList())
input[0].indices.forEach{ i ->
    oxy = oxy.filterByBit(i, false)
    co2 = co2.filterByBit(i, true)
}
println("Answer is ${oxy[0].toInt(2) * co2[0].toInt(2)}")