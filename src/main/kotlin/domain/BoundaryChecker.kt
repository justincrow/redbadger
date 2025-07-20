package com.mindfulbytes.domain

class BoundaryChecker(private val maxCoordinates: Coordinates) {

    fun coordinatesValid(coordinates: Coordinates): Boolean = (coordinates.x >= 0
            && coordinates.y >= 0
            && coordinates.x <= maxCoordinates.x
            && coordinates.y <= maxCoordinates.y)
}