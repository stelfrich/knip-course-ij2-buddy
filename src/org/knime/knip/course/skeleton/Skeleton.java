package org.knime.knip.course.skeleton;

import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/*
 * Notes for buddy class loading:
 * 
 * the manifest of org.knime.knip.imagej.core contains the entry
 * "Eclipse-BuddyPolicy: registered" to indicate that it may use the class
 * loaders of registered plugins as well to find classes.
 * 
 * To use buddy loading, add the line
 * "Eclipse-RegisterBuddy: org.knime.knip.imagej.core" to the MANIFEST.
 */

/**
 * Skeleton {@link Command}.
 * 
 * @author Stefan Helfrich (University of Konstanz)
 *
 * @param <T>
 *                type of input image
 */
@Plugin(menuPath = "DeveloperPlugins>Skeletong", headless = true, type = Command.class, description = "Skeleton with one input image and one double output")
public class Skeleton<T extends RealType<T>> implements Command {

        @Parameter
        private Img<T> input;

        @Parameter(type = ItemIO.OUTPUT)
        private double doubleOut;

        @Override
        public void run() {
                doubleOut = 1.0d;
        }

}
