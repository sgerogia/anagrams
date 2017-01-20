Anagrams
--------


# Assignment wording

Given a dictionary of english words find all sets of anagrams. eg pots, stop and tops are all anagrams of each other
because they can be formed by permuting the letters of the others.
Please do this in 2 programming languages.
If your language provides a 'generate permutations' method do not use it, please code from scratch and use basic data structures.

# Discussion

> Symbols
> * N: dictionary size
> * x: Average dictionary word size in characters
> * X: Maximum dictionary word size in characters

There are 2 approaches which I could come up with.

## Hash-based

This is based on finding a hash function which
1. returns the same value for anagrams
2. does not have any clashes to avoid false positives

For this we could use the [Prime factorization theorem](https://en.wikipedia.org/wiki/Fundamental_theorem_of_arithmetic),
assigning a prime to each letter of the alphabet (a=2, b=3,...)

Calculating the hash would have a complexity of O(x).
Since the hash function is optimal, then searching/adding would be near constant-time.

Therefore the complexity of this approach is O(Nx).

However there is a small restriction:
The 26th prime is 101.
This means that in a 32bit sized hash number we are restricted in the average/maximum size of a word without having
bit overflow.
The worst case scenario is _log100(32bit integer) ~= 4.66_ characters.
We could assume the median prime (13th prime is 46) as a more realistic value; in this case we get _log46(32bit integer)
~= 5.61_.

In other words this approach could lead to hash clashes for dictionaries with long words.

Collecting the groups of anagrams is a matter of iterating the hashtable (O(n))

### Trie-based

A [trie](https://en.wikipedia.org/wiki/Trie) does not have the length restrictions described above and is well-suited
for dictionary problems.

What we would need to do is make sure that all anagrams follow the same path on the trie.
This can be done by sorting the chars of the word alphabetically.
Sorting has a complexity of O(xlogx).

The sorted characters are then added in the trie (O(x)).

This means that the total complexity of this approach is O(N(x + xlogx)) -> O(Nxlogx).

This was chosen to be implemented as not having the restrictions of the hash-based solution.

However, the downside of this approach is collecting the anagrams;
a simple iteration of the trie is worst case (O(X^26)).
This could be improved by maintaining at runtime a separate collection of trie nodes with anagrams.