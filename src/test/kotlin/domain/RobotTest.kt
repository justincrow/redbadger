package domain

import com.mindfulbytes.domain.Heading
import com.mindfulbytes.domain.Movement
import com.mindfulbytes.domain.Robot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun testRobotHeading() {
        val robot = Robot(Heading.NORTH)

        robot.turn(Movement.LEFT).let {
            assertEquals(Heading.WEST, it.heading)
        }

    }
}