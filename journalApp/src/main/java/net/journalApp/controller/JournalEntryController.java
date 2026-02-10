package net.journalApp.controller;

import net.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<String,JournalEntry> journalEntries=new HashMap<>();

// Method inside Controller class should be public so that they can be accessed and invoked by spring framework.
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId(),newEntry);
        return true;
    }

    @GetMapping("/id/{Id}")
    public JournalEntry getJournalEntryById(@PathVariable String Id){
        return journalEntries.get(Id);
    }

    @DeleteMapping("/id/{Id}")
    public JournalEntry deleteEntryById(@PathVariable String Id){
        return journalEntries.remove(Id);
    }

    @PutMapping("/id/{Id}")
    public JournalEntry updateEntryById(@PathVariable String Id,@RequestBody JournalEntry newEntry){
        return journalEntries.put(Id,newEntry);
    }

}
