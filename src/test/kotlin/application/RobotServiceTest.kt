package application

import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.Coordinates
import com.mindfulbytes.domain.InvalidHeadingException
import com.mindfulbytes.domain.InvalidMoveException
import com.mindfulbytes.domain.InvalidTurnException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotServiceTest {

    @Test
    fun `should create a robot with correct heading and coordinates`() {
        val robotService = RobotService()
        val robot = robotService.createRobot("NORTH", 0, 0)

        assertEquals("NORTH", robot.heading.name)
        assertEquals(0, robot.coordinates.x)
        assertEquals(0, robot.coordinates.y)
    }

    @Test
    fun `should turn robot correctly`() {
        val robotService = RobotService()
        val robot = robotService.createRobot("NORTH", 0, 0)

        assertEquals("WEST", robotService.turnRobot(robot, "LEFT").heading.name)

        assertEquals("EAST", robotService.turnRobot(robot, "RIGHT").heading.name)
    }

    @Test
    fun `should move robot correctly`() {
        val robotService = RobotService()
        val robot = robotService.createRobot("NORTH", 0, 0)

        assertEquals(Coordinates(0, 1), robotService.moveRobot(robot, "FORWARD").coordinates)

        val movedRobot = robotService.moveRobot(robot, "FORWARD")
            .let { robotService.turnRobot(it, "RIGHT") }
            .let { robotService.moveRobot(it, "FORWARD") }

        assertEquals(Coordinates(1, 1), movedRobot.coordinates)
    }

    @Test
    fun `trying to create a robot with invalid heading should throw exception`() {
        val robotService = RobotService()

        assertThrows(InvalidHeadingException::class.java) {
            robotService.createRobot("INVALID", 0, 0)
        }
    }

    @Test
    fun `should throw InvalidTurnException for invalid turn command`() {
        val robotService = RobotService()
        val robot = robotService.createRobot("NORTH", 0, 0)

        assertThrows(InvalidTurnException::class.java) {
            robotService.turnRobot(robot, "INVALID")
        }
    }

    @Test
    fun `should throw InvalidMoveException for invalid move command`() {
        val robotService = RobotService()
        val robot = robotService.createRobot("NORTH", 0, 0)

        assertThrows(InvalidMoveException::class.java) {
            robotService.moveRobot(robot, "INVALID")
        }
    }
}