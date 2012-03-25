
if not advturtle or not turtle then
	error( "Cannot load advanced turtle API on this computer" )
end

if not os.loadAPI("rom/apis/turtle/turtle") then
	error( "Unable to load turtle API on this computer!" )
	return
end

native = advturtle.native or advturtle

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
advturtle["forward"] = turtle.forward
advturtle["back"] = turtle.back
advturtle["up"] = turtle.up
advturtle["down"] = turtle.down
advturtle["turnLeft"] = turtle.turnLeft
advturtle["turnRight"] = turtle.turnRight
advturtle["select"] = turtle.select
advturtle["getItemCount"] = turtle.getItemCount
advturtle["getItemSpace"] = turtle.getItemSpace
--advturtle["dig"] = turtle.dig
--advturtle["digUp"] = turtle.digUp
--advturtle["digDown"] = turtle.digDown
--advturtle["place"] = turtle.place
--advturtle["placeUp"] = turtle.placeUp
--advturtle["placeDown"] = turtle.placeDown
--advturtle["detect"] = turtle.detect
--advturtle["detectUp"] = turtle.detectUp
--advturtle["detectDown"] = turtle.detectDown
--advturtle["compare"] = turtle.compare
--advturtle["compareUp"] = turtle.compareUp
--advturtle["compareDown"] = turtle.compareDown
advturtle["drop"] = turtle.drop

-- different spellings
advturtle["turnleft"] = advturtle["turnLeft"]
advturtle["turnright"] = advturtle["turnRight"]
advturtle["getitemcount"] = advturtle["getItemCount"]
advturtle["getitemspace"] = advturtle["getItemSpace"]
--advturtle["digup"] = advturtle["digUp"]
--advturtle["digdown"] = advturtle["digDown"]
--advturtle["placeup"] = advturtle["placeUp"]
--advturtle["placedown"] = advturtle["placeDown"]
--advturtle["detectup"] = advturtle["detectUp"]
--advturtle["detectdown"] = advturtle["detectDown"]
--advturtle["compareup"] = advturtle["compareUp"]
--advturtle["comparedown"] = advturtle["compareDown"]

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

--advturtle["placeFront"] = turtle.place
--advturtle["placefront"] = turtle.place

--advturtle["detectFront"] = turtle.detect
--advturtle["detectfront"] = turtle.detect

--advturtle["compareFront"] = turtle.compare
--advturtle["comparefront"] = turtle.compare

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

advturtle["x"] = native["x"]
advturtle["y"] = native["y"]
advturtle["z"] = native["z"]
advturtle["dir"] = native["facing"]
advturtle["face"] = native["facing"]
advturtle["facing"] = native["facing"]

for k,v in pairs( native ) do
	if type( k ) == "string" and type( v ) == "function" then
		if advturtle[k] == nil then
			advturtle[k] = adv_wrap( k )
		end
	end
end

advturtle["placeFront"] = advturtle["place"]
advturtle["digFront"] = advturtle["dig"]
advturtle["compareFront"] = advturtle["compare"]
advturtle["detectFront"] = advturtle["detect"]

advturtle["placefrontleftdown"] = advturtle["placeFrontLeftDown"]
advturtle["placefrontdown"] = advturtle["placeFrontDown"]
advturtle["placefrontrightdown"] = advturtle["placeFrontRightDown"]
-- todo more lower case names

local function replace( where )
	return function( ... )
		if advturtle["detect"..where]() then
			if advturtle["compare"..where]() then
				return true
			end
			if not advturtle["dig"..where]() then
				print("can't dig "..where)
				return false 
			end
		end
		return advturtle["place"..where]()
	end
end

offsets = {
	"FrontLeftDown", "FrontDown", "FrontRightDown",
	"FrontLeft", "Front", "FrontRight",
	"FrontLeftUp", "FrontUp", "FrontRightUp",
	
	"LeftDown", "Down", "RightDown",
	"Left",	"Right",
	"LeftUp", "Up", "RightUp",
	
	"BackLeftDown", "BackDown", "BackRightDown", 
	"BackLeft", "Back", "BackRight",
	"BackLeftUp", "BackUp", "BackRightUp",
}

advturtle["replace"] = replace("")

for k,v in pairs( offsets ) do
	advturtle["replace"..v] = replace(v)
end

local env = getfenv()
for k,v in pairs( advturtle ) do
	env[k] = v
end
