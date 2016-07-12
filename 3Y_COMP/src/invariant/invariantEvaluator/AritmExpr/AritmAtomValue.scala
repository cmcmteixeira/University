package invariant.invariantEvaluator.AritmExpr

import invariant.invariantEvaluator.Parser.{InvariantParserTokenManager, InvariantParserTreeConstants,InvariantParserConstants}
import invariant.Invariant
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
import invariant.invariantEvaluator.Exceptions.{InvariantExceptionIllegalMacro, InvariantExceptionIllegalVariable}


class AritmAtomValue(strValue:String,dataType:Int,variables:Array[Invariant],macros:HashMap[String,VarDecExpr]) extends AritmAtom{
  var value:Double=0;
  var key:String=null;
  if (dataType == InvariantParserConstants.VAR){}
  if (dataType == InvariantParserConstants.NUM){value = strValue.toDouble;}
  if (dataType == InvariantParserConstants.VAR_LOCAL){key = strValue;}

  def getType(): Int={return VAL;}

  def evaluate:Double={
     if( dataType == InvariantParserConstants.NUM){return value};
    try {
      if (dataType == InvariantParserConstants.VAR_LOCAL) {
        return (macros.get(key).get.evaluate)
      }
    }catch{
      case e:NoSuchElementException => throw new InvariantExceptionIllegalMacro(key,"");
    }
    var index = strValue.replace("$","").toInt;
    if ( variables.length <= index){
      throw new InvariantExceptionIllegalVariable(strValue,null);
    }
    var value2 = variables(index).value
    return value2
  }


}
