package com.bloomberg.assignment

interface VectorRotate {

    fun rotateInPlace(vector: Array<String>, positions: Int)

    fun rotationPositionsValid(vectorSize: Int, positions: Int): Boolean {
        return positions <= vectorSize
    }
}