import 'dart:io';

// Dart:
// - Current version: `2.9.0-3.0.dev`
// - Statically typed
// - Full OO language (-> `int` is a class)
// - Single threaded like JS (e.g., Java is multithreaded)
// - Trailing `;` are required
// - 80 characters max per line
// - 'dart:core' is the main library
// - Was internally called Dash
// - Dart online: https://dartpad.dev/

// Casing:
// - snake_case for package, directory, & file names
// - lowerCamelCase for variable & function names
// - UpperCamelCase for class & type names

// `main`:
// - `void` is the result type
// - `args` are not mandatory
void main() {
  // Strings:
  // - UTF-16
  // - Type is `String` (-> capitalized)
  // - IMPORTANT: only use "" for strings containing '
  //   - Same rule for ''' & """
  // - ''' for multiline strings

  print('Hello, world!'); // Prints a `\n` (could be named `println`)
  stdout.write('Glad to ');
  stdout.write('see you \u{1d11e}\n');

  1; // Expressions can be discarded
  'Single quotes';
  "Double quotes";
  print('''A
  multiline
  string'''); //  No `\` required for breaking the string

  // Concatenation: adjacent (or `+`)
  print('Dart ' 'is ' 'fun!'); // Idiomatic
  print('Dart ' + 'is ' + 'fun!'); // Used for variables
  // Adjacent strings only works w/ literals (-> no variable allowed)

  // Variables
  // Dart features type inference
  var foo = 'Alex'; // Runtime variable
  final bar = 'Alex'; // Runtime constant
  const baz = 'Alex'; // Compile-time constant
  print('Hey: ' + foo + bar + baz);
  print('Hey: $foo${bar.length}${baz.split('').reversed.join()}'); // Idiomatic
  // IMPORTANT: compile-time constants also have access to methods

  // Types (C-S-P in IntelliJ)
  var name = 'Voyager I';
  var year = 1977; // `int`
  var antennaDiameter = 3.7; // `double` (no float)
  var flyByObjects = ['Jupiter', 'Saturn', 'Uranus', 'Neptune']; // `List`
  var image = { // `Map` literal
    'tags': ['saturn'],
    'url': '//path/to/saturn.jpg', // Ending `,` are supported
  };
  print((image['tags'] as List<String>)[0]); // `<String>` is optional here
  // `String` would be infered anyway, but it improves readability

  // Dart 2 has reified generics
  // -> A `List<String>` is not just `List`
  // -> Overcomes type erasure problems that occur in languages like Java
  var list1 = ['a'];
  print(list1.runtimeType); // List<String>
  List list2 = list1; // You should use `List<String>`
  print(list2.runtimeType); // List<String> anyway
  list2.add('b'); // `list1` is also modified
  print('list1: $list1, list2: $list2');

  // Unlike TS, inference of the `num` type works as expected
  final list3 = [1, 5.4, 4]; // Infered type: `List<num>`
}
