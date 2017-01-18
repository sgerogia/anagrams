#!/usr/bin/python

import unittest

from triepath import fromWord

class TestTriePath(unittest.TestCase):

    def test_shouldGenerateCorrectPaths_Case1(self):
        # arrange
        path = fromWord("test")
        # assert
        self.assertEqual(path.path, ['e', 's', 't', 't'])


    def test_shouldGenerateCorrectPaths_Case2(self):
        # arrange
        path = fromWord("anagram")
        # assert
        self.assertEqual(path.path, ['a', 'a', 'a', 'g', 'm', 'n', 'r'])


    def test_shouldGenerateIdenticalPathsForAnagrams(self):
        # arrange
        path1 = fromWord("pots")
        path2 = fromWord("stop")
        path3 = fromWord("tops")
        # assert
        self.assertEqual(path1.path, ['o', 'p', 's', 't'])
        self.assertEqual(path1.path, path2.path)
        self.assertEqual(path1.path, path3.path)
        self.assertEqual(path2.path, path3.path)


    def test_shouldStepPath_Case1(self):
        # arrange
        path = fromWord("anagram")
        # act
        newPath = path.stepOne()
        # assert
        self.assertEqual(newPath.path, ['a', 'a', 'g', 'm', 'n', 'r'])


    def test_shouldStepPath_Case2(self):
        # arrange
        path = fromWord("test")
        # act
        newPath = path.stepOne()
        # assert
        self.assertEqual(newPath.path, ['s', 't', 't'])


    def test_shouldReturnNextChar_Case1(self):
        # arrange
        path = fromWord("test")
        # act
        char = path.nextChar()
        # assert
        self.assertEqual(char, 'e')


    def test_shouldReturnNextChar_Case2(self):
        # arrange
        path = fromWord("test")
        # act
        char = path.stepOne().nextChar()
        # assert
        self.assertEqual(char, 's')



if __name__ == '__main__':
    unittest.main()