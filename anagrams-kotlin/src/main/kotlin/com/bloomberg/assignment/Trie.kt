package com.bloomberg.assignment

/**
 * A trie node, can have any number of words "terminating" at it.
 */
class Trie {

    val children : MutableMap<Char, Trie> = mutableMapOf()
    val words : MutableList<String> = mutableListOf()

    fun accept(path: TriePath) {
        if (path.isEmpty()) {
            words.add(path.word)
            return
        }
        val char = path.nextChar()
        val child = children.getOrElse(char!!, { Trie() })
        child.accept(path.stepOne())
        children[char] = child
    }

    fun collectAnagrams(allAnagrams: MutableList<Collection<String>>) {

        if (words.size > 1) {
            allAnagrams.add(words)
        }
        children.forEach { it.value.collectAnagrams(allAnagrams) }
    }
}