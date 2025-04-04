package com.practice.question_service.service;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.question_service.dao.QuestionDao;
import com.practice.question_service.model.Question;
import com.practice.question_service.model.QuestionWrapper;
import com.practice.question_service.model.Response;


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

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
		
		return new  ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		
		for(Integer id:questionIds) {
			questions.add(questionDao.findById(id).get());			
		}
		for(Question question:questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestion_title(question.getQuestion_title());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			
			wrappers.add(wrapper);
			
			
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		
		List<Question> question=questionDao.findAll();
		int right=0;
		for(Response response:responses) {
			for(Question questions:question)
			{
				if(questions.getId().equals(response.getId())&& questions.getRight_answer().equals(response.getResponse()))
				{
					right++;
					break;
				}
			}
		}
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
}
