
if not advturtle then
	error( "Cannot load advanced turtle API on computer" )
end

native = advturtle.native or advturtle

local function wrap( _sCommand )
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

advturtle["findid"] = native["findBlockID"]
advturtle["findblockid"] = native["findBlockID"]

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


for k,v in pairs( native ) do
	if type( k ) == "string" and type( v ) == "function" then
		if advturtle[k] == nil then
			advturtle[k] = wrap( k )
		end
	end
end

local env = getfenv()
for k,v in pairs( advturtle ) do
	env[k] = v
end
