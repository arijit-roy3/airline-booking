package com.flight.service;

import com.flight.entity.BoardingPass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardingPassService {
    public BoardingPass save(BoardingPass boardingPass);

    public List<BoardingPass> getBoardingPass();

    public BoardingPass getBoardingPassById(long id);
}
