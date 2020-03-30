# Dart in Action, Manning - Notes

<https://dart.dev/get-dart>

Dart SDK default location: `/usr/lib/dart`
Add its `bin/` directory to `PATH`

Project templates:
- *Console Application:* contains `bin/`, `lib/`, and `test`
- *Simple Console Application:* contains only `bin/`

IMPORTANT: by convention, Dart prefers snake case over Lisp case

Dart:
- Is a structured & scalable language
- Is open source
- Has a fast VM
- Transpiles to JS
- Has been designed to build larger modern web apps than JS (notably by bringing Java/C++/... features)

(A _rich client_ is a.k.a. as a _fat client_ (as opposed to _thin client_))

The author's blog <https://dartwatch.com> no longer exists

Dart has core language features as well as HTML5 features
Dart can be used for both client-side (_e.g._, web) & server-side apps

October 2011: Google releases Dart, a language aimed at developing complex, Google-scale web applications

Dart was internally called Dash while unreleased

[Leaked Google email titled "Future of JavaScript" that specifies the motivations behind Dart](https://gist.github.com/paulmillr/1208618)
-> Dart is intended to be what everything JS could be if it was reinvented

Goals:
> Maintain the dynamic nature of JS, but have a better performance profile and be amenable to tooling for large projects

[Google Web Toolkit (prounounced _gwit_)](http://www.gwtproject.org/)
GWT is a technology that Google created for cross-compiling Java to JS (and thus avoiding cheesy plugins like Flash or SilverLight)

Benefits from GWT seen again Dart:
- Structure (most notably due to OO features)
- Type-safety
- Tooling (_e.g._, autocompletion)
- Code reuse between client-side and server-side

[Dart language specification](https://dart.dev/guides/language/spec)
-> Was subject to a lot of changes as a result of real-word use by the language early adopters

/Dart Milestone 1/ was an important release of Dart

Google envisages Dart to be a _batteries included_ language

(There is "art" in "Dart")

_Dartisans_ is a community focused around Dart

Dart servers are capable of 2-way communication w/ browsers (via WebSockets)
Dart is ideal for developing large-scale apps in distributed teams

> Dart brings the structure of the server to the front end and the flexibility, dynamism and speed of browser development to the back end

Dart does not require a compile step

To support the developer experience, Google has created:
- (Obsolete) A Dart IDE (named _Dart Editor_) -> IntelliJ is now recommended
- (Obsolete) A custom Dart browser for quick developments (named _Dartium_)
- The Dart to JS converter (named `dart2js`)
- A server-side VM

Today: use [DartPad](https://dartpad.dev/)

> _Dartium_ is a special build of Chromium that includes the Dart VM

Like JS, Dart:
 - Supports first-class functions (Java & C# did not have such support at the time)
 - Has an event loop
 
Dart apps can be easily packaged as Chrome apps in Google's Web Store
 
Dart development requires at least the Dart SDK
 

# Chapter 1 - Hello Dart

Dart is a great language for developing web apps

_Single-Page web Applications_ (SPA)  are a good architecture for building apps in Dart
