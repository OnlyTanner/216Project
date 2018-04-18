local player = require("core.PlayerLuaLibrary")
local lib = require("core.LuaLibrary")
local board = require("core.BoardLuaLibrary")

pos = board.fixBoardPos(player.getPlayerPos(player.currPlayer()) - 3);
player.setPlayerPos(player.currPlayer(), pos)
