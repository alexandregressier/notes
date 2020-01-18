//import ''; // The import syntax
import 'package:flutter/material.dart';

main() { // The entry point
  // This file should not be renamed
  runApp(MyApp()); // Pass an instance of Widget
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) { // Needs to return a `Widget`
    return MaterialApp(home: Text('Hello!'),);
  }
}
