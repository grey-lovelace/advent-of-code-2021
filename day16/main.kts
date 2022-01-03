check(16 == part1("8A004A801A8002F478"))
check(12 == part1("620080001611562C8802118E34"))
check(23 == part1("C0015000016115A2E0802F182340"))
check(31 == part1("A0016C880162017C3686B18A3D4780"))
println("Part1 = ${part1(java.io.File("input.txt").readText())}")
check(3L == part2("C200B40A82"))
check(54L == part2("04005AC33890"))
check(7L == part2("880086C3E88112"))
check(9L == part2("CE00C43D881120"))
check(1L == part2("D8005AC2A8F0"))
check(0L == part2("F600BC2D8F"))
check(0L == part2("9C005AC2F8F0"))
check(1L == part2("9C0141080250320F1802104A08"))
println("Part2 = ${part2(java.io.File("input.txt").readText())}")

fun part1(input: String) : Int {
    return parsePackets(input).sumOf{ it.version }
}

fun part2(input: String) : Long {
    val packets = parsePackets(input)
    val answr = packets[0].calc()
    return answr
}

class Packet() {
    var version: Int = -1
    var typeId: Int = -1
    var value: Long = -1
    val children = mutableListOf<Packet>()
    fun calc() : Long {
        if (this.value == -1L) {
            this.value = when (this.typeId) {
                0 -> this.children.sumOf{ it.calc() }
                1 -> this.children.fold(1L){acc, it -> acc*it.calc()}
                2 -> this.children.minOf{ it.calc() }
                3 -> this.children.maxOf{ it.calc() }
                5 -> if(this.children[0].calc() > this.children[1].calc()) 1L else 0L
                6 -> if(this.children[0].calc() < this.children[1].calc()) 1L else 0L
                7 -> if(this.children[0].calc() == this.children[1].calc()) 1L else 0L
                else -> 0
            }
        }
        return this.value
    }
}

data class Parent(
    val packet: Packet,
    var count: Int,
    val lastIndex: Int,
)

class Tracker() {
    var n = 0
    fun progress(amount: Int) : Int { this.n += amount; return this.n }
}

fun parsePackets(input: String): List<Packet> {
    val binary = input.map{ it.toString().toInt(16).toString(2).padStart(4,'0') }
        .joinToString("")
    var tkr = Tracker()
    val parentList = mutableListOf<Parent>()
    val packets = mutableListOf<Packet>()
    while (tkr.n + 11 <= binary.length) {
        val currentPacket = Packet()
        packets.add(currentPacket)
        parentCheck@ while(!parentList.isEmpty()){
            val parentPacket = parentList.last()
            if(parentPacket.count > 0 || tkr.n < parentPacket.lastIndex) {
                parentPacket.packet.children.add(currentPacket)
                if(parentPacket.count > 0) parentPacket.count--
                break@parentCheck
            } else {
                parentList.removeLast()
            }
        }
        currentPacket.version = binary.substring(tkr.n, tkr.progress(3)).toInt(2)
        currentPacket.typeId = binary.substring(tkr.n, tkr.progress(3)).toInt(2)
        if (currentPacket.typeId == 4) {
            var literal = ""
            do {
                var sentry = binary.substring(tkr.n, tkr.progress(1)).toInt(2)
                literal += binary.substring(tkr.n, tkr.progress(4))
            } while (sentry == 1)
            currentPacket.value = literal.toLong(2)
        } else {
            val lengthTypeId = binary.substring(tkr.n, tkr.progress(1)).toInt(2)
            if(lengthTypeId == 0) {
                val childLength = binary.substring(tkr.n, tkr.progress(15)).toInt(2)
                parentList.add(Parent(currentPacket,0, tkr.n + childLength))
            } else {
                val childAmount = binary.substring(tkr.n, tkr.progress(11)).toInt(2)
                parentList.add(Parent(currentPacket, childAmount, 0))
            }
        }
    }
    return packets
}
