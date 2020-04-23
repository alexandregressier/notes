fn main() {
    // Rust = let's make /new/ mistakes

    // 1.3. Variables

    let x = 5; // Every variable in Rust has a type (implicited here b/c of type inference)
    let y: i32 = 6; // Not idiomatic: never explicit it unless there is an ambiguity
    let z = x + y;
    println!("z is {}", z); // `!` denotes a macro (used here to format arguments)

    //x += 1; // Variables are immutable by default (which is good for FP & concurrency)
    let mut _foo = 5;
    _foo += 1;
    //++a; // Rust does not have increment & decrement operators

    let _flag = true; // The `_` prefix signals an unused variable to the compiler


    // 1.4. Data types

    // Rust primitive data types:
    // - Simple:
    //   - Boolean: `bool` (`true` or `false`)
    let a = true;
    let b = false;

    if a { // No `()`!
        println!("`a` is true!"); // IMPORTANT: do not let yourself thinking that `;` are optional
    }
    if b {
        println!("`b` is true!");
    }

    //   - Integers:
    //     - Different sizes (for optimal resource usage): `i8`, i16`, `i32` (default), `i64`
    //       - Let the default `i32` if you are not sure of which type to use
    //     - Signed & unsigned: `i32` VS `u32`...
    //       - `i8` = 1 bit for the sign + 7 bits for the value
    //       - `u8` = 8 bits for the value
    //       -> Note that this bit is not changing the total amount of possible values
    //       -> `0` is the first value of any unsigned number type
    //     - `isize` & `usize` are architecture dependent pointer-sized integer types
    //       - E.g., x86_64 -> 64 bits
    //       - Used for indexing into collections or counting items
    let c = [100, 200, 300]; // An array
    let d = c[0]; // `0` infered type is `usize`
    println!("{:?}", c); // `:?` is the format specifier for compound types
    println!("{}", d);

    // CLION TIPS:
    // - type `d.println` & expand it
    // - type `p` & expand it

    //   - Floating point numbers:
    //     - Different sizes: `f32`, `f64` (default)
    //       - `f64` are more precise than `f32`
    //       - "minifloats" do not exist!
    //     - Like Kotlin, omitting `0` is not allowed (e.g., `.012`)
    //     - Floating point number computation problems: https://floating-point-gui.de/
    let e = 0.1 + 2.0; // -> `3.0`; would be `0.30000000000000004` in other languages
    println!("{:?}", e);
    let f = 1.01;
    println!("{:?}", f);

    //   - Characters: `char`
    //     - Unicode scalar value (not only ASCII)
    let _g = 'a';
    let _h = '串';

    // - Compound:
    //   - Tuple:
    //     - Group multiple values into one *type*
    //     - Heterogeneous: values do not have to be of the same type
    //     - Can be separated into pieces via /destructuring/ (i.e., pattern matching)
    let tup = (1, 'c', true);
    let first = tup.0; // No `_`
    let second = tup.1;
    println!("{:?}", tup);
    println!("The first is `{}` & the second is `{}`", first, second);

    let (_x, _y, _z) = tup; // Destructuring in action

    //   - Arrays:
    //     - Group multiple values into one *value*
    //     - Homogeneous
    //     - IMPORTANT: have a fixed length when you initialize them
    //       - If you need a sequential type that can change in size, use `Vec` from the /libstd/
    let j = [0.1, 3.14, 2.71, -8.7928];
    let pi = j[1];
    println!("{}", pi);

    let mut k = [7.2, 10.4, 345.01]; // Arrays can be mutable as well
    k[0] = 0.0;
    println!("{:?}", k);

    let mut _l = [7.2, 10.4, 345.01];
    //_l += 78; // Error: cannot use `+=` on type `[{float}; 3]`

    //   - Slices:
    //     - Reference to a contiguous subset of data in another data structure
    //     - Lower bound is inclusive whereas upper bound is exclusive
    let m = [100, 200, 300];
    let n = &m[0..2]; // Type: `&[i32]`; a slice of `i32`s
    println!("{:?}", n);
    let o = &m[0..1]; // Still of type: `&[i32]`
    println!("{:?}", o);

    // IMPORTANT: in Rust, string literals are string slices
    let _s = "yo";
    // It is a specific kind of slice written as `&str`

    // Rust has more complex data types such as `struct`s & `enum`s


    // 1.5. Functions

    // Functions are used to group code so that it can be called multiple times w/ different values

    // Defining a function:
    // fn name(param1: type1, ...) -> return_type {
    //     ...body...
    // }
    // IMPORTANT: unlike Java, there are no `void` return type: leave off the `-> return_type` part
    fn add(a: i32, b: i32) -> i32 {
        return a + b; // `return` is not idiomatic
    }

    // By convention, Rust tends to use:
    // - snake_case for "value-level" constructs (e.g., functions, methods)
    // - CamelCase for "type-level" constructs (e.g., types, traits)

    // Since string literals are string slices in Rust, the type must be written as `&str`
    fn next_birthday(name: &str, current_age: u8) { // `u8` is very appropriate for a human age
        let next_age = current_age + 1;
        println!("Hi {}, on your next birthday, you'll be {}!", name, next_age);
    }

    // Calling (a.k.a. applying) a function:
    println!("{} + {} = {}", 5, 7, add(5, 7));
    next_birthday("Alex", 22);

    // `a` & `b` are the /parameters/ of the `add`
    // `5` & `7` are the /arguments/ of the `add` function call

    // Returning values from functions (the idiomatic way):
    // IMPORTANT: you can omit the `return` keyword by omitting the `;` of the last line in the body
    fn square(num: i32) -> i32 {
        num * num
    }
    // You will get an error if you put a `;` w/o `return`

    // When the last expresion of a function block (or a simple block) does not end w/ `;`, this
    // expression is returned
    let outer = {
        let inner = square(2);
        inner * inner
    };
    println!("{}", outer);

    // Unlike Scala, Rust does not support single line functions

    // FUNDAMENTAL: almost everything in Rust is an expression (which makes it a good FP candidate?)
}
