module Main where

import Lib
import Lens
import Control.Lens

meetup = Meetup "Meeting" (1.23, 4.56)

matrix = [(1, 2, 3), (4, 5, 6), (7, 8, 9)]

main :: IO ()
main = do
    hello
    --print (6 `div` 0)
    case 6 `divSafe` 0 of
        Just x  -> print "Yes"
        Nothing -> print "No"
    print (meetup ^. location . _1)
    print (extractLongitude meetup)
