inc n = n + 1

double n = n * 2

square n = n ^ 2

ifEven f x =
  if even x
    then f x
    else x

genIfEven f = \x -> ifEven f x

-- When you capture a value inside a lambda function, this is referred to as a /closure/
-- FUNDAMENTAL: all parameters starting from the lambda are optional
-- (i.e., to partially apply)
a = 8

ifEvenInc = genIfEven inc

aIncremented = genIfEven inc a

--ifEvenInc a -- 9
-- A function that creates a closure with x and returns a new function that allows the user
-- to pass in a function to x if x is even
genIfXEven x = \f -> ifEven f x

ifAEven = genIfXEven a

aDoubled = ifAEven double

-- Anytime you might want to use a closure (which in Hakell is pretty much anytime), you
-- want to order your arguments from most to least general (b/c more likely to be
-- factorized).
getRequestUrl host apiKey resource id =
  host ++ "/" ++ resource ++ "/" ++ id ++ "?token=" ++ apiKey
--getRequestUrl "https://ninjava.io" "1337hAsk3ll" "dogs" 214628 -- 214628 must be a string

genHostRequestBuilder host =
  \apiKey resource id -> getRequestUrl host apiKey resource id

exampleUrlBuilder = genHostRequestBuilder "http://example.com"
--exampleUrlBuilder "1337hAsk3ll" "dogs" "214628"

-- You can combine both functions as arguments and functions as return values
genApiRequestBuilder hostBuilder apiKey =
  \resource id -> hostBuilder apiKey resource id
-- hostBuilder is used, but you could restart from scratch as well
--genApiRequestBuilder host apiKey =
--  \resource id -> genRequestUrl host apiKey resource id

apiExampleUrlBuilder = exampleUrlBuilder "1337hAsk3ll"

--apiExampleUrlBuilder "dogs" "214628"

genResourceRequestBuilder apiKeyBuilder resource =
  \id -> apiKeyBuilder resource id
-- You could reuse a hostBuilder as well
--genResourceRequestBuilder hostBuilder apiKey resource =
--  \id -> hostBuilder apiKey resource id

resourceExampleUrlBuilder =
  genResourceRequestBuilder apiExampleUrlBuilder "dogs"

--resourceExampleUrlBuilder "214628"

-- Lambda functions are powerful and useful, but can definitely clutter up otherwise neat
-- function definitions

-- In Haskell, no error is thown when calling a function with fewer than the required number
-- of args: it returns a partially applied function instead (i.e., a closure)
-- This language feature is called /partial application/

add4 a b c d = a + b + c + d

addXto3 x = \b c d -> add4 x b c d

addXto2 x y = \c d -> add4 x y c d

mystery = add4 3 -- A brand new function is created
--mystery 2 3 4 -- 12

anotherMystery = add4 2 3
--anotherMystery 1 2 -- 8

exampleBuilder = getRequestUrl "https://ninjava.io" "1337hAsk3ll" "dogs"
--exampleBuilder "214628"

-- For cases where the rule "from most to least general" is not applied, you may want to
-- write explictly your closure with a lambda function (or use something like `flip`)

-- When you use partial application, the arguments are applied first to last

-- Rule violation (i.e., name is more specific than location):
getLocationFunction location =
  case location of
    _ -> \name -> (fst name) ++ " " ++ (snd name)

addressLetter name location = (getLocationFunction location) name

-- Ad-hoc solution
addressLetter2 location name = addressLetter name location

-- General solution
flipBinaryArgs binaryFunction = \x y -> binaryFunction y x
addressLetter2Alt = flipBinaryArgs addressLetter -- x and y are not applied of course
--addressLetter2Alt "ny" ("Alexandre", "Gressier")

-- IMPORTANT: there are ',' in tuples

-- In Haskell, any infix operator can be used as a prefix operator by putting () around it:
--2 + 3
--(+) 2 3
-- 10 / 2
--(/) 10 2
--(+) 2 3 4 -- DOES NOT WORK

-- FUNDAMENTAL: you do not need x when creating a function
subtract2 = flip (-) 2

-- IMPORTANT: with lambda functions, first-class functions, and closures, you have all you

-- need to perform functional programming
-- Closures combine lambda functions and first-class functions to give amazing power

ifEvenInc2 = ifEven inc

ifEvenDouble2 = ifEven double

ifEvenSquare2 = ifEven square

-- If Haskell did not provide partial application, you could still explictly define closures
-- every time
binaryPartialApplication binaryFunction x = \y -> binaryFunction x y

takeFrom4 = binaryPartialApplication (-) 4
