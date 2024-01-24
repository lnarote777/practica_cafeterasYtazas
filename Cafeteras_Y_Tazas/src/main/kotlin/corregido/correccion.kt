package corregido

import kotlin.random.Random

class Cafetera(val ubicacion: String){
    var capacidad : Int = 1000
    var cantidad : Int = 0

    constructor(ubicacion: String, capacidad: Int): this(ubicacion){
        this.capacidad = capacidad
        this.cantidad = capacidad
    }

    /**
     * Constructor secundario que permite especificar la capacidad máxima de la cafetera al crearla.
     * @param ubicacion
     * @param
     */
    constructor(ubicacion: String, capacidad: Int, cantidad: Int): this(ubicacion){
        this.capacidad = capacidad
        this.cantidad = if (cantidad > capacidad) capacidad else cantidad
    }

    /**
     * Llena la cafetera estableciendo la cantidad actual igual a la capacidad
     */
    fun llenar(){
        this.cantidad = this.capacidad
    }

    /**
     * Simula la accion de servir una cantidad específica de cafe de la cafetera.
     * Resta la cantiad servida de la cantidad actual de cafe en la cafetera.
     * @param cantidad Cantidad de cafe a servir
     */
    fun servirTaza(taza : Taza){
        if(this.cantidad > 0){
            if(this.cantidad > taza.capacidad){
                taza.llenar()
                servircafe(taza.capacidad)
            }else{
                taza.llenar(this.cantidad)
                vaciar()
            }
        }
    }

    fun servircafe(cantidad: Int){
        this.cantidad -= cantidad
    }
    /**
     * Pone la cantidad de la cafetera a cero
     */
    fun vaciar(){
        this.cantidad = 0
    }

    /**
     * Añade a la cafetera la cantidad de cafe indicada. Por defecto, añade 200c.c.
     * Si la cantidad sumada es igual a la capacidad máxima o excede la capacidad, llena la cafetera.
     * @param cantidad Cantidad de cafe a agregar (por defecto 200c.c)
     */
    fun agregarcafe(cantidad: Int = 200){
        if (this.cantidad + cantidad > this.capacidad){
            llenar()
        }
        else{
            this.cantidad += cantidad
        }
    }

    /**
     * Representación textual de la cafetera en formato cadena.
     * @return Cadena que describe la cafetera con su ubicación, capacidad y cantidad.
     */
    override fun toString(): String {
        return "Cafetera(ubicación = ${this.ubicacion}, capacidad = ${this.capacidad}c.c., cantidad = ${this.cantidad} )"
    }

}


class Taza(val color: Color = Color.BLANCO, val capacidad: Int = 50){
    var cantidad = 0
        set(value) {
            field = if (value > this.capacidad) this.capacidad else value
        }

    /**
     * Llena la taza estableciendo la cantidad igual a su capacidad máxima.
     */
    fun llenar(){
        this.cantidad = this.capacidad
    }

    /**
     * Llena la taza estableciendo la cantidad segun el valor pasado como argumento
     * @param cantidad Cantidad de liquido con la que se llenara la taza
     */
    fun llenar(cantidad :Int){
        this.cantidad = cantidad
    }

    /**
     * Representación textual de la cafetera en formato cadena.
     * @return Cadena que describe la cafetera con su ubicación, capacidad y cantidad.
     */
    override fun toString(): String {
        return "taza(color = ${this.color}, capacidad = ${this.capacidad}c.c., cantidad = ${this.cantidad} )"
    }

}

enum class Color {
    BLANCO, NEGRO, AZUL, VERDE, GRIS
}



class gestionCafe{
    companion object{
        /**
         * Crea y devuelve una lista de tazas( por defecto 20) con capacidades aleatorias (50, 75, 100)
         * Los colores de las tazas son seleccionados aleatoriamente entre los colores disponibles
         * @return Lista de tazas generadas
         */
        fun crearListaTazas(num : Int = 20): List<Taza>{
            return mutableListOf<Taza>().apply {
                for (i in 1..num){
                    val capacidadAleatoria = when (Random.nextInt(3)){
                        0 -> 50
                        1 -> 75
                        else -> 100
                    }
                    val taza = Taza(Color.entries.toTypedArray().random(), capacidadAleatoria)
                    add(taza)
                }
            }.toList()
        }

        /**
         * Simula la acción de servir cada taza en la lista utilizando
         * Las tazas se sirven una por una , y se utiliza la primera cafetera disponible.
         * @param tazas Lista de tazas a servir
         * @param cafeteras
         */
        fun servirTazas(tazas: List<Taza>, cafeteras: List<Cafetera>){
            for (taza in tazas){
                for (cafetera in cafeteras){
                    if (cafetera.cantidad > 0)
                        cafetera.servirTaza(taza)
                    //Si se utilizó una cafetera, salimos del bucle for porque ya sirvió
                    break
                }
            }
        }

        /**
         * Muestra la información de las tazas y las cafeteras proporcionadas.
         * @param tazas lista de tazas a mostrar
         * @param cafeteras lista de cafeteras a mostrar
         */
        fun mostrarInfo(tazas: List<Taza>, cafeteras: List<Cafetera>) {
            mostrarCafeteras(cafeteras)
            mostrarTazas(tazas)
        }
        private fun mostrarTazas(tazas: List<Taza>){
            for(taza in tazas){
                println(taza)
            }
        }

        fun mostrarCafeteras(cafeteras: List<Cafetera>){
            for (cafetera in cafeteras){
                println(cafetera)
            }
        }
    }
}
fun main(){
    val cafetera1 = Cafetera("Sala" )
    val cafetera2 = Cafetera("Cocina", 750)
    val cafetera3 = Cafetera("Oficina", 500, 200 )

    val cafeteras = listOf(cafetera1, cafetera2, cafetera3)

    val tazas = gestionCafe.crearListaTazas()

    gestionCafe.mostrarInfo(tazas, cafeteras)

    cafetera1.llenar()

    cafetera2.vaciar()

    cafetera2.agregarcafe(cafetera2.capacidad / 2)

    cafetera3.agregarcafe(400)

    gestionCafe.mostrarInfo(tazas, cafeteras)
}