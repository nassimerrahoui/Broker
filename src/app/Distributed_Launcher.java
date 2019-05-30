package app;

import fr.sorbonne_u.components.cvm.utils.DCVM_Launcher;

/**
 * 
 * Classe permettant de lancer le DCVM_Launcher de BCM
 * en paramettrant le path vers le workspace du projet et le seperateur utilise dans
 * certaines commandes Unix de DCVM_Launcher
 * 
 * DCVM_Launcher permet de lancer la classe DistributedCVM avec les prerequis necessaire
 * comme le registre et la barriere cyclique.
 *
 */
public class Distributed_Launcher {

	public static void main(String[] args) {
		assert args != null && args.length >= 1;

		try {
			// le premier parametre est le path vers le config.xml du projet
			// le second parametre est le path vers le workspace du projet
			// le ; est le separateur pour les commandes sous Windows
			// le : est le separateur pour les commandes sous Linux
			DCVM_Launcher launcher = new DCVM_Launcher(args[0],"/Users/Grey/workspace",";");
			launcher.launch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
