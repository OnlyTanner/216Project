local player = require("PlayerLuaLibrary")
local board = require("BoardLuaLibrary")
local lib = require("LuaLibrary")

player.sendToJail(player.currPlayer())
lastPos = player.getPlayerPos(player.currPlayer())
player.setPlayerPos(player.currPlayer(), board.getJailSpace())
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
lib.notify("Player " .. tostring(player.currPlayer() + 1) .. " was sent to jail!")
