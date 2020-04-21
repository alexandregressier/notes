fn main() {
    // Rust = let's make /new/ mistakes

    let x = 5; // Every variable in Rust has a type (implicited here b/c of type inference)
    let y: i32 = 6; // Not idiomatic: never explicit it unless there is an ambiguity
    let z = x + y;
    println!("z is {}", z); // `!` denotes a macro (used here to format arguments)

    //x += 1; // Variables are immutable by default (which is good for FP & concurrency)
    let mut _foo = 5;
    _foo += 1;
    //++a; // Rust does not have increment & decrement operators

    let _flag = true; // The `_` prefix signals an unused variable to the compiler

    // Rust primitive data types:
    // - Simple:
    //   - Boolean: `bool` (`true` or `false`)
    let a = true;
    let b = false;

    if a { // No `()`!
        println!("`a` is true!"); // Do not let yourself thinking that `;` are optional
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
    //     - `isize` & `usize` are architecture dependent pointer-sized integer types
    //       - E.g., x86_64 -> 64 bits
    //       - Used for indexing into collections or counting items
    let c = [100, 200, 300]; // An array
    let d = c[0]; // `0` infered type is `usize`
    println!("{:?}", c); // `:?` is the format specifier for compound types
    println!("{}", d); // INTELLIJ TIP: type d.println and expand it

    //   - Floating point numbers:
    //     - Different sizes: `f32`, `f64` (default)
    //       - `f64` are more precise than `f32`
    //       - "minifloats" do not exist!
    //     - Like Kotlin, omitting `0` is not allowed (e.g., `.012`)
    //
    //     - https://floating-point-gui.de/
    let e = 0.1 + 2.0; // -> `3.0`; would be `0.30000000000000004` in other languages
    println!("{:?}", e);
    let f = 1.01;
    println!("{:?}", f);

    //   - Characters: `char`
    //     - Unicode scalar value (not only ASCII)
    let g = 'a';
    let h = '串';

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

    let (x, y, z) = tup; // Destructuring in action

    //   - Arrays:
    //     - Group multiple values into one *value*
    //     - Homogeneous
    //     - IMPORTANT: have a fixed length when you initialize them
    let j = [0.1, 3.14, 2.71, -8.7928];
    let pi = j[1];
    println!("{}", pi);

    let mut k = [7.2, 10.4, 345.01]; // Arrays can be mutable as well
    k[0] = 0.0;
    println!("{:?}", k);

    let mut l = [7.2, 10.4, 345.01];
    //l += 78; // Error: cannot use `+=` on type `[{float}; 3]`

    // -> If you need a sequential type that can change in size, use `Vec` in the libstd

    //   - Slices:
    //     - Reference to a contiguous subset of data in another data structure
    //     - Lower bound is inclusive whereas upper bound is exclusive
    let m = [100, 200, 300];
    let n = &m[0..2]; // Type: `&[i32]`; a slice of `i32`s
    println!("{:?}", n);
    let o = &m[0..1]; // Still of type: `&[i32]`
    println!("{:?}", o);

    // IMPORTANT: in Rust, string literals are string slices
    let s = "yo";
    // It is a specific kind of slice written as `&str`

    // Rust has more complex data types such as `struct`s & `enum`s
}
