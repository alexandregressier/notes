import 'dart:collection';

import 'package:quiver/iterables.dart';

import 'gressier.dart';

bool isUpperCase(final String string) =>
    string == string.toUpperCase();

bool isUpperCaseStupid(final String string) =>
    string.split('').every((c) => c == c.toUpperCase());

String removeSpaces(final String string) =>
    string.replaceAll(' ', '');

String removeSpacesStupid(final String string) =>
    string.split('').where((c) => c != ' ').join();

int minIntAlt(List<int> digits) =>
    int.parse(SplayTreeSet.from(digits).join());

int minInt(List<int> digits) =>
    int.parse((digits..sort()).toSet().join());

String createPhoneNumber(List<int> ints) {
  final parts = partition(ints, 3).toList();
  return '(${parts.head.join()}) ${parts.tail.head.join()}-${concat(parts.tail.tail).join()}';
}
