local player = require("core.PlayerLuaLibrary")
local board = require("core.BoardLuaLibrary")
local lib = require("core.LuaLibrary")

player.sendToJail(player.currPlayer())
lastPos = player.getPlayerPos(player.currPlayer())
player.setPlayerPos(player.currPlayer(), board.getJailSpace())
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
lib.notify("core.Player " .. tostring(player.currPlayer() + 1) .. " was sent to jail!")
