-- (\x -> x) "hi"
sumSquareOrSquareSumWhere x y =
  if sumSquare > squareSum
    then sumSquare
    else squareSum
  where
    sumSquare = x ^ 2 + y ^ 2
    squareSum = (x + y) ^ 2

sumSquareOrSquareSumExpr x y =
  if x ^ 2 + y ^ 2 > (x + y) ^ 2
    then x ^ 2 + y ^ 2
    else (x + y) ^ 2

body sumSquare squareSum =
  if sumSquare > squareSum
    then sumSquare
    else squareSum

sumSquareOrSquareSumBody x y =
  body (x ^ 2 + y ^ 2) ((x + y) ^ 2)

-- Recreating variables a la where
sumSquareOrSquareSumLambda x y =
  (\sumSquare squareSum ->
     if sumSquare > squareSum
       then sumSquare
       else squareSum)
    (x ^ 2 + y ^ 2) -- IMPORTANT: () are mandatory!!!
    ((x + y) ^ 2) -- IMPORTANT: () are mandatory!!!

doubleDoubleWhere x = dubs * 2
  where
    dubs = x * 2

doubleDouble x = (\x -> x * 2) (x * 2)

sumSquareOrSquareSumLetIn x y =
  let sumSquare = (x ^ 2 + y ^ 2)
      squareSum = ((x + y) ^ 2)
   in if sumSquare > squareSum
        then sumSquare
        else squareSum

-- Whether to choose to use `let` or where is a matter of style the vast majority
-- of the time.
overwrite =
  let x = 2
   in let x = 3
       in let x = 4
           in x

overwriteLambda = (\x -> (\x -> (\x -> x) 50) 3) 2 -- IMPORTANT: () are not mandatory for 2

-- Note: 2 possibilities: you can either replace the x in lambda's body or the argument
-- IMPORTANT!!!: results in 51!!!
overwriteImportant = (\x -> (\x -> (\x -> x) 50) 3) 1 + 1

x = 4

add1 y = y + x -- with y = 1, 5

add2 y = (\x -> y + x) 3 -- with y = 1, 4

add3 y = (\y -> (\x -> y + x) 1) 2 -- with y = 1, 3

simple x = (\x -> x) x

calcChange owed given =
  (\change ->
     if change > 0
       then change
       else 0)
    (owed - given) -- IMPORTANT: () are mandatory!!! Otherwise owed would be passed
                   -- and then given would be subtracted from the result

doublePlusTwo x = (\doubleX -> doubleX + 2) (x * 2)

-- CRITICAL!!!: Do not hesitate to return a function (+ remove the main arg)
-- to skip one argument pass!!!
incBad x = (\x -> x + 1) x

inc = \x -> x + 1

double = \x -> x * 2

square = \x -> x * x

f n =
  (\isEven ->
     if isEven
       then n - 2
       else 3 * n + 1)
    (even n)

-- IMPORTANT: `let` and lambdas are not exactly the same thing under the hood!
-- Error! Infinite loop
counter x =
  let x = x + 1 -- Cannot redefine x with x using `let`
   in let x = x + 1
       in x

-- Error! Infinite loop
counterNoAdd x =
  let x = x
   in let x = x
       in x

-- with x = 1, 3
counterXyz x =
  let y = x + 1
   in let z = y + 1
       in z

-- with x = 1, 1
counterXyzNoAdd x =
  let y = x
   in let z = y
       in z

counterLambda x = (\x -> x + 1) ((\x -> x + 1) x)

counterLambdaId x = (\x -> x + 1) ((\x -> x + 1) ((\x -> x) x))

counterLambda2 x = (\() -> ((\x -> x + 1) x) + 1) ()

counterLambda3 = \x -> ((\x -> x + 1) x) + 1

-- Question: For 1 arg function, are there 1 or 2 steps with currying ?
-- (First step would always return a function)
-- If there are two steps, counter x = ... and counter = \x -> ... might look alike

-- LESSON: all function with args can be replaced with no-args function returning a function
