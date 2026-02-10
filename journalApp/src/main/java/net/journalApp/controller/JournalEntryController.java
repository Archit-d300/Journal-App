package net.journalApp.controller;

import net.journalApp.entity.JournalEntry;
import net.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    // Method inside Controller class should be public so that they can be accessed and invoked by spring framework.
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry newEntry){
        newEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(newEntry);
        return newEntry;
    }

    @GetMapping("/id/{Id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId Id){
        return journalEntryService.findById(Id).orElse(null);
    }

    @DeleteMapping("/id/{Id}")
    public boolean deleteEntryById(@PathVariable ObjectId Id){
        journalEntryService.deleteById(Id);
        return true;
    }

    @PutMapping("/id/{Id}")
    public JournalEntry updateEntryById(@PathVariable ObjectId Id,@RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(Id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && newEntry.getTitle().equals("") ? old.getTitle() : newEntry.getTitle());
            old.setContent(newEntry.getContent() != null && newEntry.getContent().equals("") ? old.getContent() : newEntry.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

}
