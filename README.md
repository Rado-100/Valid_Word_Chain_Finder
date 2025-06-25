# Valid Word Chain Finder

## Overview

**Word Reducer Finder** is a Java application that identifies "reducible" words from a given dictionary. 
A reducible word is one that can be reduced to a valid one-letter word (either "A" or "I") 
by removing one letter at a time, such that all intermediate forms are valid dictionary words.

For example, the word `STARTLING` is reducible as follows:

```
STARTLING → 
STARTING → 
STARING → 
STRING → 
STING → 
SING → 
SIN → 
IN → 
I
```

## Features

- Loads a list of English words from a remote URL.
- Recursively validates each word for reducibility.
- Filters for words of a specific length (e.g., 9-letter words).

## How It Works

1. **Loads Words**: Fetches the word list from a remote source using `WordsLoader`.
2**Validation**: Uses the `WordFinder` class to check if each word can be reduced down to a 
   single letter with each intermediate step being a valid word.

## Getting Started

### Requirements

- Java 17 or higher
- Internet connection (to fetch the word list)

### Running the Program

1. Clone the repository.
2. Compile and run the Main class:

```bash
javac Main.java WordFinder.java WordsLoader.java
java Main
```

### Sample Output
 By testing with word length of 9, the application returns 775 words retrieved by 
 https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt
```java
Set<String> allWords = WordsLoader.loadAll(
        "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
    
Set<String> validWords = WordFinder.find(allWords, 9);
```

20 of which are the following:
```
GUARISHES
ASPIRATED
SHARNIEST
STAUNCHES
TOOTHINGS
BEARDIEST
SHINGLERS
TAILFLIES
PRANKINGS
ACIERATES
ANASTASES
CLASSISMS
LEGERINGS
SCOURGING
CHAMISALS
ACIERATED
FLEECHING
SWERVINGS
GANGSTERS
BALLSIEST
```
