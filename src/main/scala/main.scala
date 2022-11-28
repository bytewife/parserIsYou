/*
Grammar:

Start = Noun T_Verb Property
      | Noun T_Verb Noun

Noun = (T_Not | epsilon) Noun
Property = (T_Not | epsilon) Property
*/

// Borrowed these Terminals from https://github.com/BrianPopeck/baba-parser/blob/master/terminal_symbols.py
val TERMINAL_SYMBOLS = Map(
  "T_Noun" -> List("ALGAE", "ALL", "ANNI", "BABA", "BAT", "BELT", "BIRD", "BOG", "BOLT", "BOX", "BRICK", "BUBBLE", "BUG", "CAKE", "CLIFF", "CLOUD", "COG", "CRAB", "CURSOR", "DOOR", "DUST", "EMPTY", "FENCE", "FIRE", "FLAG", "FLOWER", "FOLIAGE", "FRUIT", "FUNGUS", "GHOST", "GRASS", "GROUP", "HAND", "HEDGE", "ICE", "IMAGE", "JELLY", "KEKE", "KEY", "LAVA", "LEAF", "LEVEL", "LINE", "LOVE", "ME", "MOON", "ORB", "PILLAR", "PIPE", "ROBOT", "ROCK", "ROCKET", "ROSE", "RUBBLE", "SKULL", "STAR", "STATUE", "SUN", "TEXT", "TILE", "TREE", "UFO", "WALL", "WATER"),
  "T_Verb" -> List("IS", "HAS", "MAKE", "FEAR", "EAT", "FOLLOW", "MIMIC", "PLAY"),
  "T_Property" -> List("YOU", "STOP", "PUSH", "PULL", "SWAP", "TELE", "MOVE", "FALL", "SHIFT", "WIN", "DEFEAT", "SINK", "HOT", "MELT", "SHUT", "OPEN", "WEAK", "FLOAT", "MORE", "UP", "DOWN", "LEFT", "RIGHT", "WORD", "BEST", "SLEEP", "RED", "BLUE", "HIDE", "BONUS", "END", "DONE", "SAFE", "SAD", "WONDER", "YOU2", "POWER", "SELECT", "TURNCW", "TURNCCW", "STILL", "CHILL", "BACK", "ORANGE", "YELLOW", "GREEN", "PURPLE", "PINK", "WHITE", "GREY", "BLACK", "BROWN", "FALLDOWN", "FALLLEFT", "FALLUP", "FALLRIGHT", "BROKEN", "NUDGEUP", "NUDGEDOWN", "NUDGELEFT", "NUDGERIGHT"),
  "T_Not" -> List("NOT")
)

@main def main() = {
}
