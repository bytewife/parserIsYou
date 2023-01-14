# Parser Is You
A (very) basic parser inspired by the grammar of the game Baba Is You!  
Given that the parser is ~100 lines, I hope that you may find this a useful learning reference!

# Grammar
```
Start = Noun T_Verb (Property | Noun)
Noun = (T_Not | epsilon) T_Noun
Property = (T_Not | epsilon) T_Property
```

# Installation
This project requires the following:
- Scala 3+
- sbt  

To run it, clone this repo, then use:
```shell
cd parserToYou
sbt run
# Then, wait for `Enter a valid Baba Is You sentence!`
# and then input a sentence, such as `baba is you`.
```

# Attribution
Thank you `BrianPopeck/baba-parser`, which I based this parser off. That one is also more feature-complete.

# License
This projects uses the MIT license.
