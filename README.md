# Auto-complete
This is an implementation of an autocomplete feature using a Trie data structure.

# System requirements
The following should be installed:
- Java SDK 1.8 or later
- sbt any version should be fine normally

## Build and run
To compile the code:

```
> sbt clean compile
```

To run the unit tests:

```
> sbt clean test
```

To run the application reading from the default file (in resources folder):

```
> sbt run
```

To specify your input file:

```
> sbt "run <path_to_file>"
```

# Additional questions

- **What would you change if the list of keywords was much larger (several millions) ?**
One way to avoid wasting memory is to implement a Ternary Trie, which takes less space since each node in the tree
can have only 3 children, or use a compressed Trie. Another improvement could be to store the complete data structure
in an external system, which can ease retrieval(such as a database) and store the top N searched words in a cache
to speedup the search(but in this case we have to give it away the sorted words feature).

- **What would you change if the requirements were to match any portion of the keywords
(for example, given the string "pro”, the program could suggest the keyword “reprobe”)?**
Probably the best way to handle this would be to use a suffix Trie which can let you search also patterns in a word.
