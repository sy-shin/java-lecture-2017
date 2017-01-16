package lecture.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class QuestionDelete {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_questionDelete_to_user"))
	private User deleter;
	
	private String time;
	
	@OneToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_questionDelete_to_question"))
	private Question question;
	
	public QuestionDelete() {}
	
	public QuestionDelete(User deleter, Question question){
		this.deleter = deleter;
		this.question = question;
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = dayTime.format(new Date(time));
		
		this.time = str;
	}

	@Override
	public String toString() {
		return "QuestionDelete [id=" + id + ", deleter=" + deleter + ", time=" + time + ", question=" + question + "]";
	}
}