import 'dart:core';

T identity<T>(T x) => x;

extension FunctionalIterable<E> on Iterable<E> {
  E get head => first;
  Iterable<E> get tail => skip(1);
  Iterable<E> get init => take(length - 1);
}
