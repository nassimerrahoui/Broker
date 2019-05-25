package fr.sorbonne_u.components.cvm.utils;

import java.io.BufferedReader;

//Copyright Jacques Malenfant, Sorbonne Universite.
//
//Jacques.Malenfant@lip6.fr
//
//This software is a computer program whose purpose is to provide a
//basic component programming model to program with components
//distributed applications in the Java programming language.
//
//This software is governed by the CeCILL-C license under French law and
//abiding by the rules of distribution of free software.  You can use,
//modify and/ or redistribute the software under the terms of the
//CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
//URL "http://www.cecill.info".
//
//As a counterpart to the access to the source code and  rights to copy,
//modify and redistribute granted by the license, users are provided only
//with a limited warranty  and the software's author,  the holder of the
//economic rights,  and the successive licensors  have only  limited
//liability. 
//
//In this respect, the user's attention is drawn to the risks associated
//with loading,  using,  modifying and/or developing or reproducing the
//software by the user in light of its specific status of free software,
//that may mean  that it is complicated to manipulate,  and  that  also
//therefore means  that it is reserved for developers  and  experienced
//professionals having in-depth computer knowledge. Users are therefore
//encouraged to load and test the software's suitability as regards their
//requirements in conditions enabling the security of their systems and/or 
//data to be ensured and,  more generally, to use and operate it in the 
//same conditions as regards security. 
//
//The fact that you are presently reading this means that you have had
//knowledge of the CeCILL-C license and that you accept its terms.

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import fr.sorbonne_u.components.cvm.config.ConfigurationFileParser;
import fr.sorbonne_u.components.cvm.config.ConfigurationParameters;

//-----------------------------------------------------------------------------
/**
 * The class <code>DCVM_Launcher</code> is used to execute BCM applications in
 * multi-JVM but mono-host mode.
 *
 * <p>
 * <strong>Description</strong>
 * </p>
 * 
 * The class uses the <code>ProcessBuilder</code> framework of Java to execute
 * the BCM application. For the time being, this class only works on a single
 * host running under a Unix operating system. Simply execute the class
 * providing it the name of the configuration file of the application as a
 * command line parameter.
 * 
 * <p>
 * <strong>Invariant</strong>
 * </p>
 * 
 * <pre>
 * invariant		true
 * </pre>
 * 
 * <p>
 * Created on : 2018-08-28
 * </p>
 * 
 * @author <a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 */
public class DCVM_Launcher {
	// ------------------------------------------------------------------------
	// Deployment information
	// ------------------------------------------------------------------------

	/** Debug mode flag. */
	public static boolean DEBUG = true;
	/** parameters obtained form the xml configuration file. */
	protected ConfigurationParameters configurationParameters;

	// ------------------------------------------------------------------------
	// Constructor
	// ------------------------------------------------------------------------

	/**
	 * create a launcher with the given configuration file name.
	 * 
	 * <p>
	 * <strong>Contract</strong>
	 * </p>
	 * 
	 * <pre>
	 * pre	configFileName != null
	 * post	true			// no postcondition.
	 * </pre>
	 * 
	 * @throws Exception
	 *             <i>todo.</i>
	 *
	 * @param configFileName
	 *            the name of the configuration file.
	 * @throws Exception
	 *             <i>todo.</i>
	 */
	public DCVM_Launcher(String configFileName) throws Exception {
		assert configFileName != null;

		File configFile = new File(configFileName);
		ConfigurationFileParser cfp = new ConfigurationFileParser();
		if (!cfp.validateConfigurationFile(configFile)) {
			throw new Exception("invalid configuration file " + configFileName);
		}
		this.configurationParameters = cfp.parseConfigurationFile(configFile);

		if (DEBUG) {
			System.out.println(this.configurationParameters.toString());
		}
	}

	// ------------------------------------------------------------------------
	// Methods
	// ------------------------------------------------------------------------

