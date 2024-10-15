package com.practice.quiz_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.practice.quiz_service.model.Quiz;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
