# SER216 Project
This game simulates the popular board game Monopoly. The game is mostly written in Java, and uses Lua scripts to control certain actions in the game. Clone the game and then the project can be opened as an IDEA project.

The game is mostly functional, but there are a few issues and features missing. Check the list of bugs and potential enhancements below to see what is working/in progress



## Enhancements
* Color code Players and Properties
* Allow Players to select their piece at the start of the game
* Fix/add the functionality to place houses and hotels on properties
* ✅ Add a button that displays all of your opponents and properties -DONE
  * To use this feature, click the blue player indicator box in the top left of the game window. A new window should open with all of the players in the game and info about their money and properties. The GUI is updated on the end of every turn
  
## Bugs Fixed
* **Game can now be won**
* Player's pieces and their properties are removed when they go bankrupt
* All mutator methods in Player now handle inputs appropriately (all unit tests pass now)
* Players are now identified properly (added an ID field for each player which is reflected in the GUI)
* Changed over to using Vectors to prevent concurrency related issues
* All chance/community chest cards work as expected (see "Bugs To Be Fixed" list to see what cards were fixed)

## Bugs To Be Fixed
* ~~The game cannot be won in its current state~~
* ~~Player's pieces and properties are not removed after they go bankrupt (in progress, mostly working)~~
* ~~Various mutator methods don't do appropriate checks before changing instance variables~~
* Players don't always roll while in jail
* No option to use a get out of jail card (always used by default)
* ~~Multiple chance/community chest cards are broken (DONE - Important to Note: I was unable to test the pay and collect cards as my gui does not show player money amounts however the lua errors were arithmetic so it should work as expected)~~
  * ~~"Advance to the nearest..." card will cause player to move backwards if the closest tile is behind them. According to the game rules the player's piece should only move forward (hence '_advance_') -DONE~~
  * ~~"Pay each player..." card will take money for _ALL_ of the players in the game, including the one it took money from -DONE~~
  * ~~"Collect $<amount> from each player..." card, similar to the last, will give a player money for all players _including_ themselves -DONE~~
  * ~~"From sale of stock you get..." card takes away money from the player instead of giving them money -DONE~~
* ~~The identification for each player in the GUI isn't accurate (_Player 2 can change to Player 1 if a player leaves. This bug was uncovered by fixing the bug that prevented bankrupted players from leaving the game_)~~
* Player's money amount isn't always updated right away in the GUI (sometimes it takes an extra turn)
* ~~ConcurrenctModificationExceptions are thrown randomly~~
