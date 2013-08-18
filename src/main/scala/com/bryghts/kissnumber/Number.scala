package com.bryghts.kissnumber

import scala.runtime.IntegralProxy
import scala.runtime.FractionalProxy
import scala.runtime.RangedProxy
import scala.runtime.ScalaNumberProxy

sealed abstract class Number
{
	def toByte:                Byte
	def toShort:               Short
	def toChar:                Char
	def toInt:                 Int
	def toLong:                Long
	def toFloat:               Float
	def toDouble:              Double
	def toInteger:             IntegerNumber
	def toReal:                RealNumber

	def unary_+ :              Number
	def unary_- :              Number

	def +(x: String):          String

	def ==(x: Number):         Boolean
	def ==(x: IntegerNumber):  Boolean
	def ==(x: RealNumber):     Boolean
	def ==(x: Byte):           Boolean
	def ==(x: Short):          Boolean
	def ==(x: Char):           Boolean
	def ==(x: Int):            Boolean
	def ==(x: Long):           Boolean
	def ==(x: Float):          Boolean
	def ==(x: Double):         Boolean

	def !=(x: Number):         Boolean
	def !=(x: IntegerNumber):  Boolean
	def !=(x: RealNumber):     Boolean
	def !=(x: Byte):           Boolean
	def !=(x: Short):          Boolean
	def !=(x: Char):           Boolean
	def !=(x: Int):            Boolean
	def !=(x: Long):           Boolean
	def !=(x: Float):          Boolean
	def !=(x: Double):         Boolean

	def <(x: Number):          Boolean
	def <(x: IntegerNumber):   Boolean
	def <(x: RealNumber):      Boolean
	def <(x: Byte):            Boolean
	def <(x: Short):           Boolean
	def <(x: Char):            Boolean
	def <(x: Int):             Boolean
	def <(x: Long):            Boolean
	def <(x: Float):           Boolean
	def <(x: Double):          Boolean

	def <=(x: Number):         Boolean
	def <=(x: IntegerNumber):  Boolean
	def <=(x: RealNumber):     Boolean
	def <=(x: Byte):           Boolean
	def <=(x: Short):          Boolean
	def <=(x: Char):           Boolean
	def <=(x: Int):            Boolean
	def <=(x: Long):           Boolean
	def <=(x: Float):          Boolean
	def <=(x: Double):         Boolean

	def >(x: Number):          Boolean
	def >(x: IntegerNumber):   Boolean
	def >(x: RealNumber):      Boolean
	def >(x: Byte):            Boolean
	def >(x: Short):           Boolean
	def >(x: Char):            Boolean
	def >(x: Int):             Boolean
	def >(x: Long):            Boolean
	def >(x: Float):           Boolean
	def >(x: Double):          Boolean

	def >=(x: Number):         Boolean
	def >=(x: IntegerNumber):  Boolean
	def >=(x: RealNumber):     Boolean
	def >=(x: Byte):           Boolean
	def >=(x: Short):          Boolean
	def >=(x: Char):           Boolean
	def >=(x: Int):            Boolean
	def >=(x: Long):           Boolean
	def >=(x: Float):          Boolean
	def >=(x: Double):         Boolean

	def +(x: Number):          Number
	def +(x: IntegerNumber):   Number
	def +(x: RealNumber):      RealNumber
	def +(x: Byte):            Number
	def +(x: Short):           Number
	def +(x: Char):            Number
	def +(x: Int):             Number
	def +(x: Long):            Number
	def +(x: Float):           RealNumber
	def +(x: Double):          RealNumber

	def -(x: Number):          Number
	def -(x: IntegerNumber):   Number
	def -(x: RealNumber):      RealNumber
	def -(x: Byte):            Number
	def -(x: Short):           Number
	def -(x: Char):            Number
	def -(x: Int):             Number
	def -(x: Long):            Number
	def -(x: Float):           RealNumber
	def -(x: Double):          RealNumber

