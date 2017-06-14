package QAPlatform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model kategorii
 * @author Bartek
 *
 */
@Entity
@Table(name = "categories")
public class QuestionCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	public QuestionCategory(String name) {
		this.name = name;
	}
	
	public QuestionCategory(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
