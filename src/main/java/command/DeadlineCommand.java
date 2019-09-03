package command;

import run.TaskList;
import run.Ui;
import run.Storage;
import task.Deadline;

/**
 * Extends AddCommand class and is used to create and manage a new deadline task
 */
public class DeadlineCommand extends AddCommand {
    protected String rawString;

    /**
     * Constructor for a new DeadLineCommand that takes in the entire raw string the user
     * enters to be later parsed and separated into the the new deadline's relevant fields
     * @param rawString complete unparsed user input of deadline creation request
     */
    public DeadlineCommand(String rawString) {
        this.rawString = rawString;
    }

    /**
     * Adds a new deadline task to current TaskList, adds this
     * new deadline into storage and interacts/updates the user through the ui
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        String remove_command = rawString.replaceFirst("deadline ", "");
        String[] splited = remove_command.split(" /by ");
        Deadline curr_task = new Deadline(splited[0], splited[1]);
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}