package domain

import com.mindfulbytes.domain.BoundaryChecker
import com.mindfulbytes.domain.Coordinates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoundaryCheckerTest {

    @Test
    fun `returns whether a coordinate is valid in current boundary system`() {

        val underTest = BoundaryChecker(Coordinates(3, 5))

        assertEquals(true, underTest.coordinatesValid(Coordinates(0,0)))
        assertEquals(false, underTest.coordinatesValid(Coordinates(3,6)))
    }
}