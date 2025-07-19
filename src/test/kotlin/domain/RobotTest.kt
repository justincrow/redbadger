package domain

import com.mindfulbytes.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun `should return new robot with correct heading after a turn`() {
        val robot = Robot(Heading.NORTH, Coordinates(0, 0))

        assertEquals(Heading.WEST, robot.turn(Turn.LEFT).heading)
        assertEquals(Heading.SOUTH, robot.turn(Turn.RIGHT).turn(Turn.RIGHT).heading)

    }

    @Test
    fun `should return new robot with correct coordinates after a movement`() {
        val robot = Robot(Heading.NORTH, Coordinates(0, 0))

        assertEquals(robot.move(Move.FORWARD).coordinates, Coordinates(x = 0, y = 1))
        assertEquals(
            robot.move(Move.FORWARD)
                .turn(Turn.RIGHT)
                .move(Move.FORWARD)
                .coordinates, Coordinates(x = 1, y = 1))

    }

}