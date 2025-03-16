/**
 *
 * The {@code Node} class represents a single node in the linked list of song
 * objects.
 * Each <b>Node</b> contains name of the song, and pointer to the next song.
 *
 * @author Nirupam Reddy Nannuri
 *
 */
class Node {

    /**
     *
     * The name of the song
     *
     */
    private String songName;

    /**
     *
     * The pointer to the next node.
     *
     */
    private Node next;

    /**
     *
     * Constructs a new node with given name.
     *
     * @param songName The name of the song.
     *
     */
    public Node(String songName) {
        this.songName = songName;
    }

    /**
     *
     * Getter method of the song name
     *
     * @return The song name
     *
     */
    public String getSongName() {
        return songName;
    }

    /**
     *
     * Setter method of song name
     *
     * @param songName The name of the song
     *
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     *
     * Returns the pointer to the next node
     *
     * @return The next node
     *
     */
    public Node getNext() {
        return next;
    }

    /**
     *
     * Setter method for next node pointer
     *
     * @param next The next node to be linked
     *
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     *
     * Returns the string containing name of the song
     *
     * @return String representation of song
     *
     */
    @Override
    public String toString() {
        return this.getSongName();
    }

}
