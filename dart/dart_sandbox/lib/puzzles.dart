import 'dart:collection';

import 'dart:math';

num average(List<num> xs) =>
  xs.fold(0, (acc, x) => acc + x) / max(1, xs.length);

num averageBy<T>(num f(T), List<T> xs) =>
  xs.map(f).fold(0, (acc, x) => acc + x) / max(1, xs.length);
