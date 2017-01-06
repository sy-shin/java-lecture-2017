package lecture.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String writer;
	private String title;
	private String contents;
	private String time;
	
	
	public void setTime(String time) {
		this.time = time;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + "time="+time+", contents=" + contents + "]";
	}
}
