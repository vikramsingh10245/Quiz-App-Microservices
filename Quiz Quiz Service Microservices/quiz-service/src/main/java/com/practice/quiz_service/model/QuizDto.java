package com.practice.quiz_service.model;

public class QuizDto {
String categoryName;
Integer numQuestions;
String title;
@Override
public String toString() {
	return "QuizDto [categoryName=" + categoryName + ", numQuestions=" + numQuestions + ", title=" + title + "]";
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public Integer getNumQuestions() {
	return numQuestions;
}
public void setNumQuestions(Integer numQuestions) {
	this.numQuestions = numQuestions;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public QuizDto() {
	super();
	// TODO Auto-generated constructor stub
}
public QuizDto(String categoryName, Integer numQuestions, String title) {
	super();
	this.categoryName = categoryName;
	this.numQuestions = numQuestions;
	this.title = title;
}

}
