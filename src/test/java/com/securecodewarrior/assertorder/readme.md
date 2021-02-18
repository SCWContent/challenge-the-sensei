## Issue: Asserting equality on the same value

In this line of code, we see an assertion that 2 values are the same:
``` 
assertThat(actual.length).isEqualTo(actual.length);
```
But because of a mistake, it's actually comparing the a value to itself, when the intention clearly would be to compare it to an `expected` value. 

The above assertion would never actually be one that would be intentional. So having those types of things in your codebase means that your unit tests could be lying to you. You'd want to detect and fix those issues as you're writing the tests. 

## Detection with Sensei

We can utilize Sensei to find instances where an assertion is being made against itself. 

```
search:
  methodcall:
    followedBy:
      methodcall:
        args:
          1:
            value: "{{{ arguments.0 }}}"
        name:
          matches: "is.*"
    name: "assertThat"
    type: "org.assertj.core.api.Assertions"
```            

This recipe searches for any `assertThat` call which is followed by a method call to a method that starts with `is`, and has the same argument as the `assertThat` call. This is achieved by utilizing the templating engine in Sensei, which allows you to compare things across the Abstract Syntax Tree of the code. This can be extremely powerful and empowers you to express rather complex code patterns in a simple manner.

**Try this for yourself:**

- **in the `AssertOrderTestIncorrect` code. We created a recipe which will detect the incorrect assertion. **
