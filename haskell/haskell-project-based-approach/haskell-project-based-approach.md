# A project based approach to learning Haskell, Monadic Party 2019 - Notes

Haskell features type inference

Haskell uses the same comment format as SQL: `--`

Haskell's stdlib is called _Prelude_

`pi` is a variable defined in _Prelude_

`1` -> Type `Num`
`"hi"` -> Type: `[Char]`

Types can be divided into (type) _classes_
Type class operations: the operations supported by the type class

Classes can be parameterized by a type
Example: the class of `Convertible` thing

Class of Convertible things:

Instances:
- Convertible Car
- Convertible Desk
- Convertible Sofa

Type class operations:
- Convert (common to all the types)


Types in Haskell:
- Integers: ...,-3,-2,-1,0,1,2,3,...
- Not Integers: "Haskell" True False 1.123

Class of Num things:

Instances of Num(eric):
- Num Integer
- Num Double
- Num Float

Type class operations:
- Add
- Subtract
- Multiply
- ...

Getting information about Num:
`> :i Num`
```haskell
class Num a where -- <- The definition of type class: Name + arguments
  (+) :: a -> a -> a
  (-) :: a -> a -> a
  (*) :: a -> a -> a
  negate :: a -> a
  abs :: a -> a
  signum :: a -> a
  fromInteger :: Integer -> a
  {-# MINIMAL (+), (*), abs, signum, fromInteger, (negate | (-)) #-}
        -- Defined in ‘GHC.Num’
instance Num Word -- Defined in ‘GHC.Num’
instance Num Integer -- Defined in ‘GHC.Num’
instance Num Int -- Defined in ‘GHC.Num’
instance Num Float -- Defined in ‘GHC.Float’
instance Num Double -- Defined in ‘GHC.Float’
```
 `a` is a _type variable_ (_i.e._, its type is a type)
 - Filled in when the type class is instantiated

All Haskell functions only take 1 argument at most
The implicit `()` in the type signature and _syntax sugar_ make it appear as if there are more arguments
To use `++` as an example, the following transformations are all equivalent

```haskell
xs ++ ys = ...
(++) xs ys = ...
(++) xs = \ys -> ...
(++) = \xs -> (\ys -> ...)
(++) = \xs ys -> ...
```

`a -> a -> a` can be read as:
> This is a function taking an integer as an input as produces a function taking another integer and returning an integer

With the expression `1+"hi"`:

> No instance for (Num [Char]) arising from a use of ‘+’

`(Num [Char])` is a type class
-> There is no instance of the type class Num that takes a sequence of character as an input 

With the operation `(+)`, you instantiate another instance of type class by adding another type `a` to `it`

`a` is your usual `<T>`

`(+) :: a -> a -> a` <- The functions take the same time as input & ouput

1 (`Integer` probably) + 1.2 (`Float` probably) <=> Num w/ `a`= `Float`
-> And there is a `+` operation

## Where do the types come from?

> Algebra (from Arabic "al-jabr", literally meaning "reunion of broken parts") is the study of **mathematical symbols** and the rules for manipulating these symbols
-> Taking elementary pieces & construct bigger pieces (reminds of function composition)

> Algebraic Data Types (ADT): an algebra with "mathematical symbols" for making types
-> Originates for the paper "HOPE: An experimental applicative language", 1980

GHCi features autocompletion by pressing `TAB`

Getting information about `Bool`:
`> :i Bool`
```haskell
data Bool = False | True        -- Defined in ‘GHC.Types’
instance Eq Bool -- Defined in ‘GHC.Classes’
instance Ord Bool -- Defined in ‘GHC.Classes’
instance Show Bool -- Defined in ‘GHC.Show’
instance Read Bool -- Defined in ‘GHC.Read’
instance Enum Bool -- Defined in ‘GHC.Enum’
instance Bounded Bool -- Defined in ‘GHC.Enum’
```
- `data` defines a new type
- `Bool` is its name
- It has 2 constructors: `False` | ("or") `True`

By convention, types & their constructors are capitalized

Do not confuse types and constructors:
```haskell
data Foo = Foo
```
- `Foo != Foo`, the 1st begin the type and the 2nd the constructor
-> The constructor `Foo` is used to construct something of type `Foo`

Lists:
```haskell
a = [1,2,3] -- `,` is used instead of `, ` by convention
head a -- 1
tail a -- [2,3]
init a -- [1,2]
last a -- 3
```
Lists can contain only one type (unlike Tuples)

Getting information about `[]`:
`> :i []` or `[]
```haskell
data [] a = [] | a : [a]        -- Defined in ‘GHC.Types’
instance Applicative [] -- Defined in ‘GHC.Base’
instance Eq a => Eq [a] -- Defined in ‘GHC.Classes’
instance Functor [] -- Defined in ‘GHC.Base’
instance Monad [] -- Defined in ‘GHC.Base’
instance Monoid [a] -- Defined in ‘GHC.Base’
instance Ord a => Ord [a] -- Defined in ‘GHC.Classes’
instance Semigroup [a] -- Defined in ‘GHC.Base’
instance Show a => Show [a] -- Defined in ‘GHC.Show’
instance Read a => Read [a] -- Defined in ‘GHC.Read’
instance Foldable [] -- Defined in ‘Data.Foldable’
instance Traversable [] -- Defined in ‘Data.Traversable’
```
The first line means:
> A list is either the empty list or an element followed by a list

```haskell
data [] a = [] | a : [a]
-- can also be written as
data [] a = [] | a : ([] a)
```
- `[]` is the name of the type & `[]` is **also a constructor**
- `a` is the type variable (here defines the type of the list elements)
-  `:` is the _cons_ operator (for _list constructing_, which is distinct from type constructors)

**FUNDAMENTAL:** `([] a)` is not a Tuple! It is a type class? just like `(Num [Char])`
-> [] is a type class? w/ the single type parameter `a`

The recursivity of the defition is more visible in the 2nd line (`data [] a`: declaration, `([] a)` or `[a]`: usage)

Getting information about Tuples:
`> :i ()`
```haskell
data () = ()    -- Defined in ‘GHC.Tuple’
-- ...
```

In computer science, list are super important
-> There are even languages like _Lisp_ (for _**Lis**t **p**rocessor) based on it
-> Racket, Clojure, Scheme are dialects of Lisp
