package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }
@PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createResponse = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry readResponse= timeEntryRepository.find(timeEntryId);
        if (readResponse!=null){
            return new ResponseEntity<TimeEntry>(readResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }

    }
@GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(),HttpStatus.OK);
    }
@PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry expected) {
    TimeEntry updateResponse= timeEntryRepository.update(timeEntryId, expected);
    if (updateResponse!=null){
        return new ResponseEntity<TimeEntry>(updateResponse,HttpStatus.OK);
    }else{
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }
    }
@DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
