#!/usr/bin/python

import unittest

from triepath import fromWord
from trie import Trie

class TestTrie(unittest.TestCase):

    def test_shouldAcceptPath(self):
        # arrange
        path = fromWord("test")
        trie = Trie()
        # act
        trie.accept(path)
        # assert
        self.assertEqual(len(trie.children), 1)
        self.assertEqual(len(trie.words), 0)
        self.assertEqual(len(trie.children['e'].children), 1)
        self.assertEqual(len(trie.children['e'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].children), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].words), 1)
        self.assertEqual(trie.children['e'].children['s'].children['t'].children['t'].words, ["test"])


    def test_shouldAcceptPaths_NoAnagrams(self):
        # arrange
        path1 = fromWord("test")
        path2 = fromWord("se")
        trie = Trie()
        # act
        trie.accept(path1)
        trie.accept(path2)
        # assert
        self.assertEqual(len(trie.children), 1)
        self.assertEqual(len(trie.words), 0)
        self.assertEqual(len(trie.children['e'].children), 1)
        self.assertEqual(len(trie.children['e'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].words), 1)
        self.assertEqual(trie.children['e'].children['s'].words, ["se"])
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].children), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].words), 1)
        self.assertEqual(trie.children['e'].children['s'].children['t'].children['t'].words, ["test"])


    def test_shouldAcceptPaths_Anagrams(self):
        # arrange
        path1 = fromWord("test")
        path2 = fromWord("tets")
        trie = Trie()
        # act
        trie.accept(path1)
        trie.accept(path2)
        # assert
        self.assertEqual(len(trie.children), 1)
        self.assertEqual(len(trie.words), 0)
        self.assertEqual(len(trie.children['e'].children), 1)
        self.assertEqual(len(trie.children['e'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children), 1)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].words), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].children), 0)
        self.assertEqual(len(trie.children['e'].children['s'].children['t'].children['t'].words), 2)
        self.assertEqual(trie.children['e'].children['s'].children['t'].children['t'].words, ["test", "tets"])


    def test_shouldCollectAnagrams(self):
        # arrange
        trie = Trie()
        trie.accept(fromWord("test"))
        trie.accept(fromWord("tets"))
        trie.accept(fromWord("an"))
        trie.accept(fromWord("foo"))
        trie.accept(fromWord("pots"))
        trie.accept(fromWord("stop"))
        trie.accept(fromWord("tops"))
        trie.accept(fromWord("fast"))
        trie.accept(fromWord("bar"))
        trie.accept(fromWord("areallylongword"))
        anagrams = []
        # act
        trie.collectAnagrams(anagrams)

        # assert
        self.assertEqual(len(anagrams), 2)
        self.assertEqual(anagrams, [["test", "tets"], ["pots", "stop", "tops"]])


if __name__ == '__main__':
    unittest.main()