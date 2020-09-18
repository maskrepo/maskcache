package fr.convergence.proddoc.libs.service

import fr.convergence.proddoc.model.table.Parametre
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory.getLogger
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class GestionnaireEvenementCacheDesParametres(@Inject var serviceDeMiseEnCacheDesParametres: ServiceDeMiseEnCacheDesParametres) {

    companion object {
        private val LOG: Logger = getLogger(Parametre::class.java)
    }

    @Incoming("parametre")
    @Outgoing("cache_status")
    fun receptionEvenementMiseAJourDunParametreDepuisMyGreffe(parametre: Parametre): String {
        try {
            LOG.debug("Réception du paramètre : $parametre");
            serviceDeMiseEnCacheDesParametres.ajoutOuMiseAJourParametre(parametre)
            LOG.debug("MAJ cache OK");
            return ("CACHE_MAJ_OK : $parametre")
        } catch (ex: Exception) {
            LOG.error("Problème de mise à jour du cache de paramètres", ex)
            return ("CACHE_MAJ_KO : $parametre => ${ex.message}")
        }
    }
}