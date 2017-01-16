package com.bloomberg.assignment


/**
 * Wrapper around a dictionary word.
 * Converts the characters into a lexicographically ordered trie path.
 * E.g. word 'test' becomes {e, s, t, t}
 */
data class TriePath(
        val word: String,
        val path: Collection<Char>) {

    companion object {

        fun fromWord(word: String): TriePath {
            val sortedChars = word.toCharArray().sorted()
            return TriePath(word, sortedChars)
        }

    }

    /**
     * Returns a copy of the path, advanced by one.
     * Examples:
     * {e, s, t} -> {s, t}
     * {e, e, s, t, t} -> {e, s, t, t}
     * {} -> {}
     */
    fun stepOne(): TriePath {
        if (path.isEmpty()) {
            return this
        }
        return copy(path = path.drop(1))
    }

    /**
     * Returns the next character in the path.
     * Examples:
     * {e, s, t, t} -> e
     * {e, e, s, t, t} -> e
     * {} -> null
     */
    fun nextChar(): Char? {
        if (path.isEmpty()) {
            return null
        }
        return path.first()
    }

    fun isEmpty(): Boolean {
        return path.isEmpty()
    }

}