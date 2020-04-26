import 'package:dart_sandbox/gressier.dart';
import 'package:test/test.dart';

void main() {
  test('`identity` results in the given value', () =>
      expect(identity(42), equals(42)));

  group('Extension `FunctionalIterable<E>`', () {
    var iterable = [4, 8, [15, 16], 23, 42];

    test('`.head` results in the first iterable element', () =>
        expect(iterable.head, equals(4)));

    test('`.tail` results in all but first iterable elements', () =>
        expect(iterable.tail, equals([8, [15, 16], 23, 42])));

    test('`.init` results in all but last iterable elements', () =>
        expect(iterable.init, equals([4, 8, [15, 16], 23])));
  });
}
