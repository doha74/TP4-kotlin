package com.example.tp4


// class produit
open class Produit(var code:Int, var prix:Double){
    constructor(code: Int):this(code,0.0)
    open fun prixProduit(): Double{
        return prix
    }
    override fun toString():String{
        return "$code ; $prix"
    }
    override fun equals(other: Any?): Boolean {
        if (other is Produit) {
            return this.code == other.code
        }
        return false
    }

    // class produits en solde
    class ProduitEnSolde(code: Int, prix: Double, var remise:Int):Produit(code,prix){
       init {
           if (remise !in 1..90){throw IllegalArgumentException("la remise doit etre entre 1 et 90")}
       }
        override fun prixProduit(): Double {
            return super.prixProduit()*(1-remise/100)
        }
        override fun toString(): String {
            return super.toString()+"; $remise"
        }
    }
}

class Boutique{
    private val listeProduits = mutableListOf<Produit>()
    fun indiceDe(code: Int): Int{
        return listeProduits.indexOfFirst {it.code == code}
    }
    fun supprimer(code: Int): Boolean{
        val index = indiceDe(code)
        if (index == -1){return false}
        listeProduits.removeAt(index)
        return true
    }
    fun ajouter(p: Produit){
        if (p in listeProduits){
            println("le produit deja existe")
        }
        listeProduits.add(p)
    }
    fun supprimer(p: Produit) : Boolean{
        if (p !in listeProduits){return false}
        listeProduits.remove(p)
        return true
    }
    fun nombreProduitsEnSolde(): Int {
        return listeProduits.count { it is Produit.ProduitEnSolde }
    }
}

fun main() {
    val boutique = Boutique()

    val produit1 = Produit(1, 100.0)
    val produit2 = Produit.ProduitEnSolde(2, 200.0, 50)
    val produit3 = Produit(1, 200.0)
    val produit4 = Produit.ProduitEnSolde(3, 200.0, 50)
    val produit5 = Produit(4, 100.0)
    val produit6 = Produit.ProduitEnSolde(5, 200.0, 50)

    boutique.ajouter(produit1)
    boutique.ajouter(produit2)
    boutique.ajouter(produit3)
    boutique.ajouter(produit4)
    boutique.ajouter(produit5)
    boutique.ajouter(produit6)

    println(boutique.indiceDe(4))
    println(boutique.indiceDe(10))
    println(boutique.supprimer(4))
    println(boutique.supprimer(produit2))

    println(boutique.nombreProduitsEnSolde())
}