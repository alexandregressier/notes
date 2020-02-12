{-# LANGUAGE TemplateHaskell #-}
module Lens where

import Control.Lens

type Degrees = Double
type Latitude = Degrees
type Longitude = Degrees

data Meetup = Meetup {
    _name :: String,
    _location :: (Latitude, Longitude)
} deriving (Show)

makeLenses ''Meetup

extractLongitude :: Meetup -> Longitude
extractLongitude meetup = meetup ^. location . _2
