import kotlin.math.pow

fun main() {
    val name = "Alex"
    var healthPoints = 89
    var isBlessed = true
    val isImmortal = false
    val karma = (Math.random().pow((110 - healthPoints) / 100.0) * 20).toInt()

    // Aura
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor =
        if (auraVisible)
            when (karma) {
                in 0..5 -> "Red"
                in 6..10 -> "Orange"
                in 11..15 -> "Purple"
                in 16..20 -> "Green"
                else -> "None"
            }
        else "None"

    // Health
    val healthStatus = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if (isBlessed) // Checking for blessedness
            "has some minor wounds but is healing quickly"
        else
            "has some minor wounds"
        in 15..74 -> "looks pretty hurt"
        else -> "is in awful condition!"
    }
    // Player status
    val statusToFormat = "[Name]\n" +
            "(HP) (Aura) (Blessedness) (Immortality)\n" +
            "Health status"
    val status = mapOf(
        "Name" to name,
        "HP" to healthPoints,
        "Health status" to healthStatus,
        "Blessedness" to isBlessed,
        "Immortality" to isImmortal,
        "Aura" to auraColor
    )
        .map { (k, v) ->
            { s: String ->
                s.replace(
                    k, "$k: ${when (v) {
                        is Boolean -> if (v) "Yes" else "No"
                        is String -> v
                        else -> "$v"
                    }}"
                )}}
        .fold(statusToFormat, { s, t -> t(s) })

    println(status)
}
