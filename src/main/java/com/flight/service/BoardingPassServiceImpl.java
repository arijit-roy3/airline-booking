package com.flight.service;

import com.flight.entity.BoardingPass;
import com.flight.repository.BoardingPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardingPassServiceImpl implements BoardingPassService{
    @Autowired
    private BoardingPassRepository boardingPassRepository;
    @Override
    public BoardingPass save(BoardingPass boardingPass){
        return boardingPassRepository.save(boardingPass);
    }

    @Override
    public List<BoardingPass> getBoardingPass() {
        return boardingPassRepository.findAll();
    }

    @Override
    public BoardingPass getBoardingPassById(long id) {
        return boardingPassRepository.findByBoardingPassId(id);
    }
}
