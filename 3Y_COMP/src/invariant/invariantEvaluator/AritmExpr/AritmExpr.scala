package invariant.invariantEvaluator.AritmExpr
import invariant.invariantEvaluator.Parser.{InvariantParserConstants, InvariantParserTreeConstants, SimpleNode}
import java.util.Vector
import java.util.Stack
import invariant.Invariant
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
;

class AritmExpr(rootNode:SimpleNode,variables:Array[Invariant],macros:HashMap[String,VarDecExpr]) {

  var currentNode:SimpleNode = rootNode;
  var infinite:Boolean = true;
  var expr = new Vector[AritmAtom]();
  infinite = rootNode.jjtGetNumChildren() ==0;
  if ( !infinite){moveDown(currentNode);}


  def evaluate():Double={
    if ( infinite){throw new AritmInfiniteException();}
    var stack:Stack[Double]=new Stack[Double];

    for(i <- 0 to expr.size-1){
      if ( expr.elementAt(i).isInstanceOf[AritmAtomOperator]){
        val second = stack.pop;
        val first  = stack.pop;
        stack push expr.elementAt(i).asInstanceOf[AritmAtomOperator].evaluate(first,second);
      }
      else {
        stack.push(expr.elementAt(i).asInstanceOf[AritmAtomValue].evaluate);
      }
    }
    var result = stack.pop();
    return result;
  }

  def moveDown(node:SimpleNode){

    if      ( node.getID == InvariantParserTreeConstants.JJTMULTEXPR ){moveDownMultExpr(node);}
    else if ( node.getID == InvariantParserTreeConstants.JJTMULTEXPR_){moveDownMult_Expr(node);}
    else if ( node.getID == InvariantParserTreeConstants.JJTSUMEXPR  ){moveDownSumExpr(node);}
    else if ( node.getID == InvariantParserTreeConstants.JJTSUMEXPR_ ){moveDownSum_Expr(node);}
    else if ( node.getID == InvariantParserTreeConstants.JJTATOM     ){moveDownAtomExpr(node);}
    else if ( node.getID == InvariantParserTreeConstants.JJTARITM    ){moveDownAritmExpr(node);}

  }

  def moveDownAritmExpr(node:SimpleNode){
    if (node.jjtGetNumChildren() == 1){moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode]);}
  }

  def moveDownSumExpr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}
    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    if ( node.jjtGetNumChildren() == 2) {
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }
  def moveDownSum_Expr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    expr.add(new AritmAtomOperator(node.operators.get(0)));
    if (node.jjtGetNumChildren() > 1){
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }

  def moveDownMultExpr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    if (node.jjtGetNumChildren() > 1 ) {
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }
  def moveDownMult_Expr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    expr.add(new AritmAtomOperator(node.operators.get(0)));
    if (node.jjtGetNumChildren() > 1){
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }

  def moveDownAtomExpr(node:SimpleNode){
    if (node.jjtGetNumChildren() > 1 ){/*ERRO ARVORE INVALIDA*/}

    if (node.jjtGetNumChildren() == 1 && !node.negative){
      return moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode]);
    }

    if (node.jjtGetNumChildren() == 1 &&  node.negative) {
      moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode]);
      expr.add(new AritmAtomValue("-1",InvariantParserConstants.NUM,variables,macros));
      expr.add(new AritmAtomOperator(InvariantParserConstants.OP_MUL))

    }
    expr.add(new AritmAtomValue(node.numVar,node.operators.elementAt(0),variables,macros)) ;
    if ( node.negative){
      expr.add(new AritmAtomValue("-1",InvariantParserConstants.NUM,variables,macros));
      expr.add(new AritmAtomOperator(InvariantParserConstants.OP_MUL))
    }
  }
}
