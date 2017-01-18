#!/usr/bin/python

import sys
import os.path

from trie import Trie
from triepath import fromWord

def main(argv):

    if len(argv) < 1:
        print("Usage: anagrams.py <dictionary file location>\n" +
            "\tDictionary must have one word per line and all words in the same case (e.g. lower-case)")
        sys.exit()

    findAnagrams(argv[0])

def findAnagrams(dictFile):

    if not os.path.isfile(dictFile):
        print("File {0} does not exist. Exiting...".format(dictFile))
        return

    print("Loading dictionary...")

    lines = open(dictFile,'r').read().splitlines()
    trie = Trie()
    for l in lines:
        trie.accept(fromWord(l))

    print("\n\nLocated anagrams are below")
    anagrams = []
    trie.collectAnagrams(anagrams)

    for a in anagrams:
        print(",".join(a))

    print("\nDone!")

if __name__ == "__main__":
    main(sys.argv[1:])