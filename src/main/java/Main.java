import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();
    private static int nextId = 1;
    private static final String FILE_NAME = "tasks.json";
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1": createTask(); break;
                case "2": listTasks(); break;
                case "3": updateTask(); break;
                case "4": completeTask(); break;
                case "5": deleteTask(); break;
                case "0":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- TASK TRACKER ---");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Listar tarefas");
        System.out.println("3. Editar tarefa");
        System.out.println("4. Marcar como concluída");
        System.out.println("5. Remover tarefa");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static void createTask() {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        Task task = new Task(nextId++, title, desc);
        tasks.add(task);
        saveTasks();
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    private static void updateTask() {
        System.out.print("ID da tarefa a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task task = findTaskById(id);
        if (task != null) {
            System.out.print("Novo título: ");
            task.setTitle(scanner.nextLine());
            System.out.print("Nova descrição: ");
            task.setDescription(scanner.nextLine());
            saveTasks();
            System.out.println("Tarefa atualizada!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void completeTask() {
        System.out.print("ID da tarefa a concluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(true);
            saveTasks();
            System.out.println("Tarefa marcada como concluída!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void deleteTask() {
        System.out.print("ID da tarefa a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            System.out.println("Tarefa removida!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static Task findTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    private static void saveTasks() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            tasks = gson.fromJson(reader, taskListType);
            if (tasks == null) tasks = new ArrayList<>();
            nextId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }
}
