# The Modern GraphQL Bootcamp (with Node.js and Apollo), Udemy - Notes

Why GraphQL?

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

What is a Graph?
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
