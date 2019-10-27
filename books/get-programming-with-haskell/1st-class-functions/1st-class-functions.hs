import Data.List -- A module. Needed for the `sort` and `sortBy` functions.

ifEvenIncOld n =
  if even n
    then n + 1
    else n

ifEvenDoubleOld n =
  if even n
    then n * 2
    else n

ifEvenSquareOld n =
  if even n
    then n ^ 2
    else n

ifEven myFunction x =
  if even x
    then myFunction x
    else x

inc = \n -> n + 1

double = \n -> n * 2

square n = n ^ 2 -- works the same way

-- Naming sets of computation (i.e., functions) is preferred and is generally a good idea
ifEvenInc n = ifEven inc n

ifEvenDouble n = ifEven double n

ifEvenSquare n = ifEven square n

-- FUNDAMENTAL: in Haskell, functions are *always* evalutated before operations
add x y = x + y

--add 1 2 * 3 -- 9
--ifEven (\x -> x * 2) 6 -- 12
--ifEven (\x -> x ^ 3) 4 -- 64
author = ("Will", "Kurt")

--fst author -- "Will"
--snd author -- "Kurt"
names =
  [ ("Hank", "Hill")
  , ("Dale", "Gribble")
  , ("Bill", "Dauterive")
  , ("Jeff", "Boomhauer")
  , ("Bobby", "Hill")
  , ("Peggy", "Hill")
  ] -- List indexing is made via the !! operator

--names !! 1 -- ("Bernard", "Summer")
--sort names
compareStringsElse string1 string2 eq =
  if string1 < string2
    then LT
    else if string1 > string2
           then GT
           else eq

compareNames name1 name2 =
  compareStringsElse
    (snd name1)
    (snd name2)
    (compareStringsElse (fst name1) (fst name2) EQ)
 -- IMPORTANT: anything can be compared in Haskell with a function called `compare`

compareNamesAlt name1 name2 =
  if lastNamesComparison == EQ
    then compare (fst name1) (fst name2)
    else lastNamesComparison -- EQ
  where
    lastNamesComparison = compare (snd name1) (snd name2)

--sortBy compareNames names
addressLetterOld name location = formattedName ++ " - " ++ location
  where
    formattedName = (fst name) ++ " " ++ (snd name)

-- IMPORTANT: test if the first letter of a string starts with 'c'
-- or later in the alphabet: if str < "c"
formatName name = (fst name) ++ " " ++ (snd name)

sfOffice name =
  formattedName ++
  " - " ++
  (if (snd name) < "L"
     then "PO Box 1234 - San Francisco, CA, 94111"
     else "PO Box 1010 - San Francisco, CA, 94109")
  where
    formattedName = formatName name

nyOffice name = formattedName ++ ": " ++ "PO Box 789 - New York, NY, 10013"
  where
    formattedName = formatName name

renoOffice name = formattedName ++ " - " ++ "PO Box 456 - Reno, NV, 89523"
  where
    formattedName = snd name

dcOffice name = formattedName ++ " - " ++ "PO Box 1337 - Washington DC, 20001"
  where
    formattedName = formatName name ++ " Esq."

getLocationFunction location =
  case location of
    "ny" -> nyOffice
    "sf" -> sfOffice
    "reno" -> renoOffice
    "dc" -> dcOffice
    _ -> \name -> formatName name -- '_' is a wildcard; results in a generic solution

addressLetter name location = (getLocationFunction location) name

addressLetterAlt name location = locationFunction name
  where
    locationFunction = getLocationFunction location
