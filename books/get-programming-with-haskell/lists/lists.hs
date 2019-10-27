-- /Lists/ the single most important data structure in functional programming
-- (Just like arrays are to C)

-- One of the key reasons is that lists are /inherently/ recursive:
-- A list is either an empty list or an element followed by another list
-- By definition, a list is always a value consed with another list (which can be [])
-- Rephrases: a list is made up of a `head` and a `tail` that are consed together

-- FUNDAMENTAL: Nearly all sequential operation in functional programming involve
-- building lists, breaking them apart, or a combination of the two

-- Taking apart lists, the main parts are the /head/, the /tail/, and the
-- end (represented by [])
--head [1,2,3] -- 1
--head [[1,2],[3,4],[5,6]] -- [1,2]

--tail [1,2,3] -- [2,3]
--tail [3] -- [] b/c [3] == 3:[]

-- An empty list is different from other lists: it has neither a head or a tail
-- Calling `head` or `tail` on `[]` will result in an error

-- To build a list, you use the infix operator `(:)`, called /cons/
-- Short for /construct/ and has its origin in Lisp
-- The operation is refered to as /consing/

-- To make a list, you need to take a value and cons it with another list:
--1:[] -- [1]

-- Under the hood, all lists in Haskell are represented as a bund of consing operations and
-- the [...] notation is /syntactic sugar/ (i.e., a feature of the programming language
-- syntax designed solely to make things easier to read)
--1:2:3:4:[] -- [1,2,3,4
--(1,2):(3,4):(5,6):[] -- [[1,2],[3,4],[5,6]]

-- Works with the syntactic sugar version
--1:[2,3,4]

-- Strings are themselves syntactic sugar for lists of characters (denoted by '')
--"a" == ['a'] && ['a'] == 'a':[]

-- IMPORTANT: In Haskell, every element of a list must be the same type
--'h':"ello" -- OK
--"h":"ello" -- KO
--['h']:['e','l','l','o'] -- KO, first layer of sugar removed
--'h':[]:'e':'l':'l':'o':[] -- KO, completely /desugared/

-- If you want to combine two lists, you need to concatenate them by using (++)
-- (Used for String concatenation as well since Strings are just lists of characters)
--"h" ++ "ello"
--[1] ++ [2,3,4]


-- Consing is an essential part of writing recursive functions on lists

--[1..10] -- [1,2,3,4,5,6,7,8,9,10]
--[1,3..10] -- [1,3,5,7,9]
--[1,1.5..5] -- [1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0]
--[1,0.. -10] -- [1,0,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10] -- IMPORTANT: space before -10
-- is required

-- Working with lists without upper bound (i.e., infinite):
simple x = x
longList = [1..]
stillLongList = simple longList

-- Haskell did not get stuck trying to evaluated an infinitely long list b/c it uses a
-- special form of evaluation called /lazy evaluation/
-- You defined an infinite list and then used it in a function
-- No code is evaluated until it is needed (e.g., none of the value of `longList` were
-- needed for computation

-- Lazy evalutation
-- Advantages:
-- - Computational benefit that any code you do not absolutely need is never computed
-- - You can define interesting structures (e.g., infinite list)
-- - Useful for a handful of practical problems
-- Disavantages:
-- - Biggest one: much harder to reason about the code's performance (e.g., it is easy to
-- see that any arg passed to `simple` will not be evaluated, but what if it was less
-- obvious?)
-- - Even bigger: you can easily build up large collections of unevaluated functions that
-- would be much cheaper to store as values

-- CRITICAL!!!: always prefer to store evaluated functions (i.e., the results) rather than
-- unevaluated whenever possible

-- This code will not cause any trouble until backwards inifity is evaluated (e.g., typed in
-- GHCi)
backwardsInfinity = reverse [1..]

-- The Haskell standard library module is called /Prelude/
-- `head`, `tail`, `(:)`, `(++)` are part of it

-- (!!): Access a particular element of a list by its index
-- Lists in Haskell are indexed starting at 0
--[1,2,3] !! 0 -- 1
--"puppies" !! 4 -- 'i'
--[1..10] !! 11 -- ERROR: index too large

-- [Prefix][Infix][Suffix]

-- REMEMBER: any infix operator can also be used like a prefix function by wrapping it in ()
--(!!) [1,2,3] 0 -- 1

-- Prefix notation advantages:
-- - Can make things such as partial application easier
-- - Useful for using operators as args to other functions (e.g., map (3*) [1..5])
-- Of course, () are required

-- Example:
--map (3*) [1..5] == map ((*) 3) [1..5] -- True

paExample1 = (!!) "dog" -- pa = partial application
--paExample1 2 -- 'g'
paExample2 = ("dog" !!)
--paExample2 2 -- 'g'

-- A partial application on a binary operator is called a /section/
-- - Left section: e.g., (2^)
-- - Right section: e.g., (^2) <=> flip (^) (not working `flip (^2)` or `flip (2^)`)
--flip (^) 5 2 -- 32

-- IMPORTANT: To perform PA on a binary operator (i.e., a section) you NEED to wrap the
-- EXPRESSION in ():
--paExample2 = ("dog" !!)
paExample3 = (!! 3)
--paExample3 = paExample "dog" -- 'g'

-- FUNDAMENTAL: with sections, parentheses ARE NOT optional


-- Other functions:

-- length: length of a list
--length [1..20] -- 20
--length [(10,20),(30,40),(50,60)] -- 3
--length "quicksand" -- 9

-- reverse: reverses a list
--reverse [1,2,3] -- [3,2,1]
--reverse "cheese" -- "esseehc"

isPalindrome word = word == reverse word
--isPalindrome "cheese"
--isPalindrome "racecar"
--isPalindrome [1,2,3]
--isPalindrome [1,2,1]

-- elem: takes a value and a list and checks whether the value is in the list
--elem 26 [0,13..100] -- True
--elem 'p' "cheese" -- False

-- FUNDAMENTAL: any binary function (even your own) can be treated as an infix operator by
-- wrapping it in `` (called /infix form/)
--mod 8 2 -- 0
--8 `mod` 2 -- 0
--'p' `elem` "cheese" -- False

respond phrase =
  if '!' `elem` phrase
    then "wow!"
    else "uh.. okay"

--respond "hello" -- "uh.. okay"
--respond "hello!" -- "wow!"

-- take: takes a number and a list and then returns the first n elements of the list
--take 5 [2,4..] -- [2,4,6,8,10]
--take 3 "wonderful" -- "won"

-- `take` gives you what is can
-- take 1000000 [1] -- [1]

-- `take` works best when being combined with other functions: e.g., get the last n elements
-- of a list: 
takeLast n aList = reverse (take n (reverse aList))
--takeLast 10 [1..100] -- [91,92,93,94,95,96,97,98,99,100]

-- drop: the opposite of `take`, it removes the first n elements of a list
--drop 2 [1,2,3,4,5] -- [3,4,5]
--drop 5 "very awesome" -- "awesome"

-- zip: combine two given lists into tuple pairs
-- If one list happens to be longer, zip will stop whenever one the two lists is empty
--zip [1,2,3] [2,4,6] -- [(1,2),(2,4),(3,6)]
--zip "dog" "rabbit" -- [('d','r'),('o','a'),('g','b')]
--zip ['a'..'f'] [1..] -- [('a',1),('b',2),('c',3),('d',4),('e',5),('f',6)]

-- NOTE: .. for Strings does not seem to exist

-- cycle: repeats a given list endlessly
-- Employs lazy evaluation to create an infinite list
-- Comes in handy in a surprising number of situations

-- Generating a list of n 1s:
ones n = take n (cycle [1])
--ones 2 -- [1,1]
--ones 4 -- [1,1,1,1]

-- IMPORTANT: using `cycle` is extremely useful to distribute evenly members of a list into
-- groups (i.e., cycling through the groups and assigning members to them):
assignToGroupsAlt n aList = zip groups aList
  where
    groups = cycle [1 .. n]

assignToGroups n aList = zip (cycle [1 .. n]) aList

--assignToGroups 3 ["file1.txt","file2.txt","file3.txt","file4.txt","file5.txt","file6.txt","file7.txt","file8.txt"]
-- [(1,"file1.txt"),(2,"file2.txt"),(3,"file3.txt"),(1,"file4.txt"),(2,"file5.txt"),(3,"file6.txt"),(1,"file7.txt"),(2,"file8.txt")]
--assignToGroups 2 ["Bob","Kathy","Sue","Joan","Jim","Mike"]
-- [(1,"Bob"),(2,"Kathy"),(1,"Sue"),(2,"Joan"),(1,"Jim"),(2,"Mike")]

-- Haskell's Prelude (and other libraries) have many other list functions
-- FUNDAMENTAL: Prelude is included automatically into all Haskell modules (containing the
-- list functions)
-- All list functions - including those in Prelude - are in the `Data.List` module

repeatCustom n = [n] ++ repeatCustom n -- same as `repeat` from Prelude
repeatCustomAlt = cycle [n]

-- REMEMBER: DO NOT FORGET YOUR () OTHERWISE FUNCTIONS ARE EVALUATED FIRST
subseq startPos endPos list = take (endPos - startPos) (drop startPos list)
--subseq 2 5 [1..10] -- [3,4,5]
--subseq 2 7 "a puppy" -- "puppy"

len xs =
  if null xs
    then 0
    else 1 + len (tail xs)

inFirstHalf x xs = x `elem` (take ((len xs) `quot` 2) xs)

-- IMPORTANT: `take` and `drop` are very useful if you do not feel recursive today
-- The author appears to be using `elem` with ``

-- FUNDAMENTAL:
--The `quot`, `rem`, `div`, and `mod` class methods satisfy these laws if y is non-zero:
--(x `quot` y) * y + (x `rem` y) == x
--(x `div` y) * y + (x `mod` y) == x

-- `quot` is integer division truncated toward 0, while `div` is truncated toward negative
-- infinity
--(-20) `divMod` 3 -- (-7,1)
--(-20) `quotRem` 3 -- (-6,-2)

-- NOTE: variables initialized in where clauses can reuse the above initialized variables

-- Eager evaluation VS Lazy evaluation
-- Infinite list are in reality only potentially infinite depending on the context you are
-- using it
