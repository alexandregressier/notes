messyMain :: IO ()
messyMain = do
  print "Who is the email for?"
  recipient <- getLine
  print "What is the title?"
  title <- getLine
  print "Who is the author?"
  author <- getLine
  print
    ("Dear " ++
     recipient ++
     ",\n" ++ "Thanks for buying " ++ title ++ "\n" ++ "Thanks,\n" ++ author)
-- () are required here!!!


toPart recipient = "Dear " ++ recipient ++ ",\n"

bodyPart bookTitle = "Thanks for buying '" ++ bookTitle ++ "'.\n"

fromPart author = "Thanks,\n" ++ author ++ "\n"

createEmail recipient bookTitle author =
  toPart recipient ++ bodyPart bookTitle ++ fromPart author

main = do
  print "Who is the email for?"
  recipient <- getLine
  print "What is the title?"
  title <- getLine
  print "Who is the author?"
  author <- getLine
  print (createEmail recipient title author)