	def *(x: Number):          Number
	def *(x: IntegerNumber):   Number
	def *(x: RealNumber):      RealNumber
	def *(x: Byte):            Number
	def *(x: Short):           Number
	def *(x: Char):            Number
	def *(x: Int):             Number
	def *(x: Long):            Number
	def *(x: Float):           RealNumber
	def *(x: Double):          RealNumber

	def /(x: Number):          RealNumber
	def /(x: IntegerNumber):   RealNumber
	def /(x: RealNumber):      RealNumber
	def /(x: Byte):            RealNumber
	def /(x: Short):           RealNumber
	def /(x: Char):            RealNumber
	def /(x: Int):             RealNumber
	def /(x: Long):            RealNumber
	def /(x: Float):           RealNumber
	def /(x: Double):          RealNumber

	def %(x: Number):          Number
	def %(x: IntegerNumber):   Number
	def %(x: RealNumber):      Number
	def %(x: Byte):            Number
	def %(x: Short):           Number
	def %(x: Char):            Number
	def %(x: Int):             Number
	def %(x: Long):            Number
	def %(x: Float):           Number
	def %(x: Double):          Number

}

object Number
{

	override def toString = "object com.bryghts.kissnumber.Number"

	@inline def apply(v: Byte):        IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Short):       IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Char):        IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Int):         IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Long):        IntegerNumber = new IntegerNumber(v)

	@inline def apply(v: Float):       RealNumber    = new RealNumber(v)
	@inline def apply(v: Double):      RealNumber    = new RealNumber(v)

	@inline def apply(v: BigDecimal):  Number    = if(v.isWhole) new IntegerNumber(v.toLong) else new RealNumber(v.toDouble)
	@inline def apply(v: BigInt):      Number    = new IntegerNumber(v.toLong)

}

final class IntegerNumber private[kissnumber](private[kissnumber] val v: Long) extends Number
{

	@inline def unary_~ :              IntegerNumber = IntegerNumber(~v)


	@inline def <<(x: IntegerNumber):  IntegerNumber = IntegerNumber(v << x.v)
	@inline def <<(x: Int):            IntegerNumber = IntegerNumber(v << x)
	@inline def <<(x: Long):           IntegerNumber = IntegerNumber(v << x)


	@inline def >>>(x: IntegerNumber): IntegerNumber = IntegerNumber(v >>> x.v)
	@inline def >>>(x: Int):           IntegerNumber = IntegerNumber(v >>> x)
	@inline def >>>(x: Long):          IntegerNumber = IntegerNumber(v >>> x)

	@inline def >>(x: IntegerNumber):  IntegerNumber = IntegerNumber(v >> x.v)
	@inline def >>(x: Int):            IntegerNumber = IntegerNumber(v >> x)
	@inline def >>(x: Long):           IntegerNumber = IntegerNumber(v >> x)

	@inline def |(x: IntegerNumber):   IntegerNumber = IntegerNumber(v | x.v)
	@inline def |(x: Byte):            IntegerNumber = IntegerNumber(v | x)
	@inline def |(x: Short):           IntegerNumber = IntegerNumber(v | x)
	@inline def |(x: Char):            IntegerNumber = IntegerNumber(v | x)
	@inline def |(x: Int):             IntegerNumber = IntegerNumber(v | x)
	@inline def |(x: Long):            IntegerNumber = IntegerNumber(v | x)

	@inline def &(x: IntegerNumber):   IntegerNumber = IntegerNumber(v & x.v)
	@inline def &(x: Byte):            IntegerNumber = IntegerNumber(v & x)
	@inline def &(x: Short):           IntegerNumber = IntegerNumber(v & x)
	@inline def &(x: Char):            IntegerNumber = IntegerNumber(v & x)
	@inline def &(x: Int):             IntegerNumber = IntegerNumber(v & x)
	@inline def &(x: Long):            IntegerNumber = IntegerNumber(v & x)

	@inline def ^(x: IntegerNumber):   IntegerNumber = IntegerNumber(v ^ x.v)
	@inline def ^(x: Byte):            IntegerNumber = IntegerNumber(v ^ x)
	@inline def ^(x: Short):           IntegerNumber = IntegerNumber(v ^ x)
	@inline def ^(x: Char):            IntegerNumber = IntegerNumber(v ^ x)
	@inline def ^(x: Int):             IntegerNumber = IntegerNumber(v ^ x)
	@inline def ^(x: Long):            IntegerNumber = IntegerNumber(v ^ x)

