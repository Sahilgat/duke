package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Abstract class for commands that results from user input that may
 * modify or retrieve information from the task list in various ways
 */
public abstract class Command {

    /**
     * Abstract method that adds, withdraws or removes information from current TaskList, updates the
     * state of the TaskList through storage and interacts/updates the user through the ui
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /** Checks if this command is an exit ("bye") command
     * @return boolean that checks if this is exit ("bye") command
     */
    public abstract boolean isExit();
}