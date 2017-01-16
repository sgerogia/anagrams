package com.bloomberg.assignment

import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains

class TrieTest {

    @Test
    fun shouldAcceptPath() {
        // arrange
        val path = TriePath.fromWord("test")
        val trie = Trie()
        // act
        trie.accept(path)
        // assert
        assertThat(trie.children.size, `is`(1))
        assertThat(trie.words.size, `is`(0))
        assertThat(trie.children['e']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.children.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words, contains("test"))
    }

    @Test
    fun shouldAcceptPaths_NoAnagrams() {
        // arrange
        val path1 = TriePath.fromWord("test")
        val path2 = TriePath.fromWord("se")
        val trie = Trie()
        // act
        trie.accept(path1)
        trie.accept(path2)
        // assert
        assertThat(trie.children.size, `is`(1))
        assertThat(trie.words.size, `is`(0))
        assertThat(trie.children['e']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.words.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.words, contains("se"))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.children.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words, contains("test"))
    }

    @Test
    fun shouldAcceptPaths_Anagrams() {
        // arrange
        val path1 = TriePath.fromWord("test")
        val path2 = TriePath.fromWord("tets")
        val trie = Trie()
        // act
        trie.accept(path1)
        trie.accept(path2)
        // assert
        assertThat(trie.children.size, `is`(1))
        assertThat(trie.words.size, `is`(0))
        assertThat(trie.children['e']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children.size, `is`(1))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.words.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.children.size, `is`(0))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words.size, `is`(2))
        assertThat(trie.children['e']!!.children['s']!!.children['t']!!.children['t']!!.words, contains("test", "tets"))
    }

    @Test
    fun shouldCollectAnagrams() {
        // arrange
        val trie = Trie()
        trie.accept(TriePath.fromWord("test"))
        trie.accept(TriePath.fromWord("tets"))
        trie.accept(TriePath.fromWord("an"))
        trie.accept(TriePath.fromWord("foo"))
        trie.accept(TriePath.fromWord("pots"))
        trie.accept(TriePath.fromWord("stop"))
        trie.accept(TriePath.fromWord("tops"))
        trie.accept(TriePath.fromWord("fast"))
        trie.accept(TriePath.fromWord("bar"))
        trie.accept(TriePath.fromWord("areallylongword"))
        val anagrams: MutableList<Collection<String>> = mutableListOf()

        // act
        trie.collectAnagrams(anagrams)

        // assert
        assertThat(anagrams.size, `is`(2))
        assertThat(anagrams, contains(
                listOf("test", "tets") as Collection<String>,
                listOf("pots", "stop", "tops") as Collection<String>))
    }
}