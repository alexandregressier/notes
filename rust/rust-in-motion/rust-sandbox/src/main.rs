fn main() {
    let x = 5; // Every variable in Rust has a type (implicited here b/c of type inference)
    let y: i32 = 6; // Not idiomatic: never explicit it unless there is an ambiguity
    let z = x + y;
    println!("z is {}", z);

    //x += 1; // Variables are immutable by default (which is good for FP & concurrency)
    let mut a = 5;
    a += 1;
    //++a; // Rust does not have increment & decrement operators

    let flag = true;
}
