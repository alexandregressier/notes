module Lib where

hello :: IO ()
hello = putStrLn "Hello!"

factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)

divSafe :: Integer -> Integer -> Maybe Integer
divSafe a 0 = Nothing
divSafe a b = Just (a `div` b)
