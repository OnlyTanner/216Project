local player = require("core.PlayerLuaLibrary")
local board = require("core.BoardLuaLibrary")
local lib = require("core.LuaLibrary")

lastPos = player.getPlayerPos(player.currPlayer())

-- Get all the railroad board positions
utilities = {board.getBoardSpace("Electric Company") - lastPos,
    board.getBoardSpace("Water Works") - lastPos}

-- Find the nearest one
nearest = 99999
for i=1,2 do
    if utilities[i] > 0 and utilities[i] < nearest then
        nearest = utilities[i]
    end
end


player.setPlayerPos(player.currPlayer(), nearest)
if lastPos > player.getPlayerPos(player.currPlayer()) then
    -- The player passed Go
    player.giveMoney(player.currPlayer(), 200)
end
