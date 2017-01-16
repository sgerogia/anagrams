package com.bloomberg.assignment

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class Anagrams {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size < 1) {
                println("Usage: Anagrams <dictionary file location>\n" +
                        "\tDictionary must have one word per line and all words in the same case (e.g. lower-case)")
                System.exit(-1)
            }
            Anagrams().findAnagrams(args[0])
        }
    }

    fun findAnagrams(dictFile: String) {

        val file = File(dictFile)
        if (!file.isFile && !file.exists()) {
            println("File $file does not exist. Exiting...")
            return
        }

        println("Loading dictionary...")
        val lines = Files.readAllLines(Paths.get(file.toURI()))
        val trie = Trie()
        lines.forEach { trie.accept(TriePath.fromWord(it)) }
        println("\n\nLocated anagrams are below")
        val anagrams = mutableListOf<Collection<String>>()
        trie.collectAnagrams(anagrams)
        anagrams.forEach { println("\t${it.joinToString()}") }
        println("\nDone!")
    }
}