	@inline def toByte:                Byte          = v.toByte
	@inline def toShort:               Short         = v.toShort
	@inline def toChar:                Char          = v.toChar
	@inline def toInt:                 Int           = v.toInt
	@inline def toLong:                Long          = v.toLong
	@inline def toFloat:               Float         = v.toFloat
	@inline def toDouble:              Double        = v.toDouble
	@inline def toInteger:             IntegerNumber = this
	@inline def toReal:                RealNumber    = RealNumber(v.toDouble)

	@inline def unary_+ :              IntegerNumber = IntegerNumber(+v)
	@inline def unary_- :              IntegerNumber = IntegerNumber(-v)

	@inline def +(x: String):          String        = v + x

	@inline def ==(x: Number):         Boolean       = x match {case x:IntegerNumber => v == x.v case x:RealNumber => v == x.v}
	@inline def ==(x: IntegerNumber):  Boolean       = v == x.v
	@inline def ==(x: RealNumber):     Boolean       = v == x.v
	@inline def ==(x: Byte):           Boolean       = v == x
	@inline def ==(x: Short):          Boolean       = v == x
	@inline def ==(x: Char):           Boolean       = v == x
	@inline def ==(x: Int):            Boolean       = v == x
	@inline def ==(x: Long):           Boolean       = v == x
	@inline def ==(x: Float):          Boolean       = v == x
	@inline def ==(x: Double):         Boolean       = v == x

	@inline def !=(x: Number):         Boolean       = x match {case x:IntegerNumber => v != x.v case x:RealNumber => v != x.v}
	@inline def !=(x: IntegerNumber):  Boolean       = v != x.v
	@inline def !=(x: RealNumber):     Boolean       = v != x.v
	@inline def !=(x: Byte):           Boolean       = v != x
	@inline def !=(x: Short):          Boolean       = v != x
	@inline def !=(x: Char):           Boolean       = v != x
	@inline def !=(x: Int):            Boolean       = v != x
	@inline def !=(x: Long):           Boolean       = v != x
	@inline def !=(x: Float):          Boolean       = v != x
	@inline def !=(x: Double):         Boolean       = v != x

	@inline def <(x: Number):          Boolean       = x match {case x:IntegerNumber => v < x.v case x:RealNumber => v < x.v}
	@inline def <(x: IntegerNumber):   Boolean       = v < x.v
	@inline def <(x: RealNumber):      Boolean       = v < x.v
	@inline def <(x: Byte):            Boolean       = v < x
	@inline def <(x: Short):           Boolean       = v < x
	@inline def <(x: Char):            Boolean       = v < x
	@inline def <(x: Int):             Boolean       = v < x
	@inline def <(x: Long):            Boolean       = v < x
	@inline def <(x: Float):           Boolean       = v < x
	@inline def <(x: Double):          Boolean       = v < x

	@inline def <=(x: Number):         Boolean       = x match {case x:IntegerNumber => v <= x.v case x:RealNumber => v <= x.v}
	@inline def <=(x: IntegerNumber):  Boolean       = v <= x.v
	@inline def <=(x: RealNumber):     Boolean       = v <= x.v
	@inline def <=(x: Byte):           Boolean       = v <= x
	@inline def <=(x: Short):          Boolean       = v <= x
	@inline def <=(x: Char):           Boolean       = v <= x
	@inline def <=(x: Int):            Boolean       = v <= x
	@inline def <=(x: Long):           Boolean       = v <= x
	@inline def <=(x: Float):          Boolean       = v <= x
	@inline def <=(x: Double):         Boolean       = v <= x

