/**
 * 
 */
package QAPlatform.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author root
 *
 */
public class QuestionTest {

	/**
	 * @throws java.lang.Exception
	 */
	private Question q;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetTopic() {
		String value = "topic";
		User user = mock(User.class);
		Question q = new Question(value,"test",1,1,user);
		assertEquals(q.getTopic(),value);
	}

}
