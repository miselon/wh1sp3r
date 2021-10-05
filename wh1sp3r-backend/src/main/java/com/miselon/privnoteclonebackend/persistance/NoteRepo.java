package com.miselon.privnoteclonebackend.persistance;

import com.miselon.privnoteclonebackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public interface NoteRepo extends JpaRepository<Note, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Note n WHERE n.ts < :currentMillis")
    int deleteExpiredNotes(@Param("currentMillis") Long currentMillis);

}

