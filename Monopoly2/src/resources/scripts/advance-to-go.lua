local player = require("PlayerLuaLibrary")
local lib = require("LuaLibrary")

player.setPlayerPos(player.currPlayer(), 0)
-- The player passed Go
player.giveMoney(player.currPlayer(), 200)
