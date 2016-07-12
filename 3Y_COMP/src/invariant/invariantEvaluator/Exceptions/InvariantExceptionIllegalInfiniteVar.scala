package invariant.invariantEvaluator.Exceptions

import java.io.{PrintWriter, PrintStream}


class InvariantExceptionIllegalInfiniteVar extends InvariantException{
  var expr:String="";
  override def printStackTrace(){
    println("\nInvariant Illegal Infinite Exception:\n");
    println("\n@inf was found inside var declaration expression{}\n");
    println("Expression provided: \n"+expr)
    println("\n");
    super.printStackTrace();
  }
  override def printStackTrace(s:PrintStream){
    s.print("\nInvariant Illegal Infinite Exception:\n");
    s.print("\n@inf was found inside var declaration expression{}\n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    super.printStackTrace(s);
  }
  override def printStackTrace(s:PrintWriter) {
    s.print("\nInvariant Illegal Infinite Exception:\n");
    s.print("\n@inf was found inside var declaration expression{}\n");
    s.print("Expression provided: \n" + expr)
    s.print("\n");
    super.printStackTrace(s);
  }
}
