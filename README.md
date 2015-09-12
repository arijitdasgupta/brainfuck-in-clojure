# Brainfuck in Clojure

To teach myself Clojure, I have started writing little command line toys in Clojure. This is the first one.

It's a interpreter for [brainfuck programming language](https://en.wikipedia.org/wiki/Brainfuck). It accepts code and inputs via `stdin`

## Usage
This project uses [`leiningen`](http://leiningen.org/). Install `leiningen` and download this code from github.

```
git clone https://github.com/rivalslayer/brainfuck-in-clojure
```

###To run

Go to the project directory

```
lein run
```

###For devs

Tests are in `test` directory. To run tests,

```
lein test
```

###Sample code and input

Try executing bf_run.sh

_On most systems this would be_

While in project directory

```
./bf_run.sh
```

or

```
bash bf_run.sh
```

This first line of `sample_code_n_input.txt` is code. From second lines these are stdin stream

## What's next?

Syntax pre-run checking in Brainfuck. More idiomatic clojure code.

##KNOWN ISSUES
 - For `[` current pointer value is checked against <=0
