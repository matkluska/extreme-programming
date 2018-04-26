package pl.edu.agh.kis.timetracker.service;

public interface Formatter<T> {

  String format(final T t);
}
