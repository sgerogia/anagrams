#!/usr/bin/python

class Trie:

    def __init__(self):
        self.children = {}
        self.words = []

    def accept(self, triePath):
        if triePath.isEmpty():
            self.words.append(triePath.word)
            return
        char = triePath.nextChar()
        child = None
        if char in self.children:
            child = self.children[char]
        else:
            child = Trie()
        child.accept(triePath.stepOne())
        self.children[char] = child


    def collectAnagrams(self, allAnagrams):
        if len(self.words) > 1:
            allAnagrams.append(self.words)
        for key, value in self.children.iteritems():
            value.collectAnagrams(allAnagrams)
