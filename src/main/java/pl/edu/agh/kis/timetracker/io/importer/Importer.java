package pl.edu.agh.kis.timetracker.io.importer;

public interface Importer<TYPE, FROM> {

  TYPE importFrom(FROM from);
}
