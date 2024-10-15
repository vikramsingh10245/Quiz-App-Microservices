package com.project.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.model.Question;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionDao.findAll();
        try {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        List<Question> question = questionDao.findByCategory(category);
        try {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

	public ResponseEntity<String> deleteQuestion(Integer id) {
			if(questionDao.findById(id).isPresent())
			{
			questionDao.deleteById(id);
				return new ResponseEntity<>("Sucessfully Deleted",HttpStatus.OK);
			}
			return new ResponseEntity<>("Id not found",HttpStatus.NOT_FOUND);
	}
}
