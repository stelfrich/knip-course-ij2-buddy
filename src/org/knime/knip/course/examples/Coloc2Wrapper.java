package org.knime.knip.course.examples;

import java.util.List;

import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import org.knime.core.node.NodeLogger;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import ij.ImagePlus;
import sc.fiji.coloc.Coloc_2;
import sc.fiji.coloc.algorithms.MissingPreconditionException;
import sc.fiji.coloc.results.AnalysisResults;
import sc.fiji.coloc.results.ValueResult;

/**
 * Computes the Pearson Correlation Coefficient for two input images.
 * 
 * @author Stefan Helfrich (University of Konstanz)
 */
@Plugin(menuPath = "DeveloperPlugins>Coloc2 Wrapper", headless = true, type = Command.class)
public class Coloc2Wrapper<T extends RealType<T> & NativeType<T>> implements Command {

        @SuppressWarnings("unused")
        private static final NodeLogger LOGGER = NodeLogger.getLogger(Coloc2Wrapper.class);

        @Parameter
        Img<T> input1;

        @Parameter
        Img<T> input2;

        @Parameter(type = ItemIO.OUTPUT, label = "Pearson Correlation Coefficient")
        double pearsonCorrelationCoefficient = 0.0d;

        @Override
        public void run() {
                // Initialize a Coloc_2 instance
                Coloc_2<T> coloc = new Coloc_2<>();

                // Set default settings
                int indexMask = 0;
                int indexRegr = 0;
                boolean autoSavePdf = false;
                boolean displayImages = false;
                boolean displayShuffledCostes = false;
                boolean useLiCh1 = true;
                boolean useLiCh2 = true;
                boolean useLiICQ = true;
                boolean useSpearmanRank = true;
                boolean useManders = true;
                boolean useKendallTau = true;
                boolean useScatterplot = true;
                boolean useCostes = true;
                int psf = 3;
                int nrCostesRandomisations = 10;

                // Coloc_2 shows a user interface if java.awt.headless is false. To not show this window, set the system property.
                System.getProperties().setProperty("java.awt.headless", "true");

                // Wrap input images
                ImagePlus in1 = ImageJFunctions.wrap(input1, "in1");
                ImagePlus in2 = ImageJFunctions.wrap(input2, "in2");

                // Initialize settings
                coloc.initializeSettings(in1, in2, indexMask, indexRegr, autoSavePdf, displayImages, displayShuffledCostes, useLiCh1, useLiCh2,
                                useLiICQ, useSpearmanRank, useManders, useKendallTau, useScatterplot, useCostes, psf, nrCostesRandomisations);

                try {
                        // Compute a colocalization result
                        AnalysisResults<T> results = coloc.colocalise(input1, input2, null, null, null);
                        List<ValueResult> values = results.values();
                        for (ValueResult v : values) {
                                // Use the following snippet to determine the interesting outputs
                                /* logger.info(v.name + "=" + (v.isNumber ? v.number : v.value)); */
                                if (v.name.equals("Pearson's R value (no threshold)")) {
                                        pearsonCorrelationCoefficient = v.number;
                                }
                        }
                } catch (MissingPreconditionException exc) {
                        // TODO Auto-generated catch block
                        exc.printStackTrace();
                }

                // Reset system property
                System.getProperties().setProperty("java.awt.headless", "false");
        }

}
