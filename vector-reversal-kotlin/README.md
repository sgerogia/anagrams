Vector reversal
-------------------

# Assignment wording

Rotate a one dimensional vector of n elements left by i positions.
eg n=8, i=3 the vector abcdefgh is rotated to defghabc.
Simple code would use an intermediate n vector element to do the job in n steps.
Can you rotate in time proportional to n using only a little more storage?

# Discussion

The discussion in [this book](https://books.google.co.uk/books?id=4gX0CwAAQBAJ&lpg=PT28&ots=WFCrGF-9AJ&pg=PT28#v=onepage&f=false)
covers the different options for solving this.

The wording seems to imply picking solution 2 (x modulo n) or 3 (swapping segments).

I have chosen to implement solution 3 (O(logn)) and 4 (a<sup>r</sup>b<sup>r</sup>)<sup>r</sup> (O(2n)).

