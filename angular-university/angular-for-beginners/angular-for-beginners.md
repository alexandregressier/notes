# Angular for Beginners, Angular University - Notes

## Helicopter View

https://angular.io/

Goal: learn the most commonly used features

> In Angular you will be using 10% of its features maybe 90% of the time!

Topics:
- Custom components with `@Component`
- Components `@Input` and `@Output`, event Emitters
- `ngFor`
ngIf`
ngClass`
ngStyle`
ngSwitch`
Built-In Pipes
Async Pipe
Custom Pipes
`@Injectable` and Custom Services
HTTP Client - `GET` `POST` `PUT` `DELETE`

This course is a subset of the _Angular Core Deep Dive_ course

Angular University is always updated the latest Angular version


## Development Environment Setup

Installation requirements:
- Node.js (not LTS, since it works better w/ tools _e.g._, Angular CLI)
- IDE: this course is using WebStorm

(Avoid using Node.js from GraalVM: LTS + apparently present only for JVM interop)

Installing Node.js on Ubuntu:
```bash
$ curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
```

This will configure the Node source APT repositories by:
- Adding the GPG keys
- Creating the `/etc/apt/sources.list.d/nodesource.list` file

```bash
$ sudo apt install nodejs
```
This will also install `npm`

Verify:
```bash
$ node --version
$ npm --version
```
(You can use `which` to be sure of which executable is used & `which -a` to see the available ones)

Installing Angular CLI:

https://cli.angular.io/

Listing installed packages:

Local:
```bash
$ npm ls --depth 0
/home/alex/prj/notes/angular-university/angular-for-beginners
└── (empty)
```

Global:
```bash
$ npm ls -g --depth 0                        
/usr/lib
└── npm@6.14.4
```
Note: w/ GraalVM, npm is not listed here (_i.e._, `(empty)` is displayed)


Installing Angular:
```bash
$ sudo npm install -g @angular/cli
```

Verify:
```bash
$ npm ls -g --depth 0                        
/usr/lib
├── @angular/cli@9.1.1
└── npm@6.14.4
```

```bash
$ ng --version
```

List `ng` commands:
```bash
$ ng
...
  new (n) Creates a new workspace and an initial Angular app.
...
  serve (s) Builds and serves your app, rebuilding on file changes.

```

Creating a new _Angular application_:
```bash
$ ng n <app name>
```
(Do not use Angular Routing yet & use _plain_ CSS)
Note: if the cwd is already managed by `git`, its initialization will be skipped (there will be .gitignore anyway )

- Creates a set of files
- **IMPORTANT:** installs several npm dependencies via `npm i` (in `node modules`):
```bash
$ npm ls --depth 0
```
`node_modules` is called the _library root_

(`⠹` rocks!)

Build & serve the app via _hot reloading_:
```bash
$ cd <app name>
$ npm start
# OR
$ ng s
```

In the `package.json` file, there are the following `npm` _scripts_:
- `ng`
- `start`
- `build`
- `test`
- `lint`
- `e2e`
-> Thus `npm start` runs the `start` script (which is equivalent to `ng serve`

https://docs.npmjs.com/misc/scripts

`npm start` is the command used by IntelliJ in the `Angular CLI server` command

`Angular Application` is used to open or debug your **running** application

As you can notice, Angular is not only a web framework: there are also many tools _e.g._, the CLI


## Custom HTML Elements, Model vs View

The default page corresponds to the `src/index.html` file

Angular uses the _kebab-case_ convention, and so should your Angular app names
-> The name will be converted to other cases on several occasions, _e.g._, in `<title>` of `index.html`

https://en.wikipedia.org/wiki/Naming_convention_(programming)#Multiple-word_identifiers

The _core_ module of Angular enables us to create custom HTML tags
-> Its one of its main features

`<app-root>` is a custom HTML tag that will be:
- Expanded w/ the template of a _component_
- **IMPORTANT:** preserved in the production code
-> Extends HTML w/ custom functionalities

A component is defined by a TypeScript file & goes along w/ other files:
- A template (HTML) -> `templateUrl`
- A CSS style sheet (CSS) -> `styleUrls`
+ A test/spec file (TS)

`app.module.ts` is not directly related to the `app` component
-> It is the place where you register your components

> Every Angular app has at least one NgModule class, the root module, which is conventionally named AppModule and resides in a file named `app.module.ts`. You launch your app by bootstrapping the root `NgModule`.

https://angular.io/guide/architecture-modules

The testing library used by Angular is _Jasmine_
https://jasmine.github.io/
-> To not be confused w/ end-to-end tests, for which Angular uses Protractor

> Karma is a JavaScript test runner which means it will read code in a certain format (in our case written in Jasmine) and run it as a suite of tests.

https://blog.jetbrains.com/webstorm/2017/02/your-first-unit-test-using-angular-cli-karma-and-webstorm/

**INTELLIJ TIP:** The Karma plugin is not installed by default (on the contrary to WebStorm)

(The default template even embed its CSS to make its deletion easier)

A component:
- Have its files gathered in a directory
- Is defined via a TS class decorated w/ the `@Component` _decorator_
- Contains TS logic & state
- Can be placed in other HTML files (_e.g._, other templates, `index.html`) via its _selector_
- Is a kind of _construct_
- Is _linked_ to a template & a style sheet
- Has its own name scope

-> As such, Angular enables us to clearly separate the _data_ (_i.e._, the _model_) of our app from the templates (_i.e._, the _view_)

> You may be familiar with the component/template duality from your experience with model-view-controller (MVC) or model-view-viewmodel (MVVM). In Angular, the component plays the part of the controller/viewmodel, and the template represents the view.

```typescript
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-course';
}
```

**HTML TIP:** properly open a page in a new tab:
```html
<a target="_blank" rel="noopener" href="http://example.com">Link</a>
```
https://github.com/sferik/rails_admin/issues/2960

> Interpolation refers to embedding expressions into marked up text. By default, interpolation uses as its delimiter the double curly braces, `{{` and `}}`.

https://angular.io/guide/template-syntax#interpolation-
(+ spaces after & before `{{` & `}}`)

Interpolation:
- Are evaluated in the context of the linking component class
- Is done at the component level
- Can be used for any TS/JS object (not only `string`)
- Is part of the _template syntax_
- Are _bound_ to a value that may change

An member variable in TS is called a _property_

Interpolation of the previous `title` property:
```angular2html
<span>{{ title }} app is running!</span>
```

_Expressions_ are _evaluated_ (_i.e._, they result in a _value_)

IMPORTANT: do not change your app while `ng serve` is launching, b/c changes done during this time w/ not be catch by triggers

Multiline strings in JS are achieved using _template literals_ (delimited by backticks)

Using another type for the bound property:
```ts
@Component({
  selector: 'app-root',
  template: `
    <span>{{ data.title }} app is running!</span>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  data = {
    title: 'angular-course'
  };
}
```
