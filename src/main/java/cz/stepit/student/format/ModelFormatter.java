package cz.stepit.student.format;

/**
 * Formats specified model of type {@code <M>}.
 *
 * @param <M> type of model to be formatted
 */
public interface ModelFormatter<M> {

    /**
     * Formats the model.
     *
     * @param model model
     * @return formatted string
     */
    String format(M model);
}
