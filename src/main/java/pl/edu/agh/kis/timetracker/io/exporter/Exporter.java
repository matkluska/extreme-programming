package pl.edu.agh.kis.timetracker.io.exporter;

public interface Exporter<FROM, WHERE> {

  void export(FROM from, WHERE where);

}
