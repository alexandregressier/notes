import 'package:flutter/material.dart';

import './question.dart';
import './answer.dart';

class Quiz extends StatelessWidget {
  final String question;
  final List<Map<String, Object>> answers;
  final Function callback; // Expected: (int) => void

  Quiz(
      {@required this.question,
      @required this.answers,
      @required this.callback});

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      Question(text: question),
      for (var answer in answers)
        Answer(
          text: answer['answer'] as String,
          callback: () => callback(answer['points'] as int),
        ),
    ]);
  }
}
