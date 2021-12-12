check(10 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(36 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    var connMap = makeConnnectionsMap(file)
    var allPaths: MutableList<List<String>> = mutableListOf()
    traverse(connMap, listOf("start"), allPaths, false, false)
    return allPaths.size
}

fun part2(file: java.io.File) : Int {
    var connMap = makeConnnectionsMap(file)
    var allPaths: MutableList<List<String>> = mutableListOf()
    traverse(connMap, listOf("start"), allPaths, true, false)
    return allPaths.size
}

fun makeConnnectionsMap(file: java.io.File) : MutableMap<String,MutableList<String>> {
    var connMap: MutableMap<String,MutableList<String>> = mutableMapOf()
    file.readLines().forEach{
        val (a, b) = it.split("-")
        if(connMap[a] != null) connMap[a]!!.add(b) else connMap[a] = mutableListOf(b)
        if(connMap[b] != null) connMap[b]!!.add(a) else connMap[b] = mutableListOf(a)
    }
    return connMap
}

fun traverse(connMap: Map<String,List<String>>, path: List<String>, allPaths: MutableList<List<String>>,
             allowDoubleSmall: Boolean, doubleSmall: Boolean) {
    connMap[path.last()]!!.filter{ it != "start" &&
            !(it.lowercase()==it && path.contains(it) && (!allowDoubleSmall || doubleSmall))
    }.map{
        if("end" == it) allPaths.add(path + it)
        else traverse(connMap,path + it, allPaths, allowDoubleSmall,
            (allowDoubleSmall && (doubleSmall || (path.contains(it) && it.lowercase()==it))))
    }
}