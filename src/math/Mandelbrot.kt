package math

object Options{
    val MAX_X          = 1.5
    val MIN_X          = -2.5
    val MAX_Y          = 1.5
    val MIN_Y          = -1.5
    val BREAKPOINT     = 2.0
    val MAX_ITERATIONS = 125
}

tailrec fun isInMandelbrot( c: Complex, z: Complex = Complex( 0.0 ), currentIteration: Int = 0 ): Boolean{
    if ( z.real > Options.BREAKPOINT ){
        return false
    } else if ( currentIteration > Options.MAX_ITERATIONS  ) {
        return true
    } else {
        return isInMandelbrot( c, z * z + c, currentIteration + 1 )
    }
}

tailrec fun obtainMandelbrotDepth( c: Complex, z: Complex = Complex( 0.0 ), currentIteration: Int = 0 ): Int{
    if ( z.real > Options.BREAKPOINT || currentIteration >= Options.MAX_ITERATIONS ){
        return currentIteration
    } else {
        return obtainMandelbrotDepth( c, z * z + c, currentIteration + 1 )
    }
}

class Mandelbrot( var pixelsX: Int, var pixelsY: Int ){
    var stepsizeX: Double = ( Options.MAX_X + Math.abs( Options.MIN_X ) ) / this.pixelsX
    var stepsizeY: Double = ( Options.MAX_Y + Math.abs( Options.MIN_Y ) ) / this.pixelsY
    var values: ByteArray = ByteArray( this.pixelsX * this.pixelsY )
    init{
        this.fillCalculateValues( 0, Options.MIN_X, Options.MIN_Y )
    }

    tailrec fun fillCalculateValues( index: Int, currentX: Double, currentY: Double ){
        if ( index >= this.values.size ){
            return
        }
        if ( 0 == ( index + 1 ) % this.pixelsX ){
            this.values[index] = obtainMandelbrotDepth( Complex( Options.MIN_X, currentY + this.stepsizeY ) ).toByte()
            return this.fillCalculateValues( index + 1, Options.MIN_X + this.stepsizeX, currentY + this.stepsizeY )
        } else {
            this.values[index] = obtainMandelbrotDepth( Complex( currentX, currentY ) ).toByte()
            return this.fillCalculateValues( index + 1, currentX + this.stepsizeX, currentY )
        }
    }

    fun getPixelValue( x: Int, y: Int ): Byte{
        return this.values[x + this.pixelsX * y]
    }

    fun debugPrintValues(){
        for( index in this.values.indices ){
            if ( 0 == ( index + 1 ) % this.pixelsX ){
                println()
            }
            print( "${this.values[index]} ")
        }
    }
}

fun consolePrintMandelbrot( charsX: Int, charsY: Int ){
    var currentX  = Options.MIN_X
    var currentY  = Options.MIN_Y
    val stepsizeX = ( Options.MAX_X + Math.abs( Options.MIN_X ) ) / charsX
    val stepsizeY = ( Options.MAX_Y + Math.abs( Options.MIN_Y ) ) / charsY

    for ( y in -( charsY / 2 )..charsY / 2 ){
        for( x in -( charsX / 2 )..charsX / 2 ){
            when ( obtainMandelbrotDepth( Complex( currentX, currentY ) ) ) {
                in 9..Options.MAX_ITERATIONS -> print( "X" )
                else -> print( " " )
            }
            currentX += stepsizeX
        }
        currentX = Options.MIN_X
        currentY += stepsizeY
        println()
    }
}

/*
fun main ( args: Array<String> ){
    //consolePrintMandelbrot( 200, 80 )
    var m = Mandelbrot( 100, 30 )
    m.debugPrintValues()
}*/