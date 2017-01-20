package com.bloomberg.assignment

class VectorRotateSegments: VectorRotate {

    override fun rotateInPlace(vector: Array<String>, positions: Int) {
        if (!rotationPositionsValid(vector.size, positions)) {
            throw IllegalArgumentException("Incorrect positions count")
        }

        moveSegments(vector, positions, vector.size)
    }

    private fun moveSegments(vector: Array<String>, segmentSize: Int, lastIndex: Int) {
        if (lastIndex <= segmentSize) {
            return
        }
        val minSegmentSize = calcMinSegmentSize(segmentSize, lastIndex)
        val buffer = Array(minSegmentSize, {""})
        // copy the last segment in the buffer
        System.arraycopy(vector, lastIndex - minSegmentSize, buffer, 0, minSegmentSize)
        // copy the first segment to the last segment
        System.arraycopy(vector, 0, vector, lastIndex - segmentSize, segmentSize)
        // copy the buffer to the first segment
        System.arraycopy(buffer, 0, vector, 0, minSegmentSize)
        // repeat ignoring the last segment
        moveSegments(vector, segmentSize, lastIndex - minSegmentSize)
    }

    /**
     * In the case when we need to shuffle 2 uneven pieces, what is the size of the smallest?
     */
    private inline fun calcMinSegmentSize(currSegmentSize: Int, remainingChunkSize: Int): Int {
        return Math.min(currSegmentSize, remainingChunkSize - currSegmentSize)
    }
}