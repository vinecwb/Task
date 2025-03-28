import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testCreateTask() {
        Task task = new Task(1, "Estudar Java", "Capítulo sobre Collections");

        assertEquals(1, task.getId());
        assertEquals("Estudar Java", task.getTitle());
        assertEquals("Capítulo sobre Collections", task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    public void testCompleteTask() {
        Task task = new Task(2, "Ler livro", "Capítulo 3");
        task.setCompleted(true);

        assertTrue(task.isCompleted());
    }

    @Test
    public void testUpdateTitleAndDescription() {
        Task task = new Task(3, "Original", "Original desc");

        task.setTitle("Atualizado");
        task.setDescription("Nova descrição");

        assertEquals("Atualizado", task.getTitle());
        assertEquals("Nova descrição", task.getDescription());
    }
}
