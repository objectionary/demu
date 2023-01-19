<img alt="logo" src="https://www.objectionary.com/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/demu)](http://www.rultor.com/p/objectionary/demu)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/objectionary/demu/actions/workflows/mvn.yml/badge.svg?branch=master)](https://github.com/objectionary/demu/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=objectionary/demu)](http://www.0pdd.com/p?name=objectionary/demu)
[![codecov](https://codecov.io/gh/objectionary/demu/branch/master/graph/badge.svg)](https://codecov.io/gh/objectionary/demu)
[![Maven Central](https://img.shields.io/maven-central/v/org.eolang/demu.svg)](https://maven-badges.herokuapp.com/maven-central/org.eolang/demu)
[![Hits-of-Code](https://hitsofcode.com/github/objectionary/demu)](https://hitsofcode.com/view/github/objectionary/demu)
![Lines of code](https://img.shields.io/tokei/lines/github/objectionary/demu)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/objectionary/demu/blob/master/LICENSE.txt)

# DeMu

It's a well known fact that programs with immutable objects much easy to
maintain, test and use, but the main benefit form immutable objects is that they
allow performing
more [aggressive optimizations [1]](https://dl.acm.org/doi/10.1145/583810.583833)
and apply more valuable analysis. **DeMu** is exactly the tool for removing
mutable objects like `memory` and `cage`
from [EO](https://github.com/objectionary/eo) programs. Actually, DeMu is
acronym for **De-Muatabilization**.

## Methodology

There isn't single formal methodology or algorithm for converting mutable
objects into immutable, but we can use some separate methods to achieve this.

### Using SSA form for simple cases

We actually can
apply [SSA [2]](http://www1.cse.wustl.edu/~cytron/cs531/Resources/Papers/valnum.pdf)
form for `eo` programs. This approach can immediately remove some usages of
mutable objects. Let's consider the following example:

```eo
[] > example
  cage 0 > index
  seq > @
    index.write 1
    stdout
      sprintf
        "%s"
        index
    TRUE
```

After applying SSA form we get something like the next:

```eo
[] > example
  seq > @
    stdout
      sprintf
        "%s"
        1
    TRUE
```

And as you can see - we removed usage of `cage` from the result program.
The relation between SSA form and Dataflow Languages (like `EO` and, 
particularly, Functional Programming Languages) is quite well described in
that [article [3]](https://www.cs.princeton.edu/~appel/papers/ssafun.pdf).

### Using recursion for loops

The second method is to convert loops into recursive calls.

## Related articles and links

1. [Immutability specification and its applications](https://dl.acm.org/doi/10.1145/583810.583833)
2. [Global Value Numbers and Redundant Computations](http://www1.cse.wustl.edu/~cytron/cs531/Resources/Papers/valnum.pdf)
3. [SSA is Functional Programming](https://www.cs.princeton.edu/~appel/papers/ssafun.pdf)

## How to Contribute

Fork repository, make changes, send us a pull request. We will review your
changes and apply them to the `master` branch shortly, provided they don't
violate our quality standards. To avoid frustration, before sending us your pull
request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.