import 'package:flutter/material.dart';

import './question.dart';
import './answer.dart';

class Quiz extends StatelessWidget {
  final String question;
  final List<String> answers;
  final VoidCallback callback;

  Quiz(
      {@required this.question,
      @required this.answers,
      @required this.callback});

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Question(question),
      for (var answer in answers) Answer(answer, callback),
    ]);
  }
}
