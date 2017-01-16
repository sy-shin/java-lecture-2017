package lecture.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_to_writer"))
	private User writer;
	
	private String title;
	
	@Lob
	private String contents;
	
	private String time;
	
	@OneToMany(mappedBy="question")
	private List<Answer> answers;
	
	private boolean deleteFlag;
	
	public Question(){}
	
	public Question(User writer, String title, String contents){
		this.writer=writer;
		this.title=title;
		this.contents=contents;
		deleteFlag=false;
				
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = dayTime.format(new Date(time));
		
		this.time = str;
	}
	
	public Question(Long id,User writer, String title, String contents){
		this.id = id;
		this.writer=writer;
		this.title=title;
		this.contents=contents;
		deleteFlag=false;
				
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = dayTime.format(new Date(time));
		
		this.time = str;
	}
	
	public void junitTestSetAnswer(Answer answer){
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(answer);
		this.answers = answers;
	}
	
	public int getAnswerCount(){
		return answers.size();
	}
	
	public void setAnswerWriterFlag(User loginUser){
		for( Answer i : answers)
			i.setWriterCheckFlag(loginUser);
	}
	
	public boolean isAnswerWriter(User loginUser){
		if(!isAnswer(loginUser))
			return true;
		setAnswerWriterFlag(loginUser);
		for( Answer i : answers)
			if(!i.isUserCheck()) return false;
		return true;
	}
	
	public boolean isDeleteOk(User loginUser){
		return (loginUser.isWriter(writer) && isAnswerWriter(loginUser));
	}
	
	private boolean isAnswer(User loginUser){
		return answers!=null;
	}
	
	public void setDeleteFlag(){
		deleteFlag=true;
	}

	public List<Question> getQuestionList(List<Question> questionList){
		List<Question> newQuestionList = new ArrayList<Question>();
		for(Question question:questionList)
			if(!question.deleteFlag) newQuestionList.add(question);
		return newQuestionList;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", contents=" + contents + deleteFlag + "]\n";
	}
}