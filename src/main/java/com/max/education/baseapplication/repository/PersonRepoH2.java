package com.max.education.baseapplication.repository;

import com.max.education.baseapplication.entity.PersonH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepoH2 extends JpaRepository<PersonH2, Integer> {
}