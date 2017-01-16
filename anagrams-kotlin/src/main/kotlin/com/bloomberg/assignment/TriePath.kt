package com.bloomberg.assignment

import java.util.*

/**
 * Wrapper around a dictionary word.
 * Converts the characters into a lexicographically ordered trie path.
 * E.g. word 'test' becomes {e*1, s*1, t*2}
 */
data class TriePath(
        val word: String,
        val path: Collection<Pair<Char, Int>>) {

    companion object {

        fun fromWord(word: String): TriePath {
            val triePath : TreeMap<Char, Int> = TreeMap()
            word.toCharArray().forEach { c ->
                var count = triePath.getOrPut(c, { 0 })
                count += 1
                triePath.put(c, count)
            }
            return TriePath(word, triePath.map { it.toPair() }.toList())
        }

    }

    /**
     * Returns a copy of the path, advanced by one.
     * Examples:
     * {e*1, s*1, t*2} -> {s*1, t*2}
     * {e*2, s*1, t*2} -> {e*1, s*1, t*2}
     * {} -> {}
     */
    fun stepOne(): TriePath {
        if (path.isEmpty()) {
            return this
        }
        val firstItem = path.first()
        val newPath = if (firstItem.second > 1)
                        mutableListOf(Pair(firstItem.first, firstItem.second - 1)) + path.drop(1)
                        else path.drop(1)
        return copy(path = newPath)
    }

    /**
     * Returns the next character in the path.
     * Examples:
     * {e*1, s*1, t*2} -> e
     * {e*2, s*1, t*2} -> e
     * {} -> null
     */
    fun nextChar(): Char? {
        if (path.isEmpty()) {
            return null
        }
        return path.first().first
    }

    fun isEmpty(): Boolean {
        return path.isEmpty()
    }

}