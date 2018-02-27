local player = require("PlayerLuaLibrary")
local lib = require("LuaLibrary")
local board = require("BoardLuaLibrary")

pos = board.fixBoardPos(player.getPlayerPos(player.currPlayer()) - 3);
player.setPlayerPos(player.currPlayer(), pos)
