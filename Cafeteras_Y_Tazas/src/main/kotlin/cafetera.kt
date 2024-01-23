class Cafetera(var ubicacion: String){

    var capacidad: Int = 1000 //Capacidad máxima de la cafetera
    var cantidad : Int = 0 //Cantidad actual en la cafetera

    constructor(ubicacion: String, capacidad: Int): this(ubicacion){
        this.cantidad = capacidad
    }
    constructor(ubicacion: String, capacidad: Int, cantidad: Int ) : this(ubicacion){
        this.capacidad = capacidad
        if(cantidad > capacidad ){
            this.cantidad = this.capacidad
        }else{
            this.cantidad = cantidad
        }
    }

    fun llenar(){
        this.cantidad = this.capacidad
    }
    fun servirTaza(){

    }
    fun vaciar(): Int {
        this.cantidad = 0
        return this.cantidad
    }
    fun agregarCafe(cc : Int){
        cc = 200

    }

    override fun toString(): String {
        return "Cafetera(ubicación = ${this.ubicacion}, capacidad = ${this.capacidad}c.c., cantidad = ${this.cantidad} )"
    }

}
