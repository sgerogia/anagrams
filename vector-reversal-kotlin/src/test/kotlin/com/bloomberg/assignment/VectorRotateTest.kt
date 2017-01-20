package com.bloomberg.assignment

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class VectorRotateTest(val impl: VectorRotate) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): Collection<Array<VectorRotate>> {
            return listOf(
                    arrayOf(VectorRotateSegments()),
                    arrayOf(VectorRotateTranspose())) as Collection<Array<VectorRotate>>
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectPositionCount() {
        impl.rotateInPlace(arrayOf("a", "b", "c"), 4)
    }

    @Test
    fun rotateTwoByOne() {
        // arrange
        val vec = arrayOf("a", "b")
        // act
        impl.rotateInPlace(vec, 1)
        // assert
        assertThat(vec, `is`(arrayOf("b", "a")))
    }

    @Test
    fun rotateEightByThree() {
        // arrange
        val vec = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")
        // act
        impl.rotateInPlace(vec, 3)
        // assert
        assertThat(vec, `is`(arrayOf("d", "e", "f", "g", "h", "a", "b", "c")))
    }

    @Test
    fun rotateEightBySeven() {
        // arrange
        val vec = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")
        // act
        impl.rotateInPlace(vec, 7)
        // assert
        assertThat(vec, `is`(arrayOf("h", "a", "b", "c", "d", "e", "f", "g")))
    }

    @Test
    fun rotateEightByEight() {
        // arrange
        val vec = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")
        // act
        impl.rotateInPlace(vec, 8)
        // assert
        assertThat(vec, `is`(arrayOf("a", "b", "c", "d", "e", "f", "g", "h")))
    }
}