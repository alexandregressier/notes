module Main where

import Lib

main :: IO ()
main = do
    hello
    --print (6 `div` 0)
    case 6 `divSafe` 0 of
        Just x  -> print "Yes"
        Nothing -> print "No"

