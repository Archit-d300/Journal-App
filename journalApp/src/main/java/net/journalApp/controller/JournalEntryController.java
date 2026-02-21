package net.journalApp.controller;

import net.journalApp.entity.JournalEntry;
import net.journalApp.entity.User;
import net.journalApp.service.JournalEntryService;
import net.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // Method inside Controller class should be public so that they can be accessed and invoked by spring framework.
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user=userService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry,@PathVariable String userName){
        try {
            journalEntryService.saveEntry(newEntry,userName);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{Id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId Id){
        Optional<JournalEntry> journalEntry=journalEntryService.findById(Id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}/id/{Id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId Id,@PathVariable String userName){
        journalEntryService.deleteById(Id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}/id/{Id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId Id,@RequestBody JournalEntry newEntry,@PathVariable String userName ){
        JournalEntry old = journalEntryService.findById(Id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && newEntry.getTitle().equals("") ? old.getTitle() : newEntry.getTitle());
            old.setContent(newEntry.getContent() != null && newEntry.getContent().equals("") ? old.getContent() : newEntry.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(newEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
