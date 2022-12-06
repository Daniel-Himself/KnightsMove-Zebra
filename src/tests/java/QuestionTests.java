import com.knights_move.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTests {
    @Test
    public void testQuestion() {
        Question question = new Question("What is the capital of France?", "Paris", "London", "Berlin", "Rome", "Paris");
        assertEquals("Paris", question.getCorrectAnswer());
        assertEquals("What is the capital of France?", question.getQuestion());
        assertEquals("London", question.getAnswerB());
        assertEquals("Berlin", question.getAnswerC());
        assertEquals("Rome", question.getAnswerD());
    }
}