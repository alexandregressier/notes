const val MAX_EXPERIENCE = 5000

fun main() {
    val playerName = "Alex"
    var experiencePoints = 5
    experiencePoints += 5
    val hasSteed = false
    val menu = mapOf(
        1 to "Eat",
        2 to "Drink",
        3 to "Leave"
    )
    println(playerName)
    println("$experiencePoints / $MAX_EXPERIENCE")
    println(hasSteed)

    println("What do you want to do, $playerName?")
    println(menu)
    val choice = menu[readLine()?.toInt()]
    println("You chose to ${choice?.toLowerCase()}")

    println("${playerName.reversed().toLowerCase().capitalize()} wants to battle!")
}
