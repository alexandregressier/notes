import 'dart:core';

T identity<T>(T x) => x;

extension FunctionalIterable<E> on Iterable<E> {
  E get head => first;
  Iterable<E> get tail => skip(1);
  Iterable<E> get init => take(length - 1);
}

extension MapImproving1<K extends Comparable<K>, V extends Comparable<V>> on Map<K, V> {
  // Map<K, V> sortByKey() {
  //   keys
  //       .toList(growable: false)
  //       ..sort((k1, k2) => this[k1].compareTo(this[k2]))
  // }

  // Map<K, V> sortByValue() {

  // }
}

// extension BestCom<T extends Comparable<> on List<T> {
//   T best(T x) => x;
// }
