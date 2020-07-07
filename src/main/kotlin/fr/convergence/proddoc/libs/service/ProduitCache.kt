package fr.convergence.proddoc.libs.service

import fr.convergence.proddoc.libs.model.Parametre
import fr.convergence.proddoc.libs.model.Produit
import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProduitCache {

    private var map = ConcurrentHashMap<String, Produit>()

    fun addParameter(produit: Produit) = map.put(computeKey(produit), produit)
    private fun computeKey(produit: Produit): String = "${produit.code_domaine}_${produit.code_sous_domaine}_${produit.cle}_${produit.chrono}"

    fun getParameter(domaine: String, sousDomaine: String, cle: String, chrono: String): Produit = map.get(computeKey(domaine, sousDomaine, cle, chrono))!!
    private fun computeKey(domaine: String, sousDomaine: String, cle: String, chrono: String): String = "${domaine}_${sousDomaine}_${cle}_${chrono}"

    fun getAll() : MutableCollection<Produit> = map.values
}
