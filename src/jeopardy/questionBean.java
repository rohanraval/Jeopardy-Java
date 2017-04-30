package jeopardy;
import java.io.Serializable;

public class questionBean implements Serializable {
	
	//instance vars
	private String question;
	private String answer;
	private int row;
	private int column;
	private int score;
	
	//constructor
	public questionBean(String question, String answer, int row, int column, int score) {
		super();
		this.question = question;
		this.answer = answer;
		this.row = row;
		this.column = column;
		this.score = score;
	}

	//getters and setters
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