	@inline def >(x: Number):          Boolean       = x match {case x:IntegerNumber => v > x.v case x:RealNumber => v > x.v}
	@inline def >(x: IntegerNumber):   Boolean       = v > x.v
	@inline def >(x: RealNumber):      Boolean       = v > x.v
	@inline def >(x: Byte):            Boolean       = v > x
	@inline def >(x: Short):           Boolean       = v > x
	@inline def >(x: Char):            Boolean       = v > x
	@inline def >(x: Int):             Boolean       = v > x
	@inline def >(x: Long):            Boolean       = v > x
	@inline def >(x: Float):           Boolean       = v > x
	@inline def >(x: Double):          Boolean       = v > x

	@inline def >=(x: Number):         Boolean       = x match {case x:IntegerNumber => v >= x.v case x:RealNumber => v >= x.v}
	@inline def >=(x: IntegerNumber):  Boolean       = v >= x.v
	@inline def >=(x: RealNumber):     Boolean       = v >= x.v
	@inline def >=(x: Byte):           Boolean       = v >= x
	@inline def >=(x: Short):          Boolean       = v >= x
	@inline def >=(x: Char):           Boolean       = v >= x
	@inline def >=(x: Int):            Boolean       = v >= x
	@inline def >=(x: Long):           Boolean       = v >= x
	@inline def >=(x: Float):          Boolean       = v >= x
	@inline def >=(x: Double):         Boolean       = v >= x

	@inline def +(x: Number):          Number        = x match {case x:IntegerNumber => IntegerNumber(v + x.v) case x:RealNumber => RealNumber(v + x.v)}
	@inline def +(x: IntegerNumber):   IntegerNumber = IntegerNumber(v + x.v)
	@inline def +(x: RealNumber):      RealNumber    = RealNumber(v + x.v)
	@inline def +(x: Byte):            IntegerNumber = IntegerNumber(v + x)
	@inline def +(x: Short):           IntegerNumber = IntegerNumber(v + x)
	@inline def +(x: Char):            IntegerNumber = IntegerNumber(v + x)
	@inline def +(x: Int):             IntegerNumber = IntegerNumber(v + x)
	@inline def +(x: Long):            IntegerNumber = IntegerNumber(v + x)
	@inline def +(x: Float):           RealNumber    = RealNumber(v + x)
	@inline def +(x: Double):          RealNumber    = RealNumber(v + x)

	@inline def -(x: Number):          Number        = x match {case x:IntegerNumber => IntegerNumber(v - x.v) case x:RealNumber => RealNumber(v - x.v)}
	@inline def -(x: IntegerNumber):   IntegerNumber = IntegerNumber(v - x.v)
	@inline def -(x: RealNumber):      RealNumber    = RealNumber(v - x.v)
	@inline def -(x: Byte):            IntegerNumber = IntegerNumber(v - x)
	@inline def -(x: Short):           IntegerNumber = IntegerNumber(v - x)
	@inline def -(x: Char):            IntegerNumber = IntegerNumber(v - x)
	@inline def -(x: Int):             IntegerNumber = IntegerNumber(v - x)
	@inline def -(x: Long):            IntegerNumber = IntegerNumber(v - x)
	@inline def -(x: Float):           RealNumber    = RealNumber(v - x)
	@inline def -(x: Double):          RealNumber    = RealNumber(v - x)

	@inline def *(x: Number):          Number        = x match {case x:IntegerNumber => IntegerNumber(v * x.v) case x:RealNumber => RealNumber(v * x.v)}
	@inline def *(x: IntegerNumber):   IntegerNumber = IntegerNumber(v * x.v)
	@inline def *(x: RealNumber):      RealNumber    = RealNumber(v * x.v)
	@inline def *(x: Byte):            IntegerNumber = IntegerNumber(v * x)
	@inline def *(x: Short):           IntegerNumber = IntegerNumber(v * x)
	@inline def *(x: Char):            IntegerNumber = IntegerNumber(v * x)
	@inline def *(x: Int):             IntegerNumber = IntegerNumber(v * x)
	@inline def *(x: Long):            IntegerNumber = IntegerNumber(v * x)
	@inline def *(x: Float):           RealNumber    = RealNumber(v * x)
	@inline def *(x: Double):          RealNumber    = RealNumber(v * x)


