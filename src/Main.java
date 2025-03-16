import java.util.Scanner;

/**
 *
 * The {@code Main} class provides a console-based interface for logging into
 * the <b>app, creating playlists, deleting playlists, and many more</b>.
 * <br><br>
 * <b>Features:</b>
 * <ul>
 *     <li>Login</li>
 *     <li>Register</li>
 *     <li>Retrieve Playlists</li>
 *     <li>Create Playlists</li>
 *     <li>Delete Playlists</li>
 *     <li>Display Songs from Selected Playlist</li>
 *     <li>Add Songs to Playlist</li>
 *     <li>Upload Updated Playlist to Database</li>
 *     <li>Remove Songs from Playlist</li>
 *     <li>Display Selected Song</li>
 *     <li>Delete User Account</li>
 * </ul>
 *
 * @author Nirupam Reddy Nannuri
 *
 */

public class Main {

    static App app = new App();

    /**
     *
     * The main method is the entry point of the application. It provides
     * a menu for users to perform operations.
     *
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("BeatScape: Playlist Simulator");
        System.out.println();
        String selection;
        do{
            System.out.println("[A] - 👨‍💻 Login\n[B] - 🔑 Register\n[C] - ❌ Exit\n");
            System.out.print("⏎ Enter selection: ");
            selection = in.nextLine();
            switch(selection.toUpperCase()){
                case "A":
                    System.out.println();
                    System.out.print("⏎ Enter email: ");
                    String email = in.nextLine();
                    System.out.print("⏎ Enter password: ");
                    String password = in.nextLine();
                    System.out.println();
                    if (app.validateLogin(email, password)){
                        System.out.println("✅ Logged in successfully!\n");
                        do{
                            System.out.println("[D] - 🔃 Retrieve Playlists\n" +
                                    "[E] - 🎯 Select Playlist By Name\n" +
                                    "[F] - 🆕 Create Playlist\n" +
                                    "[G] - 🗑️ Delete Playlist By Name\n" +
                                    "[H] - 🗑️ Delete Your Account\n" +
                                    "[I] - 🔙 Back \n");
                            System.out.print("⏎ Enter selection: ");
                            selection = in.nextLine();
                            switch(selection.toUpperCase()){
                                case "D":
                                    app.retrieveAllPlaylists(app.getUserID());
                                    break;
                                case "E":
                                    System.out.println();
                                    System.out.print("⏎ Enter Playlist Name: ");
                                    String name = in.nextLine();
                                    if (app.selectPlaylistByName(name)){
                                        System.out.println();
                                        System.out.println("✅ Playlist selected successfully!");
                                        System.out.println();
                                        do {
                                            System.out.println("[J] - 📂 Display Songs in Playlist\n" +
                                                    "[K] - ➕ Add Songs to Playlist\n" +
                                                    "[L] - ☁️ Upload Updated Songs to Database\n" +
                                                    "[M] - 🗑️ Remove Song From Playlist By Name\n" +
                                                    "[N] - 📋 Display Selected Song\n" +
                                                    "[O] - Back 🔙\n");
                                            System.out.print("⏎ Enter selection: ");
                                            selection = in.nextLine();
                                            switch(selection){
                                                case "J":
                                                    System.out.println();
                                                    app.displayAllSongs();
                                                    System.out.println();
                                                    break;
                                                case "K":
                                                    System.out.println();
                                                    System.out.print("⏎ Enter Song Name: ");
                                                    String songName = in.nextLine();
                                                    app.addSongsToPlaylist(songName);
                                                    System.out.println();
                                                    System.out.println("✅ Song added successfully!");
                                                    System.out.println();
                                                    break;
                                                case "L":
                                                    if (app.uploadUpdatedPlaylist()){
                                                        System.out.println();
                                                        System.out.println("✅ Playlist updated successfully and uploaded successfully!");
                                                        System.out.println();
                                                    } else {
                                                        System.out.println();
                                                        System.out.println("❌ Playlist upload failed!");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case "M":
                                                    System.out.println();
                                                    System.out.print("⏎ Enter Song Name: ");
                                                    String song = in.nextLine();
                                                    if (app.removeSongFromPlaylistByName(song)){
                                                        System.out.println();
                                                        System.out.println("✅ Song deleted successfully!");
                                                        System.out.println();
                                                    } else {
                                                        System.out.println();
                                                        System.out.println("❌ Song deletion failed!");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case "N":
                                                    System.out.println();
                                                    app.displaySelectedSong();
                                                    System.out.println();
                                                    break;
                                                case "O":
                                                    System.out.println();
                                                    System.out.println("⬅️ Going back!");
                                                    System.out.println();
                                                    break;
                                                default:
                                                    System.out.println();
                                                    System.out.println("❌ Invalid selection!");
                                                    System.out.println();
                                                    in.nextLine();
                                                    break;
                                            }
                                        } while (!selection.equals("O"));
                                    } else {
                                        System.out.println();
                                        System.out.println("❌ No playlist found with that name!");
                                        System.out.println();
                                    }
                                    break;
                                case "F":
                                    if (!app.getPlaylist().isEmpty()){
                                        System.out.println();
                                        System.out.println("⚠️ Please update your playlist before creating a new playlist. Changes will not be saved. ⚠️");
                                        System.out.println();
                                    } else {
                                        System.out.println();
                                        System.out.print("⏎ Enter playlist name: ");
                                        String playlistName = in.nextLine();
                                        if (app.createPlaylist(playlistName)){
                                            System.out.println();
                                            System.out.println("✅ Playlist created successfully!");
                                            System.out.println();
                                        } else {
                                            System.out.println();
                                            System.out.println("❌ Playlist creation failed!");
                                            System.out.println();
                                        }
                                    }
                                    break;
                                case "G":
                                    System.out.println();
                                    System.out.print("⏎ Enter Playlist Name: ");
                                    String playlistName = in.nextLine();
                                    if (app.deletePlaylistByName(playlistName)){
                                        System.out.println();
                                        System.out.println("✅ Playlist deleted successfully!");
                                        System.out.println();
                                    } else {
                                        System.out.println();
                                        System.out.println("❌ Playlist deletion failed!");
                                        System.out.println();
                                    }
                                    break;
                                case "H":
                                    System.out.println();
                                    System.out.print("⚠️ This action can't be reversed! Do you want to continue? (Y/N)⚠️: ");
                                    selection = in.nextLine();
                                    if (selection.equals("Y")){
                                        if (app.deleteUserAccount()){
                                            System.out.println();
                                            System.out.println("✅ User account deleted successfully!");
                                            System.out.println();
                                            selection = "I";
                                        } else {
                                            System.out.println();
                                            System.out.println("❌ User account deletion failed!");
                                            System.out.println();
                                        }
                                    } else {
                                        System.out.println();
                                        System.out.println("🚫 Process Aborted!");
                                        System.out.println();
                                    }
                                    break;
                                case "I":
                                    System.out.println();
                                    System.out.println("⬅️ Going back!");
                                    System.out.println();
                                    break;
                                default:
                                    System.out.println();
                                    System.out.println("❌ Invalid selection!");
                                    System.out.println();
                                    break;
                            }
                        } while (!selection.equals("I"));
                    } else {
                        System.out.println("⛔ Invalid login!");
                        System.out.println();
                    }
                    break;
                case "B":
                    System.out.println();
                    System.out.print("⏎ Enter email: ");
                    String emailSignUp = in.nextLine();
                    System.out.print("⏎ Enter password: ");
                    String passwordSignUp = in.nextLine();
                    if (app.signUp(emailSignUp, passwordSignUp)){
                        System.out.println();
                        System.out.println("✅ Registered successfully! You can now login!");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("⛔ Invalid registration!");
                        System.out.println();
                    }
                    break;
                case "C":
                    System.out.println();
                    System.out.println("🙏 Exiting BeatScape. See you next time!");
                    break;
                default:
                    System.out.println();
                    System.out.println("❌ Invalid selection!");
                    System.out.println();
                    break;
            }
        } while(!selection.equals("C"));
    }
}