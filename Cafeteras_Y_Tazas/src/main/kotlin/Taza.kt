class Taza(private var color: Color = Color.BLANCO, private var capacidad: Int = 50, private var cantidad: Int = 0){

    fun getColor(): Color {
        return this.color
    }
    fun setColor(color: Color){
        this.color = color
    }
    init {
        if (this.capacidad < this.cantidad){
            this.cantidad = this.capacidad
        }
    }
    fun llenar(){
        this.cantidad = this.capacidad
    }
//    fun llenar(Int){
//
//    }

    override fun toString(): String {
        return "Taza(color = ${this.color}, capacidad = ${this.capacidad}c.c., cantidad = ${this.cantidad} )"
    }

}