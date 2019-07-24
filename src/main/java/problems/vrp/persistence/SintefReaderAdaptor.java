package problems.vrp.persistence;

import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import problems.vrp.domain.VrpSolution;

import java.io.File;

public class SintefReaderAdaptor implements SolutionFileIO<VrpSolution> {


  @Override
  public String getInputFileExtension() {
    return null;
  }

  @Override
  public VrpSolution read(File file) {
    SintefReader reader = new SintefReader();
    return reader.read(file);
  }

  @Override
  public void write(VrpSolution vrpSolution, File file) {

  }
}
