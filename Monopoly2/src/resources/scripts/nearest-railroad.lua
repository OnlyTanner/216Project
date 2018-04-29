local player = require("core.PlayerLuaLibrary")
local board = require("core.BoardLuaLibrary")
local lib = require("core.LuaLibrary")

lastPos = player.getPlayerPos(player.currPlayer())

-- Get all the railroad board positions
rr = {board.getBoardSpace("B. & O. Railroad") - lastPos,
        board.getBoardSpace("Reading Railroad") - lastPos,
        board.getBoardSpace("Pennsylvania Railroad") - lastPos,
        board.getBoardSpace("Short Line") - lastPos }

-- Find the nearest one
nearest = 99999
nearestPos = -1
for i=1,4 do
    if rr[i] > 0 and rr[i] < nearest then
        nearest = rr[i]
        nearestPos = i
    end
end

if nearestPos == -1	then
	nearestPos = 2
	end;

player.setPlayerPos(player.currPlayer(), rr[nearestPos] + lastPos)
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
