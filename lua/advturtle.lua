
if not advturtle or not turtle then
	error( "Cannot load advanced turtle API on this computer" )
end

native = advturtle.native or advturtle

local function wrap( _sCommand )
	return function( ... )
		local id = native[_sCommand]( ... )
		local event, responseID, success
		while event ~= "turtle_response" or responseID ~= id do
			event, responseID, success = os.pullEvent( "turtle_response" )
		end
		return success
	end
end

local function adv_wrap( _sCommand )
	return function( ... )
		local id = native[_sCommand]( ... )
		local event, responseID, success
		while event ~= "advturtle_response" or responseID ~= id do
			event, responseID, success = os.pullEvent( "advturtle_response" )
		end
		return success
	end
end

local advturtle = {}

-- original turtle api
advturtle["forward"] = wrap( turtle.forward )
advturtle["back"] = wrap( turtle.back )
advturtle["up"] = wrap( turtle.up )
advturtle["down"] = wrap( turtle.down )
advturtle["turnLeft"] = wrap( turtle.turnLeft )
advturtle["turnRight"] = wrap( turtle.turnRight )
advturtle["select"] = wrap( turtle.select )
advturtle["getItemCount"] = turtle.getItemCount
advturtle["getItemSpace"] = turtle.getItemSpace
advturtle["dig"] = wrap( turtle.dig )
advturtle["digUp"] = wrap( turtle.digUp )
advturtle["digDown"] = wrap( turtle.digDown )
advturtle["place"] = wrap( turtle.place )
advturtle["placeUp"] = wrap( turtle.placeUp )
advturtle["placeDown"] = wrap( turtle.placeDown )
advturtle["detect"] = wrap( turtle.detect )
advturtle["detectUp"] = wrap( turtle.detectUp )
advturtle["detectDown"] = wrap( turtle.detectDown )
advturtle["compare"] = wrap( turtle.compare )
advturtle["compareUp"] = wrap( turtle.compareUp )
advturtle["compareDown"] = wrap( turtle.compareDown )
advturtle["drop"] = wrap( turtle.drop )

-- different spellings
advturtle["turnleft"] = advturtle["turnLeft"]
advturtle["turnright"] = advturtle["turnRight"]
advturtle["getitemcount"] = advturtle["getItemCount"]
advturtle["getitemspace"] = advturtle["getItemSpace"]
advturtle["digup"] = advturtle["digUp"]
advturtle["digdown"] = advturtle["digDown"]
advturtle["placeup"] = advturtle["placeUp"]
advturtle["placedown"] = advturtle["placeDown"]
advturtle["detectup"] = advturtle["detectUp"]
advturtle["detectdown"] = advturtle["detectDown"]
advturtle["compareup"] = advturtle["compareUp"]
advturtle["comparedown"] = advturtle["compareDown"]

-- additional spellings and additional commands
advturtle["findid"] = native["findBlockID"]
advturtle["findblockid"] = native["findBlockID"]
advturtle["findBlockID"] = native["findBlockID"]

advturtle["findBlock"] = native["findBlock"]
advturtle["findblock"] = native["findBlock"]

local function selectBlock( block )
	slot = native["findBlock"](block)
	if not slot then return false end
	turtle.select(slot)
	return true
end

advturtle["selectBlock"] = selectBlock
advturtle["selectblock"] = selectBlock

local function selectBlockID( block )
	slot = native["findBlockID"](block)
	if not slot then return false end
	turtle.select(slot)
	return true
end

advturtle["selectBlockID"] = selectBlockID
advturtle["selectblockid"] = selectBlockid

advturtle["placeFront"] = turtle.place
advturtle["placefront"] = turtle.place

local function left( width )
	if width == nil then width = 1 end
	if width < 1 then return false end

	turtle.turnLeft()

	local success = true
	for n=1,width do
		if not turtle.forward() then
			success = false
			break
		end
	end
	
	turtle.turnRight()
	
	return success
end

local function right( width )
	if width == nil then width = 1 end
	if width < 1 then return false end

	turtle.turnRight()

	local success = true
	for n=1,width do
		if not turtle.forward() then
			success = false
			break
		end
	end
	
	turtle.turnLeft()
	
	return success
end

advturtle["left"] = left
advturtle["right"] = right

local function getPos()
	return { native["x"](), native["y"](), native["z"]() }
end

advturtle["getPos"] = getPos
advturtle["getpos"] = getPos
advturtle["pos"] = getPos

advturtle["placefrontleftdown"] = native["placeFrontLeftDown"]
advturtle["placefrontdown"] = native["placeFrontDown"]
advturtle["placefrontrightdown"] = native["placeFrontRightDown"]

for k,v in pairs( native ) do
	if type( k ) == "string" and type( v ) == "function" then
		if advturtle[k] == nil then
			advturtle[k] = adv_wrap( k )
		end
	end
end

local env = getfenv()
for k,v in pairs( advturtle ) do
	env[k] = v
end
