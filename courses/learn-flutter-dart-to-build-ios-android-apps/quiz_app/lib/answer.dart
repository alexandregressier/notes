import 'package:flutter/material.dart';

class Answer extends StatelessWidget {
  final String text;
  final VoidCallback callback;

  Answer({
    @required this.text,
    @required this.callback});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: RaisedButton(
        child: Text(text),
        onPressed: callback,
        color: Colors.blue,
        textColor: Colors.white,
      ),
      width: double.infinity,
    );
  }
}
