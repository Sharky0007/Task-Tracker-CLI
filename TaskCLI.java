import java.io.IOException;

public class TaskCLI {

    public static void main(String[] args) throws IOException {
    TaskManager taskManager = new TaskManager();

    try{
         if(args.length != 0) {
            switch (args[0]) {
                case "add":
                taskManager.addTask(args[1]);
                    break;
                
                case "update":
                int updateId = Integer.parseInt(args[1]);
                taskManager.updateTask(updateId,args[2]);
                    break;

                case "delete":
                int deleteId = Integer.parseInt(args[1]);
                taskManager.deleteTask(deleteId);
                    break;

                case "list":
                taskManager.listTasks();
                    break;

                case "listByStatus":
                taskManager.listTasksByStatus(args[1]);
                    break;

                case "updateStatus":
                int updateStatusId = Integer.parseInt(args[1]);
                taskManager.modifyTaskStatus(updateStatusId,args[2]);
                    break;
                    
                default:
                    break;
            }
         }
       } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a task description" + e);
        }
 }
}