	/**
	 * launch the application using different Unix processes.
	 * 
	 * <p>
	 * <strong>Contract</strong>
	 * </p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @throws Exception
	 *             <i>todo.</i>
	 */
	public void launch() throws Exception {
		assert this.configurationParameters != null;

		String[] jvmURIs = this.configurationParameters.getJvms();

		Hashtable<String, String> jvms2hosts = this.configurationParameters.getJvms2hosts();
		Hashtable<String, String> hosts2dirs = this.configurationParameters.getHosts2dirs();
		Hashtable<String, String> jvms2mainclasses = this.configurationParameters.getJvms2mainclasses();

		Set<String> reflectiveJVMs = this.configurationParameters.getReflectiveJVMs();

		List<String> commandRegistry = new ArrayList<String>();
		String globalRegistryHostname = this.configurationParameters.getGlobalRegistryHostname();
		commandRegistry.add("java");
		commandRegistry.add("-ea");
		commandRegistry.add("-Xms2m");
		commandRegistry.add("-cp");
		commandRegistry.add(
				"/Users/Grey/workspace/broker/target/classes;/Users/Grey/workspace/broker/resources/jing.jar;/Users/Grey/workspace/broker/resources/jcip-annotations.jar;/Users/Grey/workspace/broker/resources/javassist.jar");
		commandRegistry.add("-Djava.security.manager");
		commandRegistry.add("-Djava.security.policy=/Users/Grey/workspace/broker/src/app/dcvm.policy");
		commandRegistry.add("fr.sorbonne_u.components.registry.GlobalRegistry");
		commandRegistry.add("/Users/Grey/workspace/broker/src/app/config.xml");
		ProcessBuilder pbRegistry = new ProcessBuilder(commandRegistry);
		pbRegistry.directory(new File(hosts2dirs.get(globalRegistryHostname)));

		Process pRegistry = pbRegistry.start();

		// try (final BufferedReader b = new BufferedReader(new
		// InputStreamReader(pRegistry.getErrorStream()))) {
		// String line;
		// if ((line = b.readLine()) != null)
		// System.out.println(line);
		// } catch (final IOException e) {
		// e.printStackTrace();
		// }

		List<String> commandBarrier = new ArrayList<String>();
		String cyclicBarrierHostname = this.configurationParameters.getCyclicBarrierHostname();
		commandBarrier.add("java");
		commandBarrier.add("-ea");
		commandBarrier.add("-Xms2m");
		commandBarrier.add("-cp");
		commandBarrier.add(
				"/Users/Grey/workspace/broker/target/classes;/Users/Grey/workspace/broker/resources/jing.jar;/Users/Grey/workspace/broker/resources/jcip-annotations.jar;/Users/Grey/workspace/broker/resources/javassist.jar");
		commandBarrier.add("-Djava.security.manager");
		commandBarrier.add("-Djava.security.policy=/Users/Grey/workspace/broker/src/app/dcvm.policy");
		commandBarrier.add("fr.sorbonne_u.components.cvm.utils.DCVMCyclicBarrier");
		commandBarrier.add("/Users/Grey/workspace/broker/src/app/config.xml");
		ProcessBuilder pbBarrier = new ProcessBuilder(commandBarrier);
		pbBarrier.directory(new File(hosts2dirs.get(cyclicBarrierHostname)));
		Process pBarrier = pbBarrier.start();

		// TODO: should be done with an explicit synchronisation!
		Thread.sleep(2000L);

		Process[] jvmProcesses = new Process[jvmURIs.length];
		for (int i = 0; i < jvmURIs.length; i++) {
			if (DEBUG) {
				System.out.println("Starting " + jvmURIs[i] + "...");
			}

			List<String> command = new ArrayList<String>();
			command.add("java");
			command.add("-ea");
			command.add("-Xms2m");
			if (reflectiveJVMs.contains(jvmURIs[i])) {
				command.add("-javaagent:hotswap.jar");
			}
			command.add("-cp");
			command.add(
					"/Users/Grey/workspace/broker/target/classes;/Users/Grey/workspace/broker/resources/jing.jar;/Users/Grey/workspace/broker/resources/jcip-annotations.jar;/Users/Grey/workspace/broker/resources/javassist.jar");
			command.add("-Djava.security.manager");
			command.add("-Djava.security.policy=/Users/Grey/workspace/broker/src/app/dcvm.policy");
			command.add(jvms2mainclasses.get(jvmURIs[i]));
			command.add(jvmURIs[i]);
			command.add("/Users/Grey/workspace/broker/src/app/config.xml");
			ProcessBuilder pbConsumer = new ProcessBuilder(command);
			pbConsumer.directory(new File(hosts2dirs.get(jvms2hosts.get(jvmURIs[i]))));
			jvmProcesses[i] = pbConsumer.start();

			if (DEBUG) {
				System.out.println("Starting " + jvmURIs[i] + "...done!");
			}
		}

		for (int i = 0; i < jvmProcesses.length; i++) {
			jvmProcesses[i].waitFor();
		}

		if (DEBUG) {
			System.out.println("exit status GlobalRegistry = " + pRegistry.exitValue());
			System.out.println("exit status CyclicBarrier = " + pBarrier.exitValue());
			for (int i = 0; i < jvmProcesses.length; i++) {
				System.out.println("exit status " + jvmURIs[i] + " = " + jvmProcesses[i].exitValue());
			}
		}
	}

	/**
	 * launch a BCM application which configuration file name is given as a
	 * command-line argument.
	 * 
	 * <p>
	 * <strong>Contract</strong>
	 * </p>
	 * 
	 * <pre>
	 * pre	args != null and args.length &gt;= 1
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param args
	 *            the config file name.
	 */
	public static void main(String[] args) {
		assert args != null && args.length >= 1;

		try {
			DCVM_Launcher launcher = new DCVM_Launcher(args[0]);
			launcher.launch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
// -----------------------------------------------------------------------------
