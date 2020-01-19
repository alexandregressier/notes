import 'package:flutter/material.dart';

import './question.dart';
import './answer.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const questions = const [
    {
      'question': 'What is your favorite color?',
      'answers': ['Red', 'Yellow', 'Green', 'Blue'],
    },
    {
      'question': 'What is your favorite animal?',
      'answers': ['Dog', 'Cat', 'Bird', 'Fish'],
    },
    {
      'question': 'Who is your favorite superhero?',
      'answers': ['Superman', 'Batman', 'Spider-man', 'Iron man'],
    },
  ];
  var _questionIndex = 0;

  void _answerQuestion() {
    setState(() {
      ++_questionIndex;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('My first app'),
          ),
          body: _questionIndex < questions.length
              ? Column(children: [
                  Question(questions[_questionIndex]['question']),
                  for (var answer in questions[_questionIndex]['answers'])
                    Answer(answer, _answerQuestion),
                ])
              : Center(
                  child: Text('You did it!'),
                )),
    );
  }
}
