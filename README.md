# Battleship Game

This is a simple Battleship game I made for my Java programming class. You play against the computer on a 10x10 grid.

## What it does

- You and the computer each place 5 ships on a grid
- Take turns guessing coordinates to try to sink each other's ships
- First one to sink all the opponent's ships wins
- The game shows hits, misses, and your ships on the map

## How to run it

1. Make sure you have Java installed
2. Download the BattleShip.java file
3. Open terminal/command prompt
4. Compile: `javac BattleShip.java`
5. Run: `java BattleShip`

## How to play

1. When the game starts, place your 5 ships by entering X and Y coordinates (0-9)
2. The computer will randomly place its ships
3. Take turns entering coordinates to attack
4. The map shows:
    - `@` = your ships
    - `!` = successful hits
    - `X` = destroyed ships
    - `-` = missed shots

## Game features

- Input validation (won't let you enter invalid coordinates)
- Color coded output (green for player, red for computer)
- You can play multiple rounds
- Shows remaining ship counts

## What I learned

This project helped me practice:
- Working with 2D arrays
- Exception handling for user input
- Game loop logic
- Using Scanner for input
- Console output formatting

## Notes

- Ships are only 1x1 size (not the traditional multi-cell ships)
- You can accidentally hit your own ships if you're not careful
- Computer picks random coordinates each turn

This was a fun project to work on and helped me understand Java basics better.