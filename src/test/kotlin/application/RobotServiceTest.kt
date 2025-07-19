package application

import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.Coordinates
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
}