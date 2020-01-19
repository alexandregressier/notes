import 'package:first_app/result.dart';
import 'package:flutter/material.dart';

import './quiz.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const _questions = const [
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
          body: _questionIndex < _questions.length
              ? Quiz(
                  question: _questions[_questionIndex]['question'],
                  answers: _questions[_questionIndex]['answers'],
                  callback: _answerQuestion,
                )
              : Result()),
    );
  }
}
