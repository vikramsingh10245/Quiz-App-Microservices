package com.project.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.dao.QuizDao;
import com.project.quizapp.model.Question;
import com.project.quizapp.model.QuestionWrapper;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

	public  ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionFromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question q:questionFromDB) {
			QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> question=quiz.getQuestions();
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
