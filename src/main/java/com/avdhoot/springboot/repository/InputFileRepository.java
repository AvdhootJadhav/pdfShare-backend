package com.avdhoot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avdhoot.springboot.entity.InputFile;

@Repository
public interface InputFileRepository extends JpaRepository<InputFile, String>{

}
