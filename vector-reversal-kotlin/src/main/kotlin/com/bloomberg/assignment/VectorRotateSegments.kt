package com.bloomberg.assignment

class VectorRotateSegments: VectorRotate {

    override fun rotateInPlace(vector: Array<String>, positions: Int) {
        if (!rotationPositionsValid(vector.size, positions)) {
            throw IllegalArgumentException("Incorrect positions count")
        }

        moveSegments(vector, positions, vector.size - 1)
    }

    fun moveSegments(vector: Array<String>, segmentSize: Int, lastIndex: Int) {
        if (segmentSize >= lastIndex) {
            return
        }
        val buffer = Array(segmentSize, {""})
        // copy the last segment in the buffer
        System.arraycopy(vector, lastIndex - segmentSize, buffer, 0, segmentSize)
        // copy the first segment to the last segment
        System.arraycopy(vector, 0, vector, lastIndex - segmentSize, segmentSize)
        // copy the buffer to the first segment
        System.arraycopy(buffer, 0, vector, 0, segmentSize)
        // repeat ignoring the last segment
        moveSegments(vector, segmentSize, lastIndex - segmentSize)
    }
}