	@inline def /(x: Number):          RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: IntegerNumber):   RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: RealNumber):      RealNumber    = RealNumber(v / x.v)
	@inline def /(x: Byte):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Short):           RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Char):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Int):             RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Long):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Float):           RealNumber    = RealNumber(v / x)
	@inline def /(x: Double):          RealNumber    = RealNumber(v / x)

	@inline def %(x: Number):          Number        = x match {case x:IntegerNumber => IntegerNumber(v % x.v) case x:RealNumber => RealNumber(v % x.v)}
	@inline def %(x: IntegerNumber):   IntegerNumber = IntegerNumber(v % x.v)
	@inline def %(x: RealNumber):      RealNumber    = RealNumber(v % x.v)
	@inline def %(x: Byte):            IntegerNumber = IntegerNumber(v % x)
	@inline def %(x: Short):           IntegerNumber = IntegerNumber(v % x)
	@inline def %(x: Char):            IntegerNumber = IntegerNumber(v % x)
	@inline def %(x: Int):             IntegerNumber = IntegerNumber(v % x)
	@inline def %(x: Long):            IntegerNumber = IntegerNumber(v % x)
	@inline def %(x: Float):           RealNumber    = RealNumber(v % x)
	@inline def %(x: Double):          RealNumber    = RealNumber(v % x)

	type Quot = (IntegerNumber, IntegerNumber)

	@inline def /%(x: IntegerNumber):  Quot = (IntegerNumber(v / x.v), IntegerNumber(v % x.v))
	@inline def /%(x: Byte):           Quot = (IntegerNumber(v / x),   IntegerNumber(v % x))
	@inline def /%(x: Short):          Quot = (IntegerNumber(v / x),   IntegerNumber(v % x))
	@inline def /%(x: Char):           Quot = (IntegerNumber(v / x),   IntegerNumber(v % x))
	@inline def /%(x: Int):            Quot = (IntegerNumber(v / x),   IntegerNumber(v % x))
	@inline def /%(x: Long):           Quot = (IntegerNumber(v / x),   IntegerNumber(v % x))

	override def toString(): String = v.toString
}

object IntegerNumber
{
	final val MinValue = IntegerNumber(Long.MinValue)
	final val MaxValue = IntegerNumber(Long.MaxValue)

	@inline def apply(v: Byte):  IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Short): IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Char):  IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Int):   IntegerNumber = new IntegerNumber(v)
	@inline def apply(v: Long):  IntegerNumber = new IntegerNumber(v)

	override def toString = "object com.bryghts.kissnumber.IntegerNumber"

}


final class RealNumber private[kissnumber](private[kissnumber] val v: Double) extends Number
{

	@inline def toByte:                Byte          = v.toByte
	@inline def toShort:               Short         = v.toShort
	@inline def toChar:                Char          = v.toChar
	@inline def toInt:                 Int           = v.toInt
	@inline def toLong:                Long          = v.toLong
	@inline def toFloat:               Float         = v.toFloat
	@inline def toDouble:              Double        = v.toDouble
	@inline def toInteger:             IntegerNumber = IntegerNumber(v.toLong)
	@inline def toReal:                RealNumber    = this

	@inline def unary_+ :              RealNumber    = RealNumber(+v)
	@inline def unary_- :              RealNumber    = RealNumber(-v)

	@inline def +(x: String):          String        = v + x

	@inline def ==(x: Number):         Boolean       = x match {case x:IntegerNumber => v == x.v case x:RealNumber => v == x.v}
	@inline def ==(x: IntegerNumber):  Boolean       = v == x.v
	@inline def ==(x: RealNumber):     Boolean       = v == x.v
	@inline def ==(x: Byte):           Boolean       = v == x
	@inline def ==(x: Short):          Boolean       = v == x
	@inline def ==(x: Char):           Boolean       = v == x
	@inline def ==(x: Int):            Boolean       = v == x
	@inline def ==(x: Long):           Boolean       = v == x
	@inline def ==(x: Float):          Boolean       = v == x
	@inline def ==(x: Double):         Boolean       = v == x

