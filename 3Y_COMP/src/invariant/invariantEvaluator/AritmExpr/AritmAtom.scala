package invariant.invariantEvaluator.AritmExpr


abstract class AritmAtom {
 val OPR:Int =0 ;
 val VAL:Int =1 ;
 val VAR:Int =2 ;

  def getType():Int;
}
