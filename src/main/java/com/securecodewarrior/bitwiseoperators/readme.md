## Bitwise Operators

A fairly simple Java Gotcha to accidentally fall into is: using a Bitwise operator instead of a Boolean Comparison operator.

e.g. a simple mistype can result in writing "&" when you really meant to write "&&".

A common heuristic we learn when reviewing code is:

> "&" or "|" when used in a conditional statement is probably not intended.

Using Bitwise operators with Booleans is perfectly valid, so Java will not report a syntax error.

If I construct a JUnit Test to explore a truth table for both Bitwise OR (`|`) and Bitwise AND (`&`) then we will see that the outputs from the Bitwise operator match the truth table. Given this, we might think that the use of Bitwise operators is not an issue.

The following Tests use Bitwise operators, and they work just fine.

```
    @Test
    void bitwiseOperatorsAndTruthTable(){
        Assertions.assertEquals(true, true & true);
        Assertions.assertEquals(false, true & false);
        Assertions.assertEquals(false, false & true);
        Assertions.assertEquals(false, false & false);
    }
```

```
    @Test
    void bitwiseOperatorsOrTruthTable(){
        Assertions.assertEquals(true, true | true);
        Assertions.assertEquals(true, true | false);
        Assertions.assertEquals(true, false | true);
        Assertions.assertEquals(false, false | false);
    }
```

## Issue: Short Circuit Operation

The real issue is the difference in behaviour between Bitwise (`&`, `|`) and Boolean (`&&`, `||`) operators.

A Boolean operator is a short circuit operator and only evaluates as much as it needs to.

e.g.

```
if (args != null & args.length() > 23) {
    System.out.println(args);
}
```

In the above code, both boolean conditions will evaluate, because the Bitwise operator has been used:

- `args != null`
- `args.length() > 23`

This leaves my code open to a `NullPointerException` if `args` is `null` because we will always perform the check for `args.length`, even when `args` is `null` because both boolean conditions have to be evaluated.

## Boolean Operators Short Circuit Evaluation

When an `&&` is used e.g.

```
if (args != null && args.length() > 23) {
    System.out.println(args);
}
```

As soon as we know that `args != null` evaluates to `false` the condition expression evaluation stops.

We don't need to evaluate the right-hand side.

Whatever the outcome of the right-hand side condition, the final value of the Boolean expression will be false.

## Detection with Sensei

Because Bitwise operators are perfectly valid, and often used in assignments we focussed on the use-case of `if` statements, and the use of Bitwise `&`, to find the problematic code.

```
search:
  expression:
    anyOf:
    - in:
        condition: {}
    value:
      caseSensitive: false
      matches: ".* & .*"
```

This uses a regular expression to match `" & "` when it is used as a condition expression. e.g. in an `if` statement.

To fix it, we again relied on regular expressions. This time using the `sed` function in the QuickFix to globally replace the `&` in the expression with `&&`.

```
availableFixes:
  - name: "Replace bitwise AND operator to logical AND operator"
    actions:
      - rewrite:
          to: "{{#sed}}s/&/&&/g,{{{ . }}}{{/sed}}"
```

**Try this for yourself:**

- **in the `BitwiseOperatorsIncorrect` code. We created a recipe to highlight the Bitwise `&` Operator in the code and amend it to use a Conditional `&&` Operator.**
- **use the recipe we created as a starting point, and amend it to convert a `|` operator into a `||` operator.**

We cover this in more detail, with some production examples we found [in this blog post](https://www.securecodewarrior.com/blog/java-gotchas-bitwise-vs-boolean-operators)