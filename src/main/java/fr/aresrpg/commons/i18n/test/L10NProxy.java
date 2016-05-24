package fr.aresrpg.commons.i18n.test;

import fr.aresrpg.commons.i18n.L10N;
import fr.aresrpg.commons.i18n.annotation.lang.En;
import fr.aresrpg.commons.i18n.annotation.lang.Fr;

public interface L10NProxy extends L10N {

	@Fr(" §6Vous êtes banni §9{0}§7 :\n§e{1}")
	@En(" §6Vous êtes banni §9{0}§7 :\n§e{1}")
	String ban1(Object... args);

	@Fr(" §eL'utilisation d'un §6WorldDownloader§e n'est pas autorisée")
	String wdlDisconnect(Object... args);

	@Fr("§cVeuillez attendre la reception du §6ressourcePack§c !")
	String ressourcePckReqUnallowSrv(Object... args);

	@Fr("§cMaintenance ! §eQue la force soit avec notre équipe d'§5Oompa Loompa")
	String maintenance(Object... args);
}


