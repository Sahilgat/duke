package task;

import run.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = parseDateTime(by);
        } catch (NumberFormatException ex) {
            Ui.showError("Invalid format! Try dd/mm/yyyy hhmm instead!");
        } catch (DateTimeException ex) {
            Ui.showError(ex.getMessage());
        }
    }
    private LocalDateTime parseDateTime(String by) throws DateTimeException, NumberFormatException {
        try {
            String[] splited = by.split(" ");
            String[] dateFields = splited[0].split("/");
            int hour = Integer.parseInt(splited[1].substring(0, 2));
            int minute = Integer.parseInt(splited[1].substring(2));
            return LocalDateTime.of(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]), hour, minute);
        } catch (DateTimeException | NumberFormatException ex) {
            throw ex;
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = parseDateTime(by);
    }

    public String getStringBy() {
        return by.getDayOfMonth() + "/" + by.getMonthValue() + "/" + by.getYear() + " " + String.format("%02d", by.getHour()) + String.format("%02d", by.getMinute());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getStringBy() + ")";
    }
}