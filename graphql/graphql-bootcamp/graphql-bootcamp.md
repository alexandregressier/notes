# The Modern GraphQL Bootcamp (with Node.js and Apollo), Udemy - Notes

## 1. Course Overview

### Why GraphQL?

Where GraphQL is fitting in an application?

Application stack:

Clients:
- Web application
- Mobile application

Back end:
- Database
- Web servers

The glue between clients and servers has always been HTTP
-> A RESTful API

We replace the RESTful API that has many endpoints that has just a single endpoint exposed

GraphQL = Graph Query Language
- Operates over HTTP as well
- GraphQL queries are just HTTP requests

**IMPORTANT:** GraphQL operates over a single endpoint

Advantages of GraphQL:
1. Fast

With HTTP:

When displaying a blog post:
- Getting the post content: GET /posts/123
- Other posts to display at the bottom: GET /posts?author=4321
- Comments: GET /posts/123/comments

With GraphQL:
POST /graphql (w/ a GraphQL query)
-> A single query: GraphQL is faster

FUNDAMENTAL: INSTEAD OF THE SERVER DETERMINING WHAT DATA GETS SENT BACK IT IS UP TO THE CLIENT TO REQUEST ALL OF THE DATA IT NEEDS

2. Flexible

The biggest advantage according to the author

In the RESTful API example, you can cram all the endpoints into 1
It is getting big and slow though: it is making more databases requests

On mobile devices, you have a whole other set of considerations:
- Less screen real estate
- Battery life
- Slow & expensive data
-> The user ends up uninstalling the application

This is the original reason why GraphQL was created: at Facebook, they did not always need the same data for both desktop & mobile clients

GRAPHQL ENABLES YOU TO FETCH DATA LATER WHEN NECESSARY

In practice, Mobile & Desktop clients have different GraphQL queries
-> The server does less work for mobile

W/ a RESTful API, you would end up using a lot of query params trying to reproduce this

Facebook & GitHub are using GraphQL in production

3. Easy to use and simple to maintain

With a RESTful API, if a client needs different data, you are generally required to implement another endpoint

Summary:
- GraphQL creates fast & flexibles APIs, giving clients complete control to ask for **just** the data they need
- Fewer HTTP requests. Flexible data querying. Less code to manage (WHICH IS THE BEST PART DUDE)

VSC generally means Visual Studio Code


## 2. GraphQL Basics: Schemas and Queries

### What is a Graph?

- Not a chart
- Model relationship between data (just like PK  & FK do)
-> A way to think about the application data

Example given: a blogging application
- Users are going to sign up & log in (& log out)
- Once they are logged in, they are going to be able to create & publish posts
- Once published, other users are going to be able to read that post & add comments on to it

Entities:
- User
- Post
- Comments
-> These are known as _types_ (what a delightful name)

Each type has _fields_ associated w/ it (aka properties)

It is up to you to define the _assocations_ (aka relationships) you need

Example of fields:
- **User**
  - id: String
  - name: String
  - age: Int
  - posts: **Post**
  - comments: **Comment**
- **Post**
  - id: String
  - title: String
  - body: String
  - pusblished: Bool
  - author: **User**
  - comments: **Comment**
- **Comment**
  - id: String
  - text: String
  - post: **Post**
  - author: **User**

This data would be modeled the same way in a SQL database (-> 1 type = 1 table)

-> Everything is doubly linked in this example

_Discrete fields_


### GraphQL Queries

https://graphql-demo.mead.io/
Tool used: GraphQL Playground
-> Can be installed in our machines

HTTP GUIs: Postman, Insomnia
-> GraphQL Playground is the same kind of tool

`C-RET` to run the query

REMEMBER: you do not have to change the URL b/c you generally have to deal w/ a single endpoint using GraphQL

**IMPORTANT:** Unlike JSON but like XML, you can have comments using `#`

3 major operations that can be performed on any GraphQL API:
- `query` (all lowercased): fetch data
- `mutation`: change data
- `subscription`: watch data for changes (great for real-time applications)

fetch = "aller chercher"

```graphql
query { # The `query` keyword is optional
 hello
}
```

We are querying a single field: `hello`

The response is in JSON

GraphQL seems to be like SQL on JSON HTTP responses

With GraphQL, we can request as much or as little data as we need

IMPORTANT: is there some sort of Swagger?
-> No, one of the major features of GraphQL is the self-documenting schema

By nature, all GraphQL APIs are self-documenting

With REST APIs, someone has to maintain documentation manually

By experience, the RESTful API documentation:
1. Does not exist
2. Is severely outdated
-> What you really do: request everything and find out where fields are located
-> Not this problem in GraphQL BECAUSE YOUR REQUEST DICTATES YOUR RESPONSE
-> You query 3 fields in a specific order, you get back the 3 fields w/ their values in the same order

Querying multiple fields:
```graphql
{
  hello
  courseInstructor
}
```

-> No `,`, `.`, `""` or the like in GraphQL

Obviously, you cannot request fields that are not defined in the schema

Error messages can be responded

GraphQL Playground is providing useful feedback on errors

`Query` is a type we will define later on

