import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var _questionIndex = 0;

  void _answerQuestion() {
    setState(() {
      ++_questionIndex;
      print(_questionIndex);
    });
  }

  @override
  Widget build(BuildContext context) {
    var questions = [
      'What is your favorite color?',
      'What is your favorite animal?',
      'What is your favorite superhero?',
    ];
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('My first app'),
        ),
        body: Column(
          children: <Widget>[
            Text(questions[_questionIndex]),
            RaisedButton(
              child: Text('Answer A'),
              onPressed: _answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer B'),
              onPressed: _answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer C'),
              onPressed: _answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer D'),
              onPressed: _answerQuestion,
            ),
          ],
        ),
      ),
    );
  }
}
