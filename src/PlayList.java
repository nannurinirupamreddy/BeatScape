/**
 *
 * The {@code PlayList} class represents a collection of songs in the form of
 * linked list. It allows users to add, remove, and retrieve songs.
 *  <br><br>
 * <b>Features:</b>
 *  <ul>
 *      <li>Adding Songs to Playlist</li>
 *      <li>Removing Songs by Name</li>
 *      <li>Checking if a Song Already Exists</li>
 *      <li>Clearing the Entire Playlist</li>
 *      <li>Tracking the Current Song (Cursor)</li>
 *      <li>Displaying All Songs in the Playlist</li>
 *  </ul>
 *
 * @author Nirupam Reddy Nannuri
 *
 */
class PlayList {
    private Node head;
    private Node tail;
    private Node cursor;
    private int size;
    private String playlistName;

    /**
     *
     * Constructs a new {@code PlayList} with the specified name.
     *
     * @param playlistName The name of the playlist.
     *
     */
    public PlayList(String playlistName) {
        this.playlistName = playlistName;
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    /**
     * Gets the head (first song) of the playlist.
     *
     * @return The head node.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the head (first song) of the playlist.
     *
     * @param head The node to be set as head.
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Gets the tail (last song) of the playlist.
     *
     * @return The tail node.
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Sets the tail (last song) of the playlist.
     *
     * @param tail The node to be set as tail.
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Gets the currently selected song.
     *
     * @return The cursor node.
     */
    public Node getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor (current song).
     *
     * @param cursor The node to be set as the cursor.
     */
    public void setCursor(Node cursor) {
        this.cursor = cursor;
    }

    /**
     * Gets the name of the playlist.
     *
     * @return The playlist name.
     */
    public String getPlaylistName() {
        return this.playlistName;
    }

    /**
     * Sets the name of the playlist.
     *
     * @param playlistName The new playlist name.
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Gets the total number of songs in the playlist.
     *
     * @return The size of the playlist.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the playlist.
     *
     * @param size The new size of the playlist.
     */

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Checks if the playlist is empty.
     *
     * @return {@code true} if the playlist is empty, {@code false} otherwise.
     */
    public boolean isEmpty(){
        return this.head == null;
    }

    /**
     * Adds a song to the playlist if it does not already exist.
     *
     * @param songName The name of the song to add.
     * @return {@code true} if the song was added, {@code false} if it already exists.
     */
    public boolean addSong(String songName) {
        if (this.isEmpty()) {
            this.head = new Node(songName);
            this.tail = this.head;
            this.cursor = this.head;
            this.size++;
            return true;
        }
        if (!this.ifAlreadyExists(songName)) {
            Node song = new Node(songName);
            this.tail.setNext(song);
            this.tail = song;
            this.cursor = this.tail;
            this.size++;
            return true;
        }
        return false;
    }

    /**
     * Checks if a song already exists in the playlist.
     *
     * @param songName The name of the song.
     * @return {@code true} if the song exists, {@code false} otherwise.
     */
    public boolean ifAlreadyExists(String songName) {
        if (!this.isEmpty()) {
            return false;
        }
        Node song = this.head;
        while (song != null) {
            if (song.getSongName().equals(songName)) {
                return true;
            }
            song = song.getNext();
        }
        return false;
    }

    /**
     * Removes a song from the playlist by name.
     *
     * @param songName The name of the song to remove.
     * @return {@code true} if the song was removed, {@code false} if not found.
     */
    public boolean removeSong(String songName) {
        if (this.isEmpty()) {
            return false;
        }
        if (!this.ifAlreadyExists(songName)) {
            return false;
        }
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
            this.cursor = null;
            this.size--;
            return true;
        }
        Node song = this.head;
        Node prev = this.head;
        while (!song.getSongName().equals(songName) && song != null) {
            prev = song;
            song = song.getNext();
        }
        if (song.getSongName().equals(songName)) {
            prev.setNext(song.getNext());
            this.size--;
            return true;
        }
        return false;
    }

    /**
     * Removes the currently selected song from the playlist.
     *
     * @return {@code true} if successful, {@code false} otherwise.
     */
    public boolean removeCurrentSong() {
        if (this.isEmpty()) {
            return false;
        }
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
            this.cursor = null;
            this.size--;
            return true;
        }
        Node song = this.head;
        while (song.getNext() != cursor && song != null) {
            song = song.getNext();
        }
        if (song.getNext() == cursor) {
            song.setNext(null);
            this.size--;
            return true;
        }
        return false;
    }

    /**
     * Removes a song from the playlist by its index.
     *
     * @param songIndex The index of the song (1-based).
     * @return {@code true} if successful, {@code false} otherwise.
     */
    public boolean removeSong(int songIndex) {
        if (this.isEmpty()) {
            return false;
        }
        if (songIndex < 1 || songIndex > this.size) {
            return false;
        }
        Node song = this.head;
        for (int i = 1; i < size; i++) {
            song = song.getNext();
        }
        if (song.getNext() != null && song.getNext().getNext() != null){
            song.setNext(song.getNext().getNext());
            this.size--;
        } else if (song.getNext() != null && song.getNext().getNext() == null) {
            song.setNext(null);
            this.size--;
        } else {
            this.removeCurrentSong();
            return true;
        }
        return false;
    }

    /**
     * Clears the entire playlist, removing all songs.
     */
    public void clearPlaylist(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.size = 0;
    }

    /**
     * Returns a string representation of the playlist, listing all songs.
     *
     * @return A formatted string of all songs in the playlist.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Node song = this.head;
        int id = 0;
        while (song != null) {
            ++id;
            output.append(id + ". " + song.getSongName() + "\n");
            song = song.getNext();
        }
        return output.toString();
    }
}
