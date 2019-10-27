-- Something is /recursive/ if it is defined in terms of itself
-- In FP, all iteration problems must be solved through recursion
-- Often more natural than other forms of iteration in programming

-- Washing the dishes is the example of /recursive process/

-- When writing recursive functions, do not think about the recursion
-- Do not mix randomness with recursion b/c you may wait for the result forever

-- IMPORTANT: you want to think recursion as a list of goals and alternate cases

-- Euclid's algorithm:
-- a = 20, b = 16
-- a / b = 20 / 16 = 1 remainder 4
-- a = 16, b = 4
-- a / b = 16 / 4 = 4 remainder 0 <- /End goal/
-- GCD = b = 4

-- Solving recursive functions (e.g., Euclid's algorithm):
-- 1. Identify the end goal
--a `mod` b == 0

-- 2. Determine what happens when a goal is reached (i.e., what value should be returned
-- at the end state)
--if a `mod` b == 0
--then b

-- 3. List all alternate possibilities (i.e., figure out all the the ways that you can move
-- closer to your goal if your goal is not met)
-- (most of the time you have only one or two alternatives)
-- 1 alternative: the remainder is not 0
--else

-- 4. Determine your "rinse and repeat" process
--else gcd b (a `mod` b)

-- 5. Ensure that each alternative moves you toward your goal
-- Using the remainder, you are always going to be shrinking your new b
-- Worth case: you will eventually get 1 for a and b

myGcd a b =
  if remainder == 0
    then b
    else myGcd b remainder
  where
    remainder = a `mod` b

-- Note: No need to check if b > a, it will only add 1 extra call

-- With more complicated recursive processes, you will have more goals (#1) and alternate
-- possibilities (#3)
-- You will need larger `if then else` or `case`

sayAmountCases n = case n of
  1 -> "one"
  2 -> "two"
  n -> "a bunch"

-- When doing recursion, use pattern matching instead:
-- (to not be confused with structural pattern matching):
sayAmount 1 = "one"
sayAmount 2 = "two"
sayAmount n = "a bunch"

-- IMPORTANT: The trick with recursion is to think in terms of patterns

-- B/c of pattern matching, parameters can be literals in Haskell
-- IMPORTANT: just like `case`, pattern matching looks at the options in order
-- If `sayAmount n` was placed first, `sayAmount` would always return "a bunch"

-- CRITICAL: Pattern matching can ONLY LOOK at arguments but CANNOT DO ANY COMPUTATION on
-- them when matching
-- E.g., you cannot check whether `n` is less than 0
isEmptyAlt [] = True
isEmptyAlt xs = False

-- Standard practice: use `_` as wildcard for values you do not use
-- Popular convention in Haskell: use `x` to designate a single value, and xs to designate a
-- a list of values
isEmpty [] = True
isEmpty _ = False

-- More sophisticated pattern matching on lists:
myHeadAlt (x:xs) = x
myHead (x:xs) = x -- () are required!
--myTail (x:xs) = xs -- ??? Haskell will not compile if this line is there

-- IMPORTANT: ':' is still cons!!!

-- `head` and `tail` will throw an error when passing them an empty list
-- So does our versions:
myHead [] = error "empty list"

myTail [] = error "empty list" -- ??? But will compile if moved here
myTail (x:xs) = xs

-- Rule: goal states should be always defined first (i.e., `myHead []` should be placed
-- first) and then all the alternate possibilities one at a time

-- CRITICAL: myGcd with pattern matching (arg1: a, arg2: remainder)
myGcdAlt b 0 = b -- you might want to use the name `a` instead
myGcdAlt a b = myGcdAlt b (a `mod` b)
