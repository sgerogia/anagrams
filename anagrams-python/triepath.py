#!/usr/bin/python

class TriePath:

    def __init__(self, theWord, thePath):
        self.word = theWord
        self.path = thePath

    def stepOne(self):
        if len(self.path) == 0:
            return self
        return TriePath(self.word, self.path[1:])

    def nextChar(self):
        if len(self.path) == 0:
            return None
        return self.path[0]

    def isEmpty(self):
        return len(self.path) == 0


def fromWord(word):

    sortedChars = sorted(list(word))
    return TriePath(word, sortedChars)

