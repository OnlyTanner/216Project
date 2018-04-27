# SER216 Project
This game simulates the popular board game Monopoly. The game is mostly written in Java, and uses Lua scripts to control certain actions in the game. Clone the game and then the project can be opened as an IDEA project.

The game is mostly functional, but there are a few issues and features missing. Check the list of bugs and potential enhancements below to see what is working/in progress



## Bugs In Progress
* ~~The game cannot be won in its current state~~
* ~~Player's pieces and properties are not removed after they go bankrupt (in progress, mostly working)~~
* ~~Various mutator methods don't do appropriate checks before changing instance variables~~
* Players don't always roll while in jail
* No option to use a get out of jail card (always used by default)
* Multiple chance/community chest cards are broken
* The identification for each player in the GUI isn't accurate (_Player 2 can change to Player 1 if a player leaves. This bug was uncovered by fixing the bug that prevented bankrupted players from leaving the game_)
* Player's money amount isn't always updated right away in the GUI (sometimes it takes an extra turn)

## Bugs Fixed
* **Game can now be won**
* Player's pieces and their properties are removed when they go bankrupt
* All mutator methods in Player now handle inputs appropriately (all unit tests pass now)

## Enhancements
* Color code Players and Properties
* Allow Players to select their piece at the start of the game
* Fix/add the functionality to place houses and hotels on properties
* Add a button that displays all of your opponents and properties
