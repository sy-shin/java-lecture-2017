package lecture.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Answer {
	// 질문, 유저, 내용
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_qeustion"))
	private Question question;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_writer"))
	private User writer;
	
	@Lob
	private String contents;
	private String time;
	
	@Transient
	private boolean writerCheckFlag;
	
	public Answer(){}
	public Answer(User writer, String contents, Question question){
		this.writer = writer;
		this.contents = contents;
		this.question = question;
		
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = dayTime.format(new Date(time));
		
		this.time = str;
	}

	public void setWriterCheckFlag(User loginUser) {
		writerCheckFlag=writer.isWriter(loginUser);
	}
	void setUserCheckFlag(boolean flag){
		writerCheckFlag = flag;
	}
	public boolean isUserCheck() {
		return writerCheckFlag;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", question=" + question + ", writer=" + writer + ", contents=" + contents
				+ ", time=" + time + ", writerCheckFlag=" + writerCheckFlag + "]";
	}
}