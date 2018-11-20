package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> hhmmtimeEntries = new HashMap<>();

    private long currentId = 1L;

    // creating method
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        hhmmtimeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    //Finding the values
    @Override
    public TimeEntry find(Long id) {
        return hhmmtimeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(hhmmtimeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        hhmmtimeEntries.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        hhmmtimeEntries.remove(id);
    }
}