package cn.cst.controller.api;

import cn.cst.dao.NoteRepository;
import cn.cst.entity.Note;
import cn.cst.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "api/backupNote")
public class BackupNoteController {


    @Autowired
    NoteRepository noteRepository;
    @PostMapping
    public ResponseEntity addKeyValue(@RequestBody Note note, HttpSession session){
        User user = (User)session.getAttribute("user");
        note.setUserId(user.getId());
        note.setCreatedTime(LocalDateTime.now());
        Note save = noteRepository.save(note);
        return ResponseEntity.ok(save);
    }
    @GetMapping
    public ResponseEntity getKeyValues(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Note> all = noteRepository.findAllByUserId(user.getId());
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id){
        noteRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
