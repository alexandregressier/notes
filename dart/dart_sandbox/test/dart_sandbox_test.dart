import 'package:dart_sandbox/dart_sandbox.dart';
import 'package:test/test.dart';

void main() {
  test('`calculate()` results in 42', () {
    expect(calculate(), 42);
  });

  test('`add(a, b, c)` results in the sum of a, b, & c', () {
    expect(add(4, 5, 6), [4, 5, 6].reduce((a, b) => a + b));
  });

  group('`int`', () {
    test('`.remainder()` evaluates to the remainder of division', () {
      expect(11.remainder(3), equals(2));
    });

    test('`.toRadixString()` evaluates to a hex string', () {
      expect(11.toRadixString(16), equals('b'));
    });
  });

  group('`String`', () {
    test('`.split()` splits the string on the delimiter', () {
      final string =
          'foo,bar,baz'; // You typically define a variable to make assertions on
      expect(string.split(','), equals(['foo', 'bar', 'baz']));
    });

    test('`.trim()` removes the surrounding whitespace', () {
      final string = '   foo ';
      expect(string.trim(), equals('foo'));
    });
  });

  group('`add`', () {
    test('add1toAll() adds 1 to all elements of a given list of number', () {
      final list = [1, 5.4, 4];
      expect(add1ToAll(list), equals([2, 6.4, 5]));
    });

    test(
        '`appendDashtoAll()` adds a dash to all elements of a given list of strings', () {
      final list = ['foo', 'bar-', 'BaZ'];
      expect(appendDashToAll(list), everyElement(endsWith('-')));
    });
  });

  group('`positiveIntegers`', () {
    test(
        '`positiveIntegers` can be transformed to generate the 10 first positive integers', () {
      expect(positiveIntegers.skip(1).take(10).toList(), allOf( // `skip` & not "drop"
        equals([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]),
        equals(List<int>.generate(10, (x) => x + 1)),
        equals([for (var i = 0; i < 10; ++i) i + 1]),
        hasLength(10),
      ));
    });

    test('`positiveIntegers` can be transformed to generate the 5 first positive even integers', () {
      expect(positiveIntegers.skip(1).where((x) => x % 2 == 0).take(5).toList(), allOf(
        equals([2, 4, 6, 8, 10]),
      ));
    });
  });
}
