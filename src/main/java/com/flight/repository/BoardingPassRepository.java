package com.flight.repository;

import com.flight.entity.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingPassRepository extends JpaRepository<BoardingPass,Long> {
    public BoardingPass findByBoardingPassId(long id);
}
