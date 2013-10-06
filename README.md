KissNumber
==========

KissNumber is an alternative boxing for numeric variables in Scala.

What you need to know about KissNumber:

- `Number` is the parent type for both integer and floating point values.
- An `IntegerNumber` holds an integer value (it wraps a `Long`)
- A `RealNumber` holds a floating point value (it wraps a `Double`)
- All the operations that can be performed in a `Double` can be performed in a `RealNumber` (including the existence of an implicit `RichRealNumber`)
- All the operations that can be performed in a `Long` can be performed in a `IntegerNumber` (including the existence of an implicit `RichIntegerNumber`) except ...
    - the '/' operator performs a floating point division and ...
    - a '/%' operator is provided instead, which returns a Pair containing the integer quotient and the modulus
- All the operations that `IntegerNumber` and `RealNumber` have in common can be performed directly on a `Number` (including the existence of an implicit `RichNumber`). Hence that `IntegerNumber` performs floating point divisions.
- Implicit bidirectional conversions between standard Scala values (`Int`, `Float`, `Double`, ...) and the adequate `Number` types are provided


## Examples

```scala
import com.bryghts.kissnumber._

object Examples extends App
{

    val n: Number = 3

    def foo(x: Number, y: Number, z: Number): Number = x + y / z

    val a: IntegerNumber = 1
    val b: RealNumber    = 2.3
    val c: Number        = 67

    val d = foo(a, b, c)

    println(d)

    val l: List[Number] = List(1, 4.5, 2, 3, -2)

    println(l.map{_ * 2}.mkString(","))

    println(l.sorted.mkString(","))

}
```

## Get it

Maven Dependency:

```xml
<dependency>
  <groupId>com.bryghts.kissnumber</groupId>
  <artifactId>kissnumber_2.10</artifactId>
  <version>0.0.3</version>
</dependency>
```

Sbt Dependency:

```scala
libraryDependencies += "com.bryghts.kissnumber" % "kissnumber_2.10" % "0.0.3"
```
