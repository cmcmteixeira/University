package invariant.invariantEvaluator.Exceptions

import java.io.{PrintWriter, PrintStream}

/**
 * Created by Papa Formigas on 20-05-2014.
 */
class InvariantExceptionIllegalVariable(variable:String,exprA:String) extends InvariantException{
  var expr = exprA;

  override def printStackTrace(){
    println("\nInvariant Illegal Variable Exception:\n");
    println("\nVariable : "+variable + " does not exist \n");
    println("Expression provided: \n"+expr)
    println("\n");
    super.printStackTrace();
  }
  override def printStackTrace(s:PrintStream){
    s.print("\nInvariant Illegal Variable Exception:\n");
    s.print("\nVariable : "+variable + " does not exist \n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    super.printStackTrace(s);
  }
  override def printStackTrace(s:PrintWriter) {
    s.print("\nInvariant Illegal Variable Exception:\n");
    s.print("\nVariable : "+variable + " does not exist \n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    super.printStackTrace(s);
  }
}
