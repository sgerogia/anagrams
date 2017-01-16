package com.bloomberg.assignment

import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains


class TriePathTest {

    @Test
    fun shouldGenerateCorrectPaths_Case1() {
        // arrange
        val path = TriePath.fromWord("test")
        // assert
        assertThat(path.path, contains(
                Pair('e', 1),
                Pair('s', 1),
                Pair('t', 2)
        ))
    }

    @Test
    fun shouldGenerateCorrectPaths_Case2() {
        // arrange
        val path = TriePath.fromWord("anagram")
        // assert
        assertThat(path.path, contains(
                Pair('a', 3),
                Pair('g', 1),
                Pair('m', 1),
                Pair('n', 1),
                Pair('r', 1)
        ))
    }

    @Test
    fun shouldGenerateIdenticalPathsForAnagrams() {
        // arrange
        val path1 = TriePath.fromWord("pots")
        val path2 = TriePath.fromWord("stop")
        val path3 = TriePath.fromWord("tops")
        // assert
        assertThat(path1.path, contains(
                Pair('o', 1),
                Pair('p', 1),
                Pair('s', 1),
                Pair('t', 1)
        ))
        assertThat(path1.path, `is`(path2.path))
        assertThat(path1.path, `is`(path3.path))
        assertThat(path2.path, `is`(path3.path))
    }

    @Test
    fun shouldStepPath_Case1() {
        // arrange
        val path = TriePath.fromWord("anagram")
        // act
        val newPath = path.stepOne()
        // assert
        assertThat(newPath.path, contains(
                Pair('a', 2),
                Pair('g', 1),
                Pair('m', 1),
                Pair('n', 1),
                Pair('r', 1)
        ))
    }

    @Test
    fun shouldStepPath_Case2() {
        // arrange
        val path = TriePath.fromWord("test")
        // act
        val newPath = path.stepOne()
        // assert
        assertThat(newPath.path, contains(
                Pair('s', 1),
                Pair('t', 2)
        ))
    }

    @Test
    fun shouldReturnNextChar_Case1() {
        // arrange
        val path = TriePath.fromWord("test")
        // act
        val char = path.nextChar()
        // assert
        assertThat(char, `is`('e'))
    }

    @Test
    fun shouldReturnNextChar_Case2() {
        // arrange
        val path = TriePath.fromWord("test")
        // act
        val char = path.stepOne().nextChar()
        // assert
        assertThat(char, `is`('s'))
    }
}