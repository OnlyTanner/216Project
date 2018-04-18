local player = require("core.PlayerLuaLibrary")

curr = player.currPlayer()
player.takeMoney(curr, player.getHouseCnt(curr) * 40 + player.getHotelCnt(curr) * 115)
