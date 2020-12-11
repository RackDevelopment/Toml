package studio.thevipershow.vtc;

public interface ConfigurationContent {

    /**
     * Try and parse the data from the configuration
     * in order to save it.
     */
    void storeContent();

    /**
     * Delete all the content that is, or is not,
     * present in the current parsed data.
     * This will make {@link #isContentAvailable()}
     * return false until other changes are made.
     */
    void deleteContent();

    /**
     * @return True if any content has been parsed,
     * false if no data is available
     */
    boolean isContentAvailable();
}
