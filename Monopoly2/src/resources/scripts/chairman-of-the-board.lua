local player = require("PlayerLuaLibrary")
local lib = require("LuaLibrary")

player.takeMoney(player.currPlayer(), player.count() * 50)
for i=0,player.count()-1 do
	if i ~= player.currPlayer() then
		player.giveMoney(i, 50)
	end
end
