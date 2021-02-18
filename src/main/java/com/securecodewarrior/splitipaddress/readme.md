## String split operator

While seemingly innocent, this line of code has quite a different result compared to what many would expect:
```
    String[] octets = "192.168.1.1".split(".");
```

Rather than returning an Array of octets, it returns an empty array. Why? 

## Issue: Split operator takes regex as argument
If you pay attention to the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html), you'll see that the signature for `split` looks like this:
``` 
String[] split(String regex)
    Splits this string around matches of the given regular expression.
```

Rather than taking "just" a string, the string is interpreted as a regex. Looking to the [documentation about the meaning of a dot in regex](https://www.regular-expressions.info/dot.html), we see that it means:
> The dot matches a single character, without caring what that character is. The only exception are line break characters.

This means that it in effect matches any character in the above scenario. Splitting along all characters means that we get no output at all. As such, the above code example is never correct.

## Detection with Sensei

Sensei makes it easy to search for this code pattern. The below recipe searches for any call to `split`, `replaceAll`, or `replaceFirst` where the argument is just a dot. 

```
search:
  methodcall:
    args:
      1:
        value: .
    name:
      matches: (split|replaceAll|replaceFirst)
    type: java.lang.String

```

We can also provide a quickfix for it, which simply escapes the dot such that it actually will match a dot in the string:

```
availableFixes:
- name: "Escape the regex special character for any char"
  actions:
  - modifyArguments:
      rewrite:
        1: "\"\\\\.\""

```

This quickfix simply modifies the first argument by replacing the first argument with the above string. 

**Try this for yourself:**

- **in the `SplitIPAddressIncorrect` code. We created a recipe to highlight split calls with an unescaped dot as the first argument. Use it to amend the code and escape the dot.**