GraphQL exposes an _application schema_, which describes all the operations that can be performed
-> In the case of a query operation, it describes all the fields you are allowed to select

GraphQL Playground has a "SCHEMA" tab on the RHS

`String!` means non-null `String`

IMPORTANT: unlike JSON, you can reorder fields however you want


### Nested GraphQL Queries

In real-world applications, you do not only have **scalar** types but also **compound** types like:
- Arrays/List
- Objects (-> JS objects)

_Scalar type_ is a term used w/in the GraphQL ecosystem

`me` is of type `User!` in the GraphQL API provided
-> "An object w/ a standard (-> defined) set of fields"

```graphql
{
  me
}
```

**CRITICAL:** when querying a GraphQL object, you must specify which fields you want
-> You cannot query everything one-shot b/c that defeats the purpose of GraphQL (-> the client dictates the response)

```graphql
{
  hello
  course
  me {
    id name email
  }
}
```

**NOTE:** GraphQL is cool b/c since your client can have simple JS objects that forms the GraphQL query and fetch only what it needs

REMEMBER: in GraphQL, objects are called types
-> You can also query arrays of objects

Like Haskell, types are capitalized

Types:
- Are noted in suffix annotations
- `Type!` denotes a not null `Type`
- `[Type!]` denotes an array of not null `Type`
- `[Type!]!` denotes a not null array of not null `Type` (The array can be empty though)

`User!` is opposed to `User?` from other languages like Swift

`[Type!]!` is a very standard type definition for an array of objects

You have tabs in the GraphQL Playground tool

**IMPORTANT**: in GraphQL, arrays are queried in the exact same way than objects:
```graphql
{
  users {
    name email
  }
}
```
-> The `[]` notation is only used in the response

GraphQL queries can be minified just like JSON:
```graphql
{users{name email}}
# Or
{users{name,email}}
```
-> `,` works as well to separate fields

> We are able to select as many or as few things as we need for each user in that array

You can also filter & sort w/ GraphQL!
-> Can you reduce to have lengths directly?

```graphql
{
  posts {
    title
    comments {
      author {
        name
      }
    }
  }
}
```
Theoretically, you double recurse indefinitely two types that have a two-way relationship

QUESTION: is there any difference when not using the `query` keyword?

UUID IDs are really cool & can serve the same purpose as _serial_ ids

boilerplate = _passe-partout_


### Setting up Babel

https://babeljs.io/
-> Page "Try it out"

> Babel is a toolchain that is mainly used to convert ECMAScript 2015+ code into a backwards compatible version of JavaScript in current and older browsers or environment
```js
// Babel Input: ES2015 arrow function
[1, 2, 3].map((n) => n + 1);

// Babel Output: ES5 equivalent
[1, 2, 3].map(function(n) {
  return n + 1;
});
```
IN A NUTSHELL: Babel enables you to use cutting edge JS features w/o worrying about backward compatibility
- Babel is JS transpiler (as stated in the documentation, as opposed to compiler)
- Useful for IE10 compatibility
- We are going to use the ES6 import/export syntax
- Typically, React developers are familiar w/ Babel but Node.js developers are not (JS back end only)
- Once Babel is set up, you can forget it

> You can use strict mode in all your programs. It helps you to write cleaner code, like preventing you from using undeclared variables. `"use strict"` is just a string, so IE 9 will not throw an error even if it does not understand it.

https://cmder.net
-> A portable Linux console emulator for Windows

Generate a new npm project:
```sh
mkdir sandbox
cd $_
npm init # Generates a `package.json` file
```
Leave default value for every prompted field (except for version if you want):
```json
{
  "name": "graphql-basics",
  "version": "0.1.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC"
}
```
- `git` corresponds to the URL of the remote repository

> The ISC license is a permissive free software license published by the Internet Software Consortium, nowadays called Internet Systems Consortium. It is functionally equivalent to the simplified BSD and MIT licenses

Installing the npm modules we need (local to the project):
```sh
npm i babel-cli babel-preset-env
```
- `babel-cli`: enable us to run a command to transpile w/ Babel
- `babel-preset-env`: process `import`/`export`, this tells Babel exactly what it should change

> A polyfill is a piece of code (usually JavaScript on the Web) used to provide modern functionality on older browsers that do not natively support it

You then have to tell Babel to use that `env` preset in a `.babelrc`:
```json
{
    "presets": [
        "env",
    ]
}
```

Create `src/index.js` containing `console.log("Hello, world!")`

`npm start` is the standard npm script used to run an npm project
-> Also used in Angular

Define the start npm script:
```json
{
  "scripts": {
    "start": "babel-node src/index.js",
  },
}
``` 
- `babel-node`:
  - Works exactly as `node`
  - Takes the files passed as arguments & passes it through the Babel transpiler using the configuration in `.babelrc` (no output file is produced though)
  - Should be used for local development purposes only

```sh
npm start
# Or
npm run start
```
-> `Hello, world!` should be printed out

**IMPORTANT**: use `npm run` to list the runnable npm scripts:
```sh
$ npm run
Lifecycle scripts included in graphql-basics:
  start
    babel-node src/index.js
  test
    echo "Error: no test specified" && exit 1

$ npm start
```
