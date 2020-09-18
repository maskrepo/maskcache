package fr.convergence.proddoc.libs.service

import fr.convergence.proddoc.model.table.Parametre
import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ServiceDeMiseEnCacheDesParametres {

    private var mapQuiContientLesParametres = ConcurrentHashMap<String, Parametre>()

    fun ajoutOuMiseAJourParametre(parametre: Parametre) = mapQuiContientLesParametres.put(computeKey(parametre), parametre)
    private fun computeKey(parametre: Parametre): String = "${parametre.code_domaine}_${parametre.code_sous_domaine}_${parametre.cle}_${parametre.chrono}"

    fun getParameter(domaine: String, sousDomaine: String, cle: String, chrono: String): Parametre = mapQuiContientLesParametres.get(computeKey(domaine, sousDomaine, cle, chrono))!!
    private fun computeKey(domaine: String, sousDomaine: String, cle: String, chrono: String): String = "${domaine}_${sousDomaine}_${cle}_${chrono}"

    fun getAll(): MutableCollection<Parametre> = mapQuiContientLesParametres.values
}