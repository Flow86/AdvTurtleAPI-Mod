@ECHO OFF

set CYGWIN=nontsec

rsync -arv --existing ../src/minecraft/net/minecraft/src/ src/
rsync -arv --existing ../jars/mods/AdvTurtleAPI/lua/ lua/

PAUSE
