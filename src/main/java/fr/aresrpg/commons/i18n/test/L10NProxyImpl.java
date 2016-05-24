package fr.aresrpg.commons.i18n.test;

public class L10NProxyImpl implements L10NProxy{
	@Override
	public String ban1(Object... args) {
		return " §6Vous êtes banni §9"+args[0]+"§7 :\n§e"+args[1];
	}

	@Override
	public String wdlDisconnect(Object... args) {
		return null;
	}

	@Override
	public String ressourcePckReqUnallowSrv(Object... args) {
		return null;
	}

	@Override
	public String maintenance(Object... args) {
		return null;
	}
}
