import javax.naming.InvalidNameException
import scala.Console.in
import scala.collection.mutable.ArrayBuffer

/*
Grammar:

Start = Noun T_Verb (Property | Noun)

Noun = (T_Not | epsilon) T_Noun
Property = (T_Not | epsilon) T_Property
*/

// Borrowed these Terminals from https://github.com/BrianPopeck/baba-parser/blob/master/terminal_symbols.py
val TERMINAL_SYMBOLS = Map(
  "T_Noun" -> List("ALGAE", "ALL", "ANNI", "BABA", "BAT", "BELT", "BIRD", "BOG", "BOLT", "BOX", "BRICK", "BUBBLE", "BUG", "CAKE", "CLIFF", "CLOUD", "COG", "CRAB", "CURSOR", "DOOR", "DUST", "EMPTY", "FENCE", "FIRE", "FLAG", "FLOWER", "FOLIAGE", "FRUIT", "FUNGUS", "GHOST", "GRASS", "GROUP", "HAND", "HEDGE", "ICE", "IMAGE", "JELLY", "KEKE", "KEY", "LAVA", "LEAF", "LEVEL", "LINE", "LOVE", "ME", "MOON", "ORB", "PILLAR", "PIPE", "ROBOT", "ROCK", "ROCKET", "ROSE", "RUBBLE", "SKULL", "STAR", "STATUE", "SUN", "TEXT", "TILE", "TREE", "UFO", "WALL", "WATER"),
  "T_Verb" -> List("IS", "HAS", "MAKE", "FEAR", "EAT", "FOLLOW", "MIMIC", "PLAY"),
  "T_Property" -> List("YOU", "STOP", "PUSH", "PULL", "SWAP", "TELE", "MOVE", "FALL", "SHIFT", "WIN", "DEFEAT", "SINK", "HOT", "MELT", "SHUT", "OPEN", "WEAK", "FLOAT", "MORE", "UP", "DOWN", "LEFT", "RIGHT", "WORD", "BEST", "SLEEP", "RED", "BLUE", "HIDE", "BONUS", "END", "DONE", "SAFE", "SAD", "WONDER", "YOU2", "POWER", "SELECT", "TURNCW", "TURNCCW", "STILL", "CHILL", "BACK", "ORANGE", "YELLOW", "GREEN", "PURPLE", "PINK", "WHITE", "GREY", "BLACK", "BROWN", "FALLDOWN", "FALLLEFT", "FALLUP", "FALLRIGHT", "BROKEN", "NUDGEUP", "NUDGEDOWN", "NUDGELEFT", "NUDGERIGHT"),
  "T_Not" -> List("NOT")
)

class RuleReturnType(
  val isValid: Boolean,
 )

class Token(
  val category: String,
  val literal: String
)

// tokens format: (category, string)
var tokens: ArrayBuffer[Token] = ArrayBuffer[Token]()
var token_index = 0

@main def main() = {
  val input = "BABA IS WIN"
  val inputs = input.split(' ')
  scan(inputs)
  val isValid = parse()
  val result = if (isValid) "valid" else "invalid"
  println(s"'$input' is ${result}")
}

// Scan; get tokens.
def scan(strs: Array[String]) = {
  strs.foreach((str) => {
    tokenize(str) match {
      case Some(category) => tokens += Token(category, str)
      case None => {
        throw new IllegalArgumentException(s"Error: Invalid string '$str'. Exiting.")
      }
    }
  })
}

// Get terminal symbol of input string.
def tokenize(str: String): Option[String] = {
  for (category, literals) <- TERMINAL_SYMBOLS do
    if literals.contains(str) then
      return Some(category)
  return None
}

def parse(): Boolean = {
  return Start().isValid
}

def doRule(rules: (() => RuleReturnType)*): RuleReturnType = {
  val original_index = token_index
  for rule <- rules do
    val ret = rule()
    if ret.isValid then ()
    else
      token_index = original_index
      return RuleReturnType(false)
  return RuleReturnType(true)
}

// Determine if current token string matches given syntactic category.
def isCategory(category: String): RuleReturnType = {
  if tokens.length <= token_index then return RuleReturnType(false)
  if tokens(token_index).category == category then
    token_index += 1
    return RuleReturnType(true)
  return RuleReturnType(false)
}

def Start(): RuleReturnType = {
  // Abuse short-circuit OR.
  return RuleReturnType(doRule(Noun, T_Verb, Property).isValid || doRule(Noun, T_Verb, Property).isValid)
}

def Noun(): RuleReturnType = {
  return doRule(T_Not, T_Noun)
}

def Property(): RuleReturnType = {
  return doRule(T_Not, T_Property)
}

def T_Noun(): RuleReturnType = {
  return isCategory("T_Noun")
}

def T_Verb(): RuleReturnType = {
  return isCategory("T_Verb")
}

def T_Property(): RuleReturnType = {
  return isCategory("T_Property")
}

def T_Not(): RuleReturnType = {
  // Note: Since this contains '| epsilon', it will always match as true.
  return RuleReturnType(true)
}
