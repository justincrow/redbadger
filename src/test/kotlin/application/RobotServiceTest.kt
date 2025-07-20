package application

import com.mindfulbytes.Helpers.flatMap
import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotServiceTest {

    @Test
    fun `should create a robot with correct heading and coordinates`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))
        val robot = robotService.createRobot("NORTH", 0, 0).getOrThrow()

        assertEquals("NORTH", robot.heading.name)
        assertEquals(0, robot.coordinates.x)
        assertEquals(0, robot.coordinates.y)
    }

    @Test
    fun `should turn robot correctly`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))
        val robot = robotService.createRobot("NORTH", 0, 0).getOrThrow()

        assertEquals("WEST", robotService.turnRobot(robot, "LEFT").getOrThrow().heading.name)
        assertEquals("EAST", robotService.turnRobot(robot, "RIGHT").getOrThrow().heading.name)
    }

    @Test
    fun `should move robot correctly`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))
        val robot = robotService.createRobot("NORTH", 0, 0).getOrThrow()

        assertEquals(Coordinates(0, 1), robotService.moveRobot(robot, "FORWARD").getOrThrow().coordinates)

        val movedRobot = robotService.moveRobot(robot, "FORWARD")
            .flatMap { robotService.turnRobot(it, "RIGHT") }
            .flatMap { robotService.moveRobot(it, "FORWARD") }

        assertEquals(Coordinates(1, 1), movedRobot.getOrThrow().coordinates)
    }

    @Test
    fun `trying to create a robot with invalid heading should throw exception`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))

        robotService.createRobot("INVALID", 0, 0).exceptionOrNull()!!.let {
            when (it) {
                is InvalidHeadingException -> assertEquals("Invalid heading: INVALID", it.message)
                else -> fail("Incorrect exception thrown: $it")
            }
        }
    }

    @Test
    fun `should throw InvalidTurnException for invalid turn command`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))
        robotService.createRobot("NORTH", 0, 0)
            .flatMap { robotService.turnRobot(it, "INVALID") }
            .exceptionOrNull()!!.let {
                when (it) {
                    is InvalidTurnException -> assertEquals("Invalid turn: INVALID", it.message)
                    else -> fail("Incorrect exception thrown: $it")
                }
            }
    }

    @Test
    fun `should throw InvalidMoveException for invalid move command`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(3, 5)))
        robotService.createRobot("NORTH", 0, 0)
            .flatMap { robotService.moveRobot(it, "INVALID") }
            .exceptionOrNull()!!.let {
                when (it) {
                    is InvalidMoveException -> assertEquals("Invalid move: INVALID", it.message)
                    else -> fail("Incorrect exception thrown: $it")
                }
            }
    }

    /*
    3 2 N
FRRFLLFFRRFLL
     */
    @Test
    fun `should return LOST if robot moves off grid`() {
        val robotService = RobotService(BoundaryChecker(Coordinates(5, 3)))

        with(robotService) {
            val lostRobot = robotService.createRobot("NORTH", 3, 2)
                .flatMap { moveRobot(it, "FORWARD") }
                .flatMap { turnRobot(it, "RIGHT") }
                .flatMap { turnRobot(it, "RIGHT") }
                .flatMap { moveRobot(it, "FORWARD") }
                .flatMap { turnRobot(it, "LEFT") }
                .flatMap { turnRobot(it, "LEFT") }
                .flatMap { moveRobot(it, "FORWARD") }
                .flatMap { moveRobot(it, "FORWARD") }
                .flatMap { turnRobot(it, "RIGHT") }
                .flatMap { turnRobot(it, "RIGHT") }
                .flatMap { moveRobot(it, "FORWARD") }
                .flatMap { turnRobot(it, "LEFT") }
                .flatMap { turnRobot(it, "LEFT") }

            lostRobot.exceptionOrNull()!!.let {
                when (it) {
                    is RobotLostException -> assertEquals("Robot lost at: 3,4", it.message)
                    else -> fail("Incorrect exception thrown: $it")
                }
            }
        }
    }
}

