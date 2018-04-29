local player = require("core.PlayerLuaLibrary")
local board = require("core.BoardLuaLibrary")
local lib = require("core.LuaLibrary")

lastPos = player.getPlayerPos(player.currPlayer())

-- Get all the railroad board positions
utilities = {board.getBoardSpace("Electric Company") - lastPos,
    board.getBoardSpace("Water Works") - lastPos}

-- Find the nearest one
nearest = 99999
nearestPos = -1
for i=1,2 do
    if utilities[i] > 0 and utilities[i] < nearest then
        nearest = utilities[i]
        nearestPos = i
    end
end

if nearestPos == -1	then
	nearestPos = 1
	end;
player.setPlayerPos(player.currPlayer(), utilities[nearestPos] + lastPos)
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
