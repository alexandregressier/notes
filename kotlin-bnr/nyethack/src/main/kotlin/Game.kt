fun main() {
    val name = "Alex"
    var healthPoints = 89
    var isBlessed = true
    val isImmortal = false

    // Aura
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"

    println(auraColor)

    // Health
    val healthStatus =
        if (healthPoints == 100)
            "is in excellent condition!"
        else if (healthPoints in 90..99)
            "has a few scratches"
        else if (healthPoints in 75..89)
            if (isBlessed) // Checking for blessedness
                "has some minor wounds but is healing quickly"
            else
                "has some minor wounds"
        else if (healthPoints in 15..74)
            "looks pretty hurt"
        else
            "$name is in awful condition!"

    println("$name $healthStatus")
}
