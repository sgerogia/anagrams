package com.bloomberg.assignment

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.Test


class TriePathTest {

    @Test
    fun shouldGenerateCorrectPaths_Case1() {
        // arrange
        val path = TriePath.fromWord("test")
        // assert
        assertThat(path.path, contains('e', 's', 't', 't'))
    }

    @Test
    fun shouldGenerateCorrectPaths_Case2() {
        // arrange
        val path = TriePath.fromWord("anagram")
        // assert
        assertThat(path.path, contains('a', 'a', 'a', 'g', 'm', 'n', 'r'))
    }

    @Test
    fun shouldGenerateIdenticalPathsForAnagrams() {
        // arrange
        val path1 = TriePath.fromWord("pots")
        val path2 = TriePath.fromWord("stop")
        val path3 = TriePath.fromWord("tops")
        // assert
        assertThat(path1.path, contains('o', 'p', 's', 't'))
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
        assertThat(newPath.path, contains('a', 'a', 'g', 'm', 'n', 'r'))
    }

    @Test
    fun shouldStepPath_Case2() {
        // arrange
        val path = TriePath.fromWord("test")
        // act
        val newPath = path.stepOne()
        // assert
        assertThat(newPath.path, contains('s', 't', 't'))
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