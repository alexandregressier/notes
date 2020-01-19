import 'package:flutter/material.dart';

import './quiz.dart';
import './result.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const _questions = const [
    {
      'question': 'What is your favorite color?',
      'answers': [
        {'answer': 'Red', 'points': 30},
        {'answer': 'Yellow', 'points': 20},
        {'answer': 'Green', 'points': 10},
        {'answer': 'Blue', 'points': 25},
      ],
    },
    {
      'question': 'What is your favorite animal?',
      'answers': [
        {'answer': 'Dog', 'points': 35},
        {'answer': 'Cat', 'points': 5},
        {'answer': 'Bird', 'points': 25},
        {'answer': 'Fish', 'points': 15},
      ],
    },
    {
      'question': 'Who is your favorite superhero?',
      'answers': [
        {'answer': 'Superman', 'points': 10},
        {'answer': 'Batman', 'points': 25},
        {'answer': 'Spider-man', 'points': 35},
        {'answer': 'Iron man', 'points': 20},
      ],
    },
  ];
  var _questionIndex = 0;
  var _score = 0;

  void _answerQuestion(final int points) {
    setState(() {
      ++_questionIndex;
    });
    _score += points;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('My first app'),
          ),
          body: _questionIndex < _questions.length
              ? Quiz(
                  question: _questions[_questionIndex]['question'],
                  answers: _questions[_questionIndex]['answers'],
                  callback: _answerQuestion,
                )
              : Result(score: _score)),
    );
  }
}
