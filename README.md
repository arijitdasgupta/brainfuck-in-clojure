# Brainfuck in Clojure

To teach myself Clojure, I have started writing little command line toys in Clojure. This is the first one.

It's a interpreter for [brainfuck programming language](https://en.wikipedia.org/wiki/Brainfuck). It accepts code and inputs via `stdin`. Also supports nested loops.

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

This first line of `sample_code_n_input.txt` is code, following lines are `stdin` inputs. On first of run `bf_run.sh`, `sample_code_n_input.txt` is copied to `code_n_input.txt`; On that and subsequent runs, the interpreter runs takes `code_n_input.txt`.

**bf_run.sh requires leiningen**

## What's next?

Syntax pre-run checking in Brainfuck. More idiomatic clojure code.

## DIFFERENCE FROM ORIGINAL BF
 - For `[` current pointer value is checked against `<=0`
 - Nested-loops are supported. Which means, if there is a `[...[...]...]` like series of instructions, the inner closing bracket will make the instruction pointer to jump back to the inner opening bracket.
 - Has an `M` instruction for printing whole machine state for debugging.
