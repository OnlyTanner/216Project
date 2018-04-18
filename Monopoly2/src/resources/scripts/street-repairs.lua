local player = require("PlayerLuaLibrary")

curr = player.currPlayer()
player.takeMoney(curr, player.getHouseCnt(curr) * 40 + player.getHotelCnt(curr) * 115)
