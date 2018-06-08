package math

data class Complex( val real: Double, val imag: Double ){

    constructor( real: Double ) : this( real, 0.0 )
    constructor() : this( 0.0, 0.0 )

    override fun equals( other: Any? ): Boolean{
        when ( other ) {
            is Complex -> if ( this.real == other.real && this.imag == other.imag ) return true
            is Double -> if ( this.real == other && 0.0 == this.imag ) return true
        }
        return false
    }
    override fun toString(): String {
        if ( 0 > this.imag ) return "${this.real}${this.imag}i" else return "${this.real}+${this.imag}i"
    }
    operator fun plus( other: Complex ): Complex{
        return Complex( this.real + other.real, this.imag + other.imag )
    }
    operator fun plus( other: Double ): Complex{
        return Complex( this.real + other, this.imag )
    }
    operator fun minus( other: Complex ): Complex{
        return Complex( this.real - other.real, this.imag - other.imag )
    }
    operator fun minus( other: Double ): Complex{
        return Complex( this.real - other, this.imag )
    }
    operator fun times( other: Complex ): Complex{
        // c1 * c2 = ( c1.r * c2.r - c1.i * c2.i ) + ( c1.r * c2.i + c1.i * c2.r )
        return Complex( this.real * other.real - this.imag * other.imag, this.real * other.imag + this.imag * other.real )
    }
    operator fun times( other: Double ): Complex{
        return this.times( Complex( other ) )
    }
    operator fun div( other: Complex ): Complex{
        /*           c1.r * c2.r + c1.i * c2.i   c1.i * c2.r - c1.r * c2.i
         * c1 / c2 = ------------------------- + -------------------------
         *              c2.r²    +    c2.i²         c2.r²    +    c2.i²
         */
        return Complex( ( this.real * other.real + this.imag * other.imag ) / ( other.real * other.real + other.imag * other.imag ), ( this.imag * other.real - this.real * other.imag ) / ( other.real * other.real + other.imag * other.imag ) )
    }
    operator fun div( other: Double ): Complex{
        return this.div( Complex( other ) )
    }
}