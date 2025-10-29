package com.kylegordon.crocsswimlog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.io.Serializable

data class Drill(
    val title: String,
    val description: String,
    val yardage: String,
    val stroke: StrokeType
)

enum class StrokeType {
    FREESTYLE, BACKSTROKE, BREASTSTROKE, BUTTERFLY
}
class DOTDViewModel : ViewModel() {

    // List of freestyle swimming drills paired with descriptions
    private val freeDrills = listOf(
        Drill(
            "Catch-Up Drill",
            "Swim freestyle using only one arm at a time, with the other arm out front in streamline. Helps with arm timing along with strength and length of the pull.",
            "4 x 50 Yards on the minute, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular freestyle",
            StrokeType.FREESTYLE
        ),
        Drill(
            "Flick Drill",
            "Flick the water upwards as you swim freestyle to practice nice, high elbows and power out of the water",
            "4 x 50 Yards on the minute, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular freestyle",
            StrokeType.FREESTYLE
        ),
        Drill(
            "Fingertip Drag Drill",
            "Drag your fingertips forward along the water to practice your full range of motion for freestyle.",
            "4 x 50 Yards on the minute, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular freestyle",
            StrokeType.FREESTYLE
        ),
        Drill(
            "Closed Fist Drill",
            "Swim your freestyle with a balled fist rather than extended fingers, reminding the body to also use the forearms in freestyle, not just the hands.",
            "4 x 50 Yards on the minute, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular freestyle",
            StrokeType.FREESTYLE
        ),
        Drill(
            "Tarzan Drill",
            "Swim your freestyle with your head looking straight out of the water while everything else stays the same. This practices strength, but be careful not to overexert on this drill.",
            "4 x 25 Yards on the 45 second.",
            StrokeType.FREESTYLE
        )
    )

    // List of backstroke swimming drills paired with descriptions
    private val backDrills = listOf(
        Drill(
            "Single Arm Backstroke",
            "Use one arm to isolate and perfect your backstroke form, really focusing on pull and rotation.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BACKSTROKE
        ),
        Drill(
            "Laneline Drill",
            "Use whichever arm is closer to the laneline to push yourself forward, practicing how your arms should catch the water when swimming backstroke.",
            "4 x 50 Yards on the minute. Both arms should be used in the 50.",
            StrokeType.BACKSTROKE
        ),
        Drill(
            "Wave Drill",
            "When your arm is pointing up at the ceiling, twist your arm 3 times to practice the rotation of your arms as they enter and exit the water.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BACKSTROKE
        ),
        Drill(
            "Balancing Act Drill",
            "Fill a plastic cup halfway with water and place it on your forehead. From there, kick on your back in streamline, not letting the cup fall off.",
            "4 x 25 Yards on the minute",
            StrokeType.BACKSTROKE
        ),
        Drill(
            "12-Kick Drill",
            "Have 12 strong kicks, then do a backstroke pull to swap sides. Make sure the body is fully rotated 90 degrees, you should be on your side.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BACKSTROKE
        )
    )

    // List of breaststroke swimming drills paired with descriptions
    private val breastDrills = listOf(
        Drill(
            "Breaststroke Kick on Back",
            "Practice the kick position and motions while floating on your back.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BREASTSTROKE
        ),
        Drill(
            "Double Kick Drill",
            "Perform 2 breaststroke kicks to each pull, focusing on strength and distance of kick.",
            "4 x 50 Yards on the 1 min 15 sec, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular breaststroke",
            StrokeType.BREASTSTROKE
        ),
        Drill(
            "Extended Glide Drill",
            "Hold the glide for an extra 2-3 seconds compared to your regular breaststroke to maximize distance per stroke.",
            "4 x 50 Yards on the 1 min 15 sec, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular breaststroke",
            StrokeType.BREASTSTROKE
        ),
        Drill(
            "Heel Tag Drill",
            "With your hands at your side, try to touch your heels to your hand with every kick, which works on range of motion and setting up for the push of the kick.",
            "4 x 50 Yards on the 1 min 15 sec, 1st 25 doing the drill, 2nd 25 to incorporate the form into your regular breaststroke",
            StrokeType.BREASTSTROKE
        ),
        Drill(
            "Breaststroke With Flutter Kick",
            "Exactly as the title says. Breaststroke arms with a flutter kick, which practices the speed and explosiveness of the arms.",
            "4 x 50 Yards on the 1 min 30 sec",
            StrokeType.BREASTSTROKE
        )
    )

    // List of butterfly swimming drills paired with descriptions
    private val flyDrills = listOf(
        Drill(
            "Butterfly Body Wave",
            "Focus on undulation without using your arms. Improves the flow of your butterfly as you swim it.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BUTTERFLY
        ),
        Drill(
            "3-3-3 Drill",
            "3 pulls right, 3 left, 3 both — builds rhythm and strength in your butterfly.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BUTTERFLY
        ),
        Drill(
            "Backerfly",
            "Swim butterfly on your back to build endurance and strength.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BUTTERFLY
        ),
        Drill(
            "Knuckle Drill",
            "Swim butterfly with your fists closed to work on the high catch of the elbows and strength of the arms underwater.",
            "4 x 25 Yards on the 40 second",
            StrokeType.BUTTERFLY
        ),
        Drill(
            "Butterfly With Flutter Kick",
            "Exactly as the title says. Butterfly arms with a flutter kick, which practices the speed and explosiveness of the arms.",
            "4 x 50 Yards on the 1 min 30 sec",
            StrokeType.BUTTERFLY
        )
    )

    private val allDrillLists = listOf(freeDrills, backDrills, breastDrills, flyDrills)

    var currentDrill = mutableStateOf(showRandomDrill())
        private set

    private fun showRandomDrill(): Drill {
        val drills = allDrillLists.random()
        return drills.random()
    }

    fun generateNewDrill() {
        currentDrill.value = showRandomDrill()
    }
}