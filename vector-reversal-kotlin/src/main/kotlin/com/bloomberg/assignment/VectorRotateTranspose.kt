package com.bloomberg.assignment

class VectorRotateTranspose: VectorRotate {

    override fun rotateInPlace(vector: Array<String>, positions: Int) {
        if (!rotationPositionsValid(vector.size, positions)) {
            throw IllegalArgumentException("Incorrect positions count")
        }
        reverse(vector, 0, positions - 1)
        if (positions < vector.size) {
            reverse(vector, positions, vector.size - 1)
        }
        reverse(vector, 0, vector.size - 1)
    }

    private fun reverse(vector: Array<String>, start: Int, end: Int) {
        var temp: String
        for (i in 0.rangeTo((end - start) / 2)) {
            temp = vector[start + i]
            vector[start + i] = vector[end - i]
            vector[end - i] = temp
        }
    }
}