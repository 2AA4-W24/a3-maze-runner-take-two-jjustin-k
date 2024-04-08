
# Assignment A3 - Maze Runner, Take Two

  * **Student**: [Justin Kwinecki](kwineckj@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*

## Introduction

This program was built on top of my first assignment, which was to solve any maze that follows certain conditions.
In this assignment, I used all my knowledge gained from this course to improve my original code, as well as use a 
graphing algorithm to find the shortest path to solve the maze.

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
    - For this assignment, the path does not have to be the shortest one.
- The program can take a path as input and verify if it's a legit one.

### Delivered version

#### Command line arguments

My program reacts to these four flags

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {graph, righthand}`: specifies which path computation method to use. (default is graph)
- `-baseline {graph, righthand}`: specifies which method is the baseline for comparison. 

#### Flag Priority

The -i flag must be used for every run.  The rest of the flags are prioritized in this order given below:
- If a path is given, the program will print "correct" or "incorrect" on the standard output, depending on its validity.
- If the baseline flag is used, the method flag must also be used to have two solutions to compare.  The program will print the speed to load the maze, the speed to solve the maze using the "method", the speed to solve the maze using "baseline" and the SpeedUp.
- If the method flag is used, the program will print the solution of the maze using given method (righthand, graph).
- If no flags are provided, the program will print the solution of the maze using the graph algorithm.
