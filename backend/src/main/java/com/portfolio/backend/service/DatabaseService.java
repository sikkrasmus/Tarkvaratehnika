package com.portfolio.backend.service;

import com.portfolio.backend.repository.DatabaseRepository;
import com.portfolio.backend.user.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DatabaseService {

    private DatabaseRepository databaseRepository;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public Database getSessionById(String sessionId){
        Iterable<Database> sessions = databaseRepository.findAll();
        for (Database session : sessions){
            System.out.println("Comparison: " + session.getSessionId() + "    " + sessionId);
            if (session.getSessionId().equals(sessionId)){
                return session;
            }
        }
        return null;
    }
}
