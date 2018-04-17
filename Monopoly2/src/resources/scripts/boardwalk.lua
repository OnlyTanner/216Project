local player = require("core.PlayerLuaLibrary")
local board = require("core.BoardLuaLibrary")
local lib = require("core.LuaLibrary")

lastPos = player.getPlayerPos(player.currPlayer())
player.setPlayerPos(player.currPlayer(), board.getBoardSpace("Boardwalk"))
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
