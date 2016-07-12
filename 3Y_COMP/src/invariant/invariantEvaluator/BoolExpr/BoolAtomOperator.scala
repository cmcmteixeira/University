package invariant.invariantEvaluator.BoolExpr

import invariant.invariantEvaluator.Parser.InvariantParserTreeConstants

class BoolAtomOperator(name:Int) extends BoolAtom{
  var operation = name;


  def getType():Int={return OPR};
  def evaluate(val1:Boolean ,val2:Boolean):Boolean={
    if      ( operation == InvariantParserTreeConstants.JJTANDEXPR){return val1 && val2;}
    else if ( operation == InvariantParserTreeConstants.JJTOREXPR ){return val1 || val2;}

    return false;
  }
}
