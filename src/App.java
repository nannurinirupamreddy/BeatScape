import java.sql.*;

/**
 *
 * The {@code App} class provides core functionality for managing playlists
 * and user accounts in the BeatScape playlist simulator. It handles database
 * connections.
 *
 * @author Nirupam Reddy Nannuri
 *
 */
class App {

    private PlayList playlist;

    /**
     *
     * The connection details of database.
     *
     */
    private static final String url = "jdbc:mysql://localhost:3306/beatscape";
    private static final String username = "root";
    private static final String password = "[YOUR-PASSWORD]";
    private Connection con;

    /**
     * user_id: The user id of the current logged-in user from database.<br>
     * playlist_id: The playlist id of the currently selected playlist.
     *
     */
    private int user_id;
    private int playlist_id;

    /**
     *
     * SQL Query operations to check if the login details are correct or not,
     * or if the user is registering for the first time, or if user wants to
     * delete their account.
     *
     */
    private static final String validateLoginQuery = "SELECT * FROM user_accounts WHERE email = ? and password = ?";
    private static final String signUpQuery = "INSERT INTO user_accounts (email, password) VALUES (?, ?)";
    private static final String deleteUserAccountQuery = "DELETE FROM user_accounts WHERE id = ?";

    /**
     *
     * SQL Queries for playlist operations.
     *
     */
    private static final String retrievePlaylistQuery = "SELECT * FROM playlists WHERE user_id = ?";
    private static final String selectPlaylistQueryName = "SELECT * FROM playlists WHERE name = ?";
    private static final String createPlaylistQuery = "INSERT INTO playlists (name, user_id) VALUES (?, ?)";
    private static final String uploadUpdatedPlaylist = "INSERT INTO songs (name, playlist_id) SELECT ?, ? FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM songs WHERE name = ? AND playlist_id = ?)";
    private static final String deletePlaylistQueryName = "DELETE FROM playlists WHERE name = ? AND user_id = ?";

    /**
     *
     * SQL Queries for operations on song elements.
     *
     */
    private static final String deleteSongQueryName = "DELETE FROM songs WHERE name = ? AND playlist_id = ?";
    private static final String retrieveSongsQuery = "SELECT * FROM songs WHERE playlist_id = ?";

