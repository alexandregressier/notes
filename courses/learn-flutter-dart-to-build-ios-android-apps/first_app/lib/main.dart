import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var questionIndex = 0;

  void answerQuestion() {
    setState(() {
      ++questionIndex;
      print(questionIndex);
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
            Text(questions[questionIndex]),
            RaisedButton(
              child: Text('Answer A'),
              onPressed: answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer B'),
              onPressed: answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer C'),
              onPressed: answerQuestion,
            ),
            RaisedButton(
              child: Text('Answer D'),
              onPressed: answerQuestion,
            ),
          ],
        ),
      ),
    );
  }
}
