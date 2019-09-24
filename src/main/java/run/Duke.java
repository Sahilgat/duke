package run;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import exception.DukeException;
import parser.Parser;
import command.Command;

/**
 * Creates a new Duke interface task management system that has includes a task list, storage system and user
 * interface.
 */
public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/avatar.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/hal.jpg"));
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath to current save state file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructor for Duke for JavaFx.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("C:\\Users\\nisga\\OneDrive\\Desktop\\duke\\src\\main\\java\\data\\tasks.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Calls parser to get a response from user input. If response is exit message additionally closes stage
     * Todo: Bad design, split handling
     * @param input raw String entered by user
     * @return String response after parsing user input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            if (response.equals(Ui.EXIT_MESSAGE)) {
                Main.STAGE.close();
                return Ui.EXIT_MESSAGE;
            } else {
                return response;
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

}
