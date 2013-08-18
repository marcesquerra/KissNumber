package com.bryghts.kissnumber

import java.lang.{Double => JavaDouble}


trait IntegerNumberIsIntegralDef extends Integral[IntegerNumber]
{
	def plus     (x: IntegerNumber, y: IntegerNumber): IntegerNumber = x + y
	def minus    (x: IntegerNumber, y: IntegerNumber): IntegerNumber = x - y
	def times    (x: IntegerNumber, y: IntegerNumber): IntegerNumber = x * y
	def quot     (x: IntegerNumber, y: IntegerNumber): IntegerNumber = IntegerNumber(x.v / y.v)
	def rem      (x: IntegerNumber, y: IntegerNumber): IntegerNumber = x % y

	def negate   (x: IntegerNumber): IntegerNumber = -x

	def fromInt  (x: Int):           IntegerNumber = IntegerNumber(x)
	def toInt    (x: IntegerNumber): Int           = x.toInt
	def toLong   (x: IntegerNumber): Long          = x.v
	def toFloat  (x: IntegerNumber): Float         = x.toFloat
	def toDouble (x: IntegerNumber): Double        = x.toDouble
}

trait IntegerNumberOrderingDef extends Ordering[IntegerNumber]
{

	def compare(x: IntegerNumber, y: IntegerNumber) =
			     if (x <  y)  -1
			else if (x == y)   0
			else               1

}

trait RealNumberIsConflictedDef extends Numeric[RealNumber]
{
	def plus     (x: RealNumber, y: RealNumber): RealNumber = x + y
	def minus    (x: RealNumber, y: RealNumber): RealNumber = x - y
	def times    (x: RealNumber, y: RealNumber): RealNumber = x * y

	def negate   (x: RealNumber):                RealNumber = -x
	def fromInt  (x: Int):                       RealNumber = x

	def toInt    (x: RealNumber):                Int        = x.toInt
	def toLong   (x: RealNumber):                Long       = x.toLong
	def toFloat  (x: RealNumber):                Float      = x.toFloat
	def toDouble (x: RealNumber):                Double     = x
}

trait RealNumberIsFractionalDef extends RealNumberIsConflictedDef with Fractional[RealNumber]
{
	def div      (x: RealNumber, y: RealNumber): RealNumber = x / y
}

trait RealNumberAsIfIntegralDef extends RealNumberIsConflictedDef with Integral[RealNumber]
{
	def quot     (x: RealNumber, y: RealNumber): RealNumber = RealNumber((BigDecimal(x.v) / BigDecimal(y.v)).doubleValue)
	def rem      (x: RealNumber, y: RealNumber): RealNumber = RealNumber((BigDecimal(x.v) remainder BigDecimal(y.v)).doubleValue)
}

trait NumberIsConflictedDef extends Numeric[Number]
{
	def plus     (x: Number, y: Number): Number = x + y
	def minus    (x: Number, y: Number): Number = x - y
	def times    (x: Number, y: Number): Number = x * y

	def negate   (x: Number):            Number = -x
	def fromInt  (x: Int):               Number = IntegerNumber(x)

	def toInt    (x: Number):            Int        = x.toInt
	def toLong   (x: Number):            Long       = x.toLong
	def toFloat  (x: Number):            Float      = x.toFloat
	def toDouble (x: Number):            Double     = x.toDouble
}

trait NumberIsFractionalDef extends NumberIsConflictedDef with Fractional[Number]
{
	def div      (x: Number, y: Number): Number = x / y
}

trait NumberAsIfIntegralDef extends NumberIsConflictedDef with Integral[Number]
{
	private def bd(x: Number): BigDecimal = x match {case n: IntegerNumber => BigDecimal(n.v) case n: RealNumber => BigDecimal(n.v)}

	def quot     (x: Number, y: Number): Number = Number((bd(x) / bd(y)).doubleValue)
	def rem      (x: Number, y: Number): Number = Number((bd(x) remainder bd(y)).doubleValue)
}

trait RealNumberOrderingDef extends Ordering[RealNumber]
{outer =>

