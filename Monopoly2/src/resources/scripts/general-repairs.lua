local player = require("PlayerLuaLibrary")

curr = player.currPlayer()
player.takeMoney(curr, player.getHouseCnt(curr) * 25 + player.getHotelCnt(curr) * 100)
