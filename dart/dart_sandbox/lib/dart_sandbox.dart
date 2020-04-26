// Single line function

// Doc comments:
// - Start w/ a single-sentence summary (and add a /blank line/)
// - Start w/ a verb
// - Write at the 3rd person to describe what the function does
// - Dart discourages using verbose documentation (-> w/ `@param` & the like)

/// Performs a simple computation.
int calculate() => 6 * 7;

/// Adds 3 numbers.
num add(num a, num b, num c) => a + b + c;

// Reified generics in action:
/// Adds 1 to all elements of [nums].
List<num> add1ToAll(final List<num> nums) =>
    nums.map((e) => e + 1).toList(); // `.toList()` is required

/// Appends a dash to all elements of [strings].
List<String> appendDashToAll(final List<String> strings) =>
    strings.map((e) => e + '-').toList();

// Generator functions
// Can be:
// - Asynchronous (results in a stream of value)
// - Synchronous (results in an iterable w/ values)
// Generators are lazy: waits for iterating the iterable/listening to the stream
Iterable<int> get positiveIntegers sync* {
  var i = 0;
  while (true) yield i++;
}