	         def compare (x: RealNumber, y: RealNumber): Int         = JavaDouble.compare(x.v, y.v)

	override def lteq    (x: RealNumber, y: RealNumber): Boolean     = x <= y
	override def gteq    (x: RealNumber, y: RealNumber): Boolean     = x >= y
	override def lt      (x: RealNumber, y: RealNumber): Boolean     = x < y
	override def gt      (x: RealNumber, y: RealNumber): Boolean     = x > y
	override def equiv   (x: RealNumber, y: RealNumber): Boolean     = x == y
	override def max     (x: RealNumber, y: RealNumber): RealNumber  = RealNumber(math.max(x.v, y.v))
	override def min     (x: RealNumber, y: RealNumber): RealNumber  = RealNumber(math.min(x.v, y.v))

	override def reverse: Ordering[RealNumber] = new RealNumberOrderingDef
	{
		override def reverse = outer

		override def compare (x: RealNumber, y: RealNumber): Int     = outer.compare(y, x)

		override def lteq    (x: RealNumber, y: RealNumber): Boolean = outer.lteq(y, x)
		override def gteq    (x: RealNumber, y: RealNumber): Boolean = outer.gteq(y, x)
		override def lt      (x: RealNumber, y: RealNumber): Boolean = outer.lt(y, x)
		override def gt      (x: RealNumber, y: RealNumber): Boolean = outer.gt(y, x)
	}
}

trait NumberOrderingDef extends Ordering[Number]
{outer =>

	def compare (x: Number, y: Number): Int = (x, y) match
	{
		case (x: IntegerNumber, y: IntegerNumber) => IntegerNumberOrdering.compare(x, y)
		case (x: RealNumber,    y: RealNumber)    => RealNumberOrdering.compare(x, y)
		case (x: RealNumber,    y: IntegerNumber) => BigDecimal(x.v).compare(BigDecimal(y.v))
		case (x: IntegerNumber, y: RealNumber)    => BigDecimal(x.v).compare(BigDecimal(y.v))
	}

	override def max     (x: Number, y: Number): Number = (x, y) match
	{
		case (x: IntegerNumber, y: IntegerNumber) => IntegerNumberOrdering.max(x, y)
		case (x: RealNumber,    y: RealNumber)    => RealNumberOrdering.max(x, y)
		case (x: RealNumber,    y: IntegerNumber) => Number(BigDecimal(x.v).max(BigDecimal(y.v)))
		case (x: IntegerNumber, y: RealNumber)    => Number(BigDecimal(x.v).max(BigDecimal(y.v)))
	}

	override def min     (x: Number, y: Number): Number = (x, y) match
	{
		case (x: IntegerNumber, y: IntegerNumber) => IntegerNumberOrdering.min(x, y)
		case (x: RealNumber,    y: RealNumber)    => RealNumberOrdering.min(x, y)
		case (x: RealNumber,    y: IntegerNumber) => Number(BigDecimal(x.v).min(BigDecimal(y.v)))
		case (x: IntegerNumber, y: RealNumber)    => Number(BigDecimal(x.v).min(BigDecimal(y.v)))
	}

	override def lteq    (x: Number, y: Number): Boolean     = x <= y
	override def gteq    (x: Number, y: Number): Boolean     = x >= y
	override def lt      (x: Number, y: Number): Boolean     = x < y
	override def gt      (x: Number, y: Number): Boolean     = x > y
	override def equiv   (x: Number, y: Number): Boolean     = x == y
	
	override def reverse: Ordering[Number] = new NumberOrderingDef
	{
		override def reverse = outer
				
				override def compare (x: Number, y: Number): Int     = outer.compare(y, x)
				
				override def lteq    (x: Number, y: Number): Boolean = outer.lteq(y, x)
				override def gteq    (x: Number, y: Number): Boolean = outer.gteq(y, x)
				override def lt      (x: Number, y: Number): Boolean = outer.lt(y, x)
				override def gt      (x: Number, y: Number): Boolean = outer.gt(y, x)
	}
}
