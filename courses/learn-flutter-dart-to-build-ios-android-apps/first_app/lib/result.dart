import 'package:flutter/material.dart';

class Result extends StatelessWidget {
  final int score;
  final VoidCallback resetCallback;

  Result({@required this.score, @required this.resetCallback});

  String get resultPhrase {
    if (score < 50) {
      return 'You will do better next time!';
    } else if (score < 75) {
      return 'Not bad!';
    } else if (score < 100) {
      return 'You did great!';
    } else {
      return 'Congratulations!!';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          Text(
            'Final score: $score',
            style: TextStyle(
              fontSize: 36,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(
            resultPhrase,
            style: TextStyle(
              fontSize: 20,
            ),
          ),
          FlatButton(
            child: Text('Restart quiz'),
            onPressed: resetCallback,
            textColor: Colors.blue,
          )
        ],
        mainAxisAlignment: MainAxisAlignment.center,
      ),
    );
  }
}
