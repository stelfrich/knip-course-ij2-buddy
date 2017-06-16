package org.knime.knip.imagej2.example;

import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Menu;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/*
 * Notes for buddy class loading
 * 
 * the manifest of org.knime.knip.imagej.core contains the entry "Eclipse-BuddyPolicy: registered" to indicate that it may use the
 * class loaders of registered plugins as well to find classes.
 * 
 * Therefore to use buddy loading we have to add "Eclipse-RegisterBuddy:" with "org.knime.knip.imagej.core" to register the buddydemo plugin
 * and "help" imagej.core with class loading.
 */

@Plugin(menu = {@Menu(label = "DeveloperPlugins"), @Menu(label = "Array Output Test")}, description = "Nope", headless = true, type = Command.class)
public class ArrayOutputTest<T extends RealType<T>> implements Command {

        @Parameter
        private float testFloat;

        @Parameter(type = ItemIO.OUTPUT)
        private float[] floatOut;

        @Override
        public void run() {
                floatOut = new float[] {testFloat};
        }

}