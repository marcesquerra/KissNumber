package com.bryghts


import scala.runtime.IntegralProxy
import java.lang.{Long => JavaLong}
import scala.runtime.FractionalProxy
import scala.runtime.RangedProxy
import scala.runtime.ScalaNumberProxy
import scala.collection.immutable.NumericRange
import scala.collection.immutable.Range
import scala.collection.generic.CanBuildFrom


package object kissnumber
{

	implicit def KissIntegerNumber(in: Byte):   IntegerNumber = IntegerNumber(in)
	implicit def KissIntegerNumber(in: Char):   IntegerNumber = IntegerNumber(in)
	implicit def KissIntegerNumber(in: Short):  IntegerNumber = IntegerNumber(in)
	implicit def KissIntegerNumber(in: Int):    IntegerNumber = IntegerNumber(in)
	implicit def KissIntegerNumber(in: Long):   IntegerNumber = IntegerNumber(in)

	implicit def KissRealNumber(in: Float):     RealNumber    = RealNumber(in)
	implicit def KissRealNumber(in: Double):    RealNumber    = RealNumber(in)

	implicit def KissNumber(in: IntegerNumber): Long          = in.v
	implicit def KissNumber(in: RealNumber):    Double        = in.v



	implicit object NumberIsFractional      extends NumberIsFractionalDef      with NumberOrderingDef
	implicit object NumberOrdering          extends NumberOrderingDef

	         object NumberAsIfIntegral      extends NumberAsIfIntegralDef      with NumberOrderingDef

	implicit object IntegerNumberIsIntegral extends IntegerNumberIsIntegralDef with IntegerNumberOrderingDef
	implicit object IntegerNumberOrdering   extends IntegerNumberOrderingDef

	implicit object RealNumberIsFractional  extends RealNumberIsFractionalDef  with RealNumberOrderingDef
	implicit object RealNumberOrdering      extends RealNumberOrderingDef

	         object RealNumberAsIfIntegral  extends RealNumberAsIfIntegralDef  with RealNumberOrderingDef



	implicit class RichIntegerNumber(val self: IntegerNumber) extends AnyVal with IntegralProxy[IntegerNumber]
	{

		@inline private def v = self.v

		protected def num = IntegerNumberIsIntegral
		protected def ord = IntegerNumberOrdering

		def toBinaryString: String = JavaLong.toBinaryString(v)
		def toHexString:    String = JavaLong.toHexString(v)
		def toOctalString:  String = JavaLong.toOctalString(v)

		override def isValidByte   = v .toByte  .toLong   ==   v
		override def isValidShort  = v .toShort .toLong   ==   v
		override def isValidChar   = v .toChar  .toLong   ==   v
		override def isValidInt    = v .toInt   .toLong   ==   v

	}

	implicit class RichRealNumber(val self: RealNumber) extends AnyVal with FractionalProxy[RealNumber] 
	{

		@inline private def v = self.v


		protected def ord            = RealNumberOrdering
		protected def num            = RealNumberIsFractional
		protected def integralNum    = RealNumberAsIfIntegral

		def round:     IntegerNumber = IntegerNumber(math.round(v))
		def ceil:      RealNumber    = RealNumber(math.ceil(v))
		def floor:     RealNumber    = RealNumber(math.floor(v))

		def toRadians: Double = math.toRadians(v)
		def toDegrees: Double = math.toDegrees(v)

		def isNaN:         Boolean = java.lang.Double.isNaN(v)
		def isInfinity:    Boolean = java.lang.Double.isInfinite(v)
		def isPosInfinity: Boolean = isInfinity && v > 0.0
		def isNegInfinity: Boolean = isInfinity && v < 0.0

		override def isValidByte = v.toByte.toDouble == v
		override def isValidShort = v.toShort.toDouble == v
		override def isValidChar = v.toChar.toDouble == v
		override def isValidInt = v.toInt.toDouble == v

		override def isWhole =
		{
			val l = v.toLong
			v.toDouble == v || v == Long.MaxValue && v < Double.PositiveInfinity || l == Long.MinValue && v > Double.NegativeInfinity
		}
	}

	implicit class RichNumber(val self: Number) extends AnyVal with RangedProxy[Number] with ScalaNumberProxy[Number]
	{

		protected def num = NumberIsFractional
		protected def ord = NumberOrdering

		protected implicit def integralNum = NumberAsIfIntegral

		type ResultWithoutStep = Range.Partial[Number, NumericRange[Number]]

		def isWhole() = self match {case n: IntegerNumber => true case n: RealNumber => n.isWhole}
		def until(end: Number): ResultWithoutStep                  = new Range.Partial(NumericRange(self, end, _))

		def until(end: Number, step: Number): NumericRange.Exclusive[Number] = NumericRange(self, end, step)
		def to(end: Number): ResultWithoutStep                     = new Range.Partial(NumericRange.inclusive(self, end, _))
		def to(end: Number, step: Number): NumericRange.Inclusive[Number]    = NumericRange.inclusive(self, end, step)
	}

}