    /**
     *
     * Constructor for App class. Connects to database.
     *
     */
    public App() {
        playlist = new PlayList("");
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * Getter method for playlist object.
     *
     */
    public PlayList getPlaylist() {
        return playlist;
    }

    /**
     *
     * Setter method for playlist object.
     *
     * @param playlist The playlist to be set.
     *
     */
    public void setPlaylist(PlayList playlist) {
        this.playlist = playlist;
    }

    /**
     *
     * Getter method for connection object.
     *
     */
    public Connection getConnection() {
        return con;
    }

    /**
     *
     * Getter method for user id when logged in.
     *
     */
    public int getUserID() {
        return user_id;
    }

    /**
     *
     * Setter method for user id.
     *
     * @param userID The user id of user in database.
     *
     */
    public void setUserID(int userID) {
        this.user_id = userID;
    }

    /**
     *
     * Getter method for playlist id in database when user logged in.
     *
     */
    public int getPlaylistID() {
        return playlist_id;
    }

    /**
     *
     * Setter method for playlist id.
     *
     * @param playlistID The playlist id of the playlist in the database.
     *
     */
    public void setPlaylistID(int playlistID) {
        this.playlist_id = playlistID;
    }

    /**
     *
     * This method validates if the user exists, and the password matches
     * with the password entered while registering.
     *
     * @param email The email of user.
     * @param password The password of user.
     *
     */
    public boolean validateLogin(String email, String password) {
        try{
            PreparedStatement ps = con.prepareStatement(validateLoginQuery);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setUserID(rs.getInt("id"));
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method signs up user with the email and password they entered.
     *
     * @param email The email of user.
     * @param password The password of user.
     *
     */
    public boolean signUp(String email, String password) {
        try {
            PreparedStatement ps = con.prepareStatement(signUpQuery);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method retrieves all the playlists associated with the user in
     * the database.
     *
     * @param user_id The user id of the user in database.
     *
     */
    public void retrieveAllPlaylists(int user_id) {
        try {
            PreparedStatement ps = con.prepareStatement(retrievePlaylistQuery);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            int id = 0;
            if (!rs.next()) {
                System.out.println();
                System.out.println("No playlists found!");
                System.out.println();
                return;
            }
            while (rs.next()) {
                ++id;
                System.out.println(id + ". " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * This method selects playlist by name, if it is associated with the
     * user logged in. (Playlist Name: Case-Sensitive)
     *
     * @param name The name of the playlist.
     *
     */
    public boolean selectPlaylistByName(String name) {
        try {
            PreparedStatement ps = con.prepareStatement(selectPlaylistQueryName);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setPlaylistID(rs.getInt("id"));
                this.playlist.setPlaylistName(rs.getString("name"));
                this.retrieveSongs(this.getPlaylistID());
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method retrieves songs from the selected playlist that is
     * associated with the user.
     *
     * @param playlist_id The playlist id of the user.
     *
     */
    public void retrieveSongs(int playlist_id) {
        try{
            PreparedStatement ps = con.prepareStatement(retrieveSongsQuery);
            ps.setInt(1, playlist_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.playlist.addSong(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * This method displays all the songs of the selected playlist.
     *
     */
    public void displayAllSongs(){
        if (playlist.isEmpty()){
            System.out.println();
            System.out.println("No songs to display!");
            System.out.println();
        }
        System.out.println(playlist.toString());
    }

    /**
     *
     * This method adds songs to the current playlist.
     *
     * @param songName The song name to be added.
     *
     */
    public void addSongsToPlaylist(String songName) {
        playlist.addSong(songName);
    }

    /**
     *
     * This method uploads the updated playlist to database.
     *
     * @return True if the upload is successful, false if not.
     *
     */
    public boolean uploadUpdatedPlaylist(){
        try{
            PreparedStatement ps = con.prepareStatement(uploadUpdatedPlaylist);
            Node song = this.playlist.getHead();
            while (song != null) {
                ps.setString(1, song.getSongName());
                ps.setInt(2, this.getPlaylistID());
                ps.setString(3, song.getSongName());
                ps.setInt(4, this.getPlaylistID());
                ps.executeUpdate();
                song = song.getNext();
            }
            playlist.clearPlaylist();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method removes song from the selected playlist.
     *
     * @param songName The name of the song to be deleted.
     *
     */
    public boolean removeSongFromPlaylistByName(String songName) {
        try {
            PreparedStatement ps = con.prepareStatement(deleteSongQueryName);
            ps.setString(1, songName);
            ps.setInt(2, this.getPlaylistID());
            ps.executeUpdate();
            playlist.removeSong(songName);
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method displays the current song.
     *
     */
    public void displaySelectedSong() {
        System.out.println(this.playlist.getCursor().toString());
    }

    /**
     *
     * This method deletes the playlist from the database.
     *
     * @param name The name of the playlist to be deleted.
     * @return True if the deletion is successful, false if not.
     *
     */
    public boolean deletePlaylistByName(String name) {
        try{
            PreparedStatement ps = con.prepareStatement(deletePlaylistQueryName);
            ps.setString(1, name);
            ps.setInt(2, this.getPlaylistID());
            ps.executeUpdate();
            playlist = new PlayList(name);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method creates a new playlist.
     *
     * @param name The name of the playlist to be created.
     * @return True if creation of playlist is successful, false if not.
     *
     */
    public boolean createPlaylist(String name) {
        playlist = new PlayList(name);
        try{
            PreparedStatement ps = con.prepareStatement(createPlaylistQuery);
            ps.setString(1, name);
            ps.setInt(2, this.getUserID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * This method deletes the user's account which is logged in currently.
     *
     * @return True if deletion of account is successful, false if not.
     *
     */
    public boolean deleteUserAccount() {
        try{
            PreparedStatement ps = con.prepareStatement(deleteUserAccountQuery);
            ps.setInt(1, this.getUserID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}