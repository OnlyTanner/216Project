local player = require("core.PlayerLuaLibrary")
local lib = require("core.LuaLibrary")

player.takeMoney(player.currPlayer(), (player.count()-1) * 50)
for i=0,player.count()-1 do
	if i ~= player.currPlayer() then
		player.giveMoney(i, 50)
	end
end
