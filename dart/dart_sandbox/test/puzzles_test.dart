import 'dart:math';

import 'package:dart_sandbox/puzzles.dart';
import 'package:test/test.dart';

void main() {
  group('`average`', () {
    test('outputs 0 when given `[]`', () =>
      expect(average([]), equals(0)));

    test('outputs 2 when given `[1,2,3]`', () =>
      expect(average([1,2,3]), equals(2)));
  });
  group('`averageBy`', () {
    test('outputs 0 when given `(x) => 0` and `[]`', () =>
      expect(averageBy((x) => 0, []), equals(0)));

    test('outputs 4 when given `(x) => x * 2` and `[1,2,3]`', () {
      final xs = [1,2,3];
      expect(averageBy((x) => x * 2, xs), equals(average(xs) * 2));
    });
  });
}
