import 'package:flutter/material.dart';

class Result extends StatelessWidget {
  final int score;

  Result({@required this.score});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text('Final score: $score'),
    );
  }
}
