package fr.convergence.proddoc.libs.service

import fr.convergence.proddoc.model.table.Parametre
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory.getLogger
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class CacheUtils (@Inject var cache :ParametreCache){

    companion object {
        private val LOG: Logger = getLogger(Parametre::class.java)
    }

    @Incoming("toto")
    fun toto(strtoto :String) {
        LOG.info("toto : $strtoto")
    }

    @Incoming("parametre")
    @Outgoing("cache_status")
    fun ecoute(parametre: Parametre) :String {
        try {
            LOG.debug("Réception du paramètre : $parametre");
            cache.addParameter(parametre)
            LOG.debug("MAJ cache OK");
            return ("CACHE_MAJ_OK")
        }
        catch (e :Exception)
        {
            LOG.error("Problème de mise à jour du cache de paramètres", e)
            return ("CACHE_MAJ_KO")
        }
    }
}