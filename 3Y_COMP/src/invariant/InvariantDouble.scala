package invariant

import invariant.invariantEvaluator.InvariantEvaluator
import invariant.invariantEvaluator.Exceptions.InvariantExceptionRestr

class InvariantDouble(startValue:Double) extends Invariant {

		var value = startValue;
		var evaluator:InvariantEvaluator= null;
    var variables:Array[Invariant]=null;

    def restrict(rest:String,args:Invariant*){
      variables = new Array[Invariant](args.size+1);
      variables(0)=this;
      for (i<-0 to args.size-1){
        variables(i+1) = args(i);
      }
      evaluator = new InvariantEvaluator(rest,variables);

    }
    def is (arg:Double){
      val oldValue = value;
      value = arg;
      if (!evaluator.evaluate()){  value = oldValue; throw new InvariantExceptionRestr(evaluator.expr,value,arg); }
    }

		def +(arg:InvariantDouble) :  Double={return arg.value + value;}
		def +(arg:Double) 	       :  Double={return value + arg;}
		def +(arg:Int) 		         :  Double={return value + arg;}
		def +(arg:Float)           :  Double={return value + arg;}

		def -(arg:InvariantDouble) : Double={return arg.value - value;}
		def -(arg:Double) 	       : Double={return value - arg;}
		def -(arg:Int) 		         : Double={return value - arg;}
		def -(arg:Float) 	         : Double={return value - arg;}

		def *(arg:InvariantDouble) : Double={return arg.value * value;}
		def *(arg:Double) 	       : Double={return value * arg;}
		def *(arg:Int) 		         : Double={return value * arg;}
		def *(arg:Float) 	         : Double={return value * arg;}

		def /(arg:InvariantDouble) : Double={return arg.value / value;}
		def /(arg:Double) 	       : Double={return value / arg;}
		def /(arg:Int) 		         : Double={return value / arg;}
		def /(arg:Float) 	         : Double={return value / arg;}

		def %(arg:InvariantDouble) : Double={return arg.value % value;}
		def %(arg:Double) 	       : Double={return value % arg;}
		def %(arg:Int) 		         : Double={return value % arg;}
		def %(arg:Float) 	         : Double={return value % arg;}

		def !=(arg:InvariantDouble):  Boolean={return value != arg.value;}
		def !=(arg:Double) 	       :  Boolean={return value != arg;}
		def !=(arg:Int) 	         :  Boolean={return value != arg;}
		def !=(arg:Float) 	       :  Boolean={return value != arg;}

		def ==(arg:InvariantDouble):  Boolean={return value == arg.value;}
		def ==(arg:Double) 	       :  Boolean={return value == arg;}
		def ==(arg:Int) 	         :  Boolean={return value == arg;}
		def ==(arg:Float) 	       :  Boolean={return value == arg;}

		def <(arg:InvariantDouble) :  Boolean={return value < arg.value;}
		def <(arg:Double) 	       :  Boolean={return value < arg;}
		def <(arg:Int) 		         :  Boolean={return value < arg;}
		def <(arg:Float) 	         :  Boolean={return value < arg;}

		def <=(arg:InvariantDouble):  Boolean={return value <= arg.value;}
		def <=(arg:Double)         :  Boolean={return value <= arg;}
		def <=(arg:Int) 	         :  Boolean={return value <= arg;}
		def <=(arg:Float) 	       :  Boolean={return value <= arg;}

		def >(arg:InvariantDouble) :  Boolean={return value > arg.value;}
		def >(arg:Double) 	       :  Boolean={return value > arg;}
		def >(arg:Int) 		         :  Boolean={return value > arg;}
		def >(arg:Float) 	         :  Boolean={return value > arg;}

		def >=(arg:InvariantDouble):  Boolean={return value >= arg.value;}
		def >=(arg:Double) 	       :  Boolean={return value >= arg;}
		def >=(arg:Int) 	         :  Boolean={return value >= arg;}
		def >=(arg:Float) 	       :  Boolean={return value >= arg;}
	}

