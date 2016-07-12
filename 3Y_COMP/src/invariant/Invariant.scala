package invariant

/**
 * Created by Papa Formigas on 17-05-2014.
 */
abstract class Invariant {
  var value:Double;
  def getValue():Double={return value;}


  def +(arg:InvariantDouble) :  Double;
  def +(arg:Double) 	       :  Double
  def +(arg:Int) 		         :  Double
  def +(arg:Float)           :  Double

  def -(arg:InvariantDouble) : Double;
  def -(arg:Double) 	       : Double;
  def -(arg:Int) 		         : Double;
  def -(arg:Float) 	         : Double;

  def *(arg:InvariantDouble) : Double;
  def *(arg:Double) 	       : Double;
  def *(arg:Int) 		         : Double;
  def *(arg:Float) 	         : Double;

  def /(arg:InvariantDouble) : Double;
  def /(arg:Double) 	       : Double;
  def /(arg:Int) 		         : Double;
  def /(arg:Float) 	         : Double;

  def %(arg:InvariantDouble) : Double;
  def %(arg:Double) 	       : Double;
  def %(arg:Int) 		         : Double;
  def %(arg:Float) 	         : Double;

  def !=(arg:InvariantDouble):  Boolean;
  def !=(arg:Double) 	       :  Boolean;
  def !=(arg:Int) 	         :  Boolean;
  def !=(arg:Float) 	       :  Boolean;

  def ==(arg:InvariantDouble):  Boolean;
  def ==(arg:Double) 	       :  Boolean;
  def ==(arg:Int) 	         :  Boolean;
  def ==(arg:Float) 	       :  Boolean;

  def <(arg:InvariantDouble) :  Boolean;
  def <(arg:Double) 	       :  Boolean;
  def <(arg:Int) 		         :  Boolean;
  def <(arg:Float) 	         :  Boolean;

  def <=(arg:InvariantDouble):  Boolean;
  def <=(arg:Double)         :  Boolean;
  def <=(arg:Int) 	         :  Boolean;
  def <=(arg:Float) 	       :  Boolean;

  def >(arg:InvariantDouble) :  Boolean;
  def >(arg:Double) 	       :  Boolean;
  def >(arg:Int) 		         :  Boolean;
  def >(arg:Float) 	         :  Boolean;

  def >=(arg:InvariantDouble):  Boolean;
  def >=(arg:Double) 	       :  Boolean;
  def >=(arg:Int) 	         :  Boolean;
  def >=(arg:Float) 	       :  Boolean;
}
