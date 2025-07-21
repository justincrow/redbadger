package adaptor.inbound.file

import com.mindfulbytes.adapter.inbound.file.RobotFileInputHandler
import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.BoundaryChecker
import com.mindfulbytes.domain.Coordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RobotFileInputHandlerTest {

    @Test
    fun `interprets a list of valid string commands`() {

        val input = listOf(
            "1 1 E",
            "RFRFRFRF",
        )

        val handler =
            RobotFileInputHandler(RobotService(BoundaryChecker(Coordinates(5, 3))), input)

        val output = handler.handleCommands()
        assertEquals(listOf("1 1 E"), output)
    }

    @Test
    fun `interprets a list of string commands causing a LOST situation`() {

        val input = listOf(
            "0 3 W",
            "LLFFFLFLFL"
        )

        val handler =
            RobotFileInputHandler(RobotService(BoundaryChecker(Coordinates(5, 3))), input)

        val output = handler.handleCommands()
        assertEquals(listOf("3 3 N LOST"), output)
    }

    @Test
    fun `interprets a list of string commands, ignoring previous LOST positions`() {

        val input = listOf(
            "1 1 E",
            "RFRFRFRF",
            "3 2 N",
            "FRRFLLFFRRFLL",
            "0 3 W",
            "LLFFFLFLFL"
        )

        val handler =
            RobotFileInputHandler(RobotService(BoundaryChecker(Coordinates(5, 3))), input)

        val output = handler.handleCommands()
        assertEquals(listOf("1 1 E", "3 3 N LOST", "2 3 S"), output)
    }
}