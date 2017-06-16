package org.knime.knip.imagej2.example;

import java.util.List;

import net.imglib2.img.Img;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import sc.fiji.coloc.Coloc_2;
import sc.fiji.coloc.algorithms.MissingPreconditionException;
import sc.fiji.coloc.results.AnalysisResults;
import sc.fiji.coloc.results.ValueResult;

/**
 * 
 * @author Stefan Helfrich
 */
@Plugin(menuPath = "DeveloperPlugins>Coloc2 Wrapper", headless = true, type = Command.class)
public class Coloc2Wrapper implements Command {

        @Parameter
        Img<?> input1;

        @Parameter
        Img<?> input2;

        @Parameter(type = ItemIO.OUTPUT)
        double testOut;

        @SuppressWarnings("rawtypes")
        @Override
        public void run() {
                Coloc_2 coloc = new Coloc_2();
                try {
                        AnalysisResults results = coloc.colocalise(input1, input2, null, null, null);
                        List<ValueResult> values = results.values();
                        testOut = values.get(0).number;
                } catch (MissingPreconditionException exc) {
                        // TODO Auto-generated catch block
                        exc.printStackTrace();
                }

        }

}
