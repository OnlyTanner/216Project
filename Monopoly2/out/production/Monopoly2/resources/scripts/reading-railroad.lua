local player = require("PlayerLuaLibrary")
local board = require("BoardLuaLibrary")
local lib = require("LuaLibrary")

lastPos = player.getPlayerPos(player.currPlayer())
player.setPlayerPos(player.currPlayer(), board.getBoardSpace("Reading Railroad"))
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
