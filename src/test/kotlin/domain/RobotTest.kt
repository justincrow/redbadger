package domain

import com.mindfulbytes.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun `should return new robot with correct heading after a turn`() {
        val robot = Robot(Heading.N, Coordinates(0, 0))

        assertEquals(Heading.W, robot.turn(Turn.L).heading)
        assertEquals(Heading.S, robot.turn(Turn.R).turn(Turn.R).heading)

    }

    @Test
    fun `should return new robot with correct coordinates after a movement`() {
        val robot = Robot(Heading.N, Coordinates(0, 0))

        assertEquals(robot.move(Move.F).coordinates, Coordinates(x = 0, y = 1))
        assertEquals(
            robot.move(Move.F)
                .turn(Turn.R)
                .move(Move.F)
                .coordinates, Coordinates(x = 1, y = 1))

    }

}