	@inline def !=(x: Number):         Boolean       = x match {case x:IntegerNumber => v != x.v case x:RealNumber => v != x.v}
	@inline def !=(x: IntegerNumber):  Boolean       = v != x.v
	@inline def !=(x: RealNumber):     Boolean       = v != x.v
	@inline def !=(x: Byte):           Boolean       = v != x
	@inline def !=(x: Short):          Boolean       = v != x
	@inline def !=(x: Char):           Boolean       = v != x
	@inline def !=(x: Int):            Boolean       = v != x
	@inline def !=(x: Long):           Boolean       = v != x
	@inline def !=(x: Float):          Boolean       = v != x
	@inline def !=(x: Double):         Boolean       = v != x

	@inline def <(x: Number):          Boolean       = x match {case x:IntegerNumber => v < x.v case x:RealNumber => v < x.v}
	@inline def <(x: IntegerNumber):   Boolean       = v < x.v
	@inline def <(x: RealNumber):      Boolean       = v < x.v
	@inline def <(x: Byte):            Boolean       = v < x
	@inline def <(x: Short):           Boolean       = v < x
	@inline def <(x: Char):            Boolean       = v < x
	@inline def <(x: Int):             Boolean       = v < x
	@inline def <(x: Long):            Boolean       = v < x
	@inline def <(x: Float):           Boolean       = v < x
	@inline def <(x: Double):          Boolean       = v < x

	@inline def <=(x: Number):         Boolean       = x match {case x:IntegerNumber => v <= x.v case x:RealNumber => v <= x.v}
	@inline def <=(x: IntegerNumber):  Boolean       = v <= x.v
	@inline def <=(x: RealNumber):     Boolean       = v <= x.v
	@inline def <=(x: Byte):           Boolean       = v <= x
	@inline def <=(x: Short):          Boolean       = v <= x
	@inline def <=(x: Char):           Boolean       = v <= x
	@inline def <=(x: Int):            Boolean       = v <= x
	@inline def <=(x: Long):           Boolean       = v <= x
	@inline def <=(x: Float):          Boolean       = v <= x
	@inline def <=(x: Double):         Boolean       = v <= x

	@inline def >(x: Number):          Boolean       = x match {case x:IntegerNumber => v > x.v case x:RealNumber => v > x.v}
	@inline def >(x: IntegerNumber):   Boolean       = v > x.v
	@inline def >(x: RealNumber):      Boolean       = v > x.v
	@inline def >(x: Byte):            Boolean       = v > x
	@inline def >(x: Short):           Boolean       = v > x
	@inline def >(x: Char):            Boolean       = v > x
	@inline def >(x: Int):             Boolean       = v > x
	@inline def >(x: Long):            Boolean       = v > x
	@inline def >(x: Float):           Boolean       = v > x
	@inline def >(x: Double):          Boolean       = v > x

	@inline def >=(x: Number):         Boolean       = x match {case x:IntegerNumber => v >= x.v case x:RealNumber => v >= x.v}
	@inline def >=(x: IntegerNumber):  Boolean       = v >= x.v
	@inline def >=(x: RealNumber):     Boolean       = v >= x.v
	@inline def >=(x: Byte):           Boolean       = v >= x
	@inline def >=(x: Short):          Boolean       = v >= x
	@inline def >=(x: Char):           Boolean       = v >= x
	@inline def >=(x: Int):            Boolean       = v >= x
	@inline def >=(x: Long):           Boolean       = v >= x
	@inline def >=(x: Float):          Boolean       = v >= x
	@inline def >=(x: Double):         Boolean       = v >= x

	@inline def +(x: Number):          RealNumber    = x match {case x:IntegerNumber => RealNumber(v + x.v) case x:RealNumber => RealNumber(v + x.v)}
	@inline def +(x: RealNumber):      RealNumber    = RealNumber(v + x.v)
	@inline def +(x: IntegerNumber):   RealNumber    = RealNumber(v + x.v)
	@inline def +(x: Byte):            RealNumber    = RealNumber(v + x)
	@inline def +(x: Short):           RealNumber    = RealNumber(v + x)
	@inline def +(x: Char):            RealNumber    = RealNumber(v + x)
	@inline def +(x: Int):             RealNumber    = RealNumber(v + x)
	@inline def +(x: Long):            RealNumber    = RealNumber(v + x)
	@inline def +(x: Float):           RealNumber    = RealNumber(v + x)
	@inline def +(x: Double):          RealNumber    = RealNumber(v + x)

	@inline def -(x: Number):          RealNumber    = x match {case x:IntegerNumber => RealNumber(v - x.v) case x:RealNumber => RealNumber(v - x.v)}
	@inline def -(x: RealNumber):      RealNumber    = RealNumber(v - x.v)
	@inline def -(x: IntegerNumber):   RealNumber    = RealNumber(v - x.v)
	@inline def -(x: Byte):            RealNumber    = RealNumber(v - x)
	@inline def -(x: Short):           RealNumber    = RealNumber(v - x)
	@inline def -(x: Char):            RealNumber    = RealNumber(v - x)
	@inline def -(x: Int):             RealNumber    = RealNumber(v - x)
	@inline def -(x: Long):            RealNumber    = RealNumber(v - x)
	@inline def -(x: Float):           RealNumber    = RealNumber(v - x)
	@inline def -(x: Double):          RealNumber    = RealNumber(v - x)

	@inline def *(x: Number):          RealNumber    = x match {case x:IntegerNumber => RealNumber(v * x.v) case x:RealNumber => RealNumber(v * x.v)}
	@inline def *(x: RealNumber):      RealNumber    = RealNumber(v * x.v)
	@inline def *(x: IntegerNumber):   RealNumber    = RealNumber(v * x.v)
	@inline def *(x: Byte):            RealNumber    = RealNumber(v * x)
	@inline def *(x: Short):           RealNumber    = RealNumber(v * x)
	@inline def *(x: Char):            RealNumber    = RealNumber(v * x)
	@inline def *(x: Int):             RealNumber    = RealNumber(v * x)
	@inline def *(x: Long):            RealNumber    = RealNumber(v * x)
	@inline def *(x: Float):           RealNumber    = RealNumber(v * x)
	@inline def *(x: Double):          RealNumber    = RealNumber(v * x)

	@inline def /(x: Number):          RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: IntegerNumber):   RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: RealNumber):      RealNumber    = RealNumber(v / x.v)
	@inline def /(x: Byte):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Short):           RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Char):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Int):             RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Long):            RealNumber    = RealNumber(v / x.toDouble)
	@inline def /(x: Float):           RealNumber    = RealNumber(v / x)
	@inline def /(x: Double):          RealNumber    = RealNumber(v / x)

	@inline def %(x: Number):          RealNumber    = x match {case x:IntegerNumber => RealNumber(v % x.v) case x:RealNumber => RealNumber(v % x.v)}
	@inline def %(x: IntegerNumber):   RealNumber    = RealNumber(v % x.v)
	@inline def %(x: RealNumber):      RealNumber    = RealNumber(v % x.v)
	@inline def %(x: Byte):            RealNumber    = RealNumber(v % x)
	@inline def %(x: Short):           RealNumber    = RealNumber(v % x)
	@inline def %(x: Char):            RealNumber    = RealNumber(v % x)
	@inline def %(x: Int):             RealNumber    = RealNumber(v % x)
	@inline def %(x: Long):            RealNumber    = RealNumber(v % x)
	@inline def %(x: Float):           RealNumber    = RealNumber(v % x)
	@inline def %(x: Double):          RealNumber    = RealNumber(v % x)

	override def toString(): String = v.toString
}

object RealNumber
{
	val MinPositiveValue = RealNumber(Double.MinPositiveValue)
	val NaN              = RealNumber(Double.NaN)
	val PositiveInfinity = RealNumber(Double.PositiveInfinity)
	val NegativeInfinity = RealNumber(Double.NegativeInfinity)

	val MinValue         = RealNumber(Double.MinValue)
	val MaxValue         = RealNumber(Double.MaxValue)

	@inline def apply(v: Float):  RealNumber = new RealNumber(v)
	@inline def apply(v: Double): RealNumber = new RealNumber(v)

	override def toString = "object com.bryghts.kissnumber.RealNumber"
}

