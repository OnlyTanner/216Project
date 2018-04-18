local player = require("core.PlayerLuaLibrary")
local lib = require("core.LuaLibrary")

player.setPlayerPos(player.currPlayer(), 0)
-- The player passed Go
player.giveMoney(player.currPlayer(), 200)
