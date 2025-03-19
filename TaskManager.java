import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class TaskManager {

// ADD A TASK:
    public void addTask(String task){
        int id = 0;
        String filepath= "task.json";
        File file = new File(filepath);
        if(!file.exists()){
            try{
                file.createNewFile();
            }
            catch(IOException e){
                throw new RuntimeException("Error creating file");
            }
        }
            try{
                BufferedReader br = new BufferedReader(new FileReader(filepath));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                    if(line.contains("taskId")){
                        String[] parts = line.split(":");
                        id = Integer.parseInt(parts[1].substring(0, parts[1].length()-1).trim());
                    }
                }
            br.close();
            id++;
            Task addTask = new Task(id, task, "TODO", new Date(), new Date());

            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            String newData = sb.toString().trim();
            if(sb.length()>0){
                newData = newData.substring(0, newData.length()-1) + ",\n" + addTask.toJson() + "\n]";
                bw.write(newData);
            }
            else{
                newData = "[\n" +addTask.toJson() + "\n]";
                bw.write(newData);
            }

            bw.close();
            }
            catch(FileNotFoundException e){
                throw new RuntimeException("File not found");
            }
            catch(IOException e){
                throw new RuntimeException("Error reading file");
            }
    }

// UPDATE A TASK:
    public void updateTask(int id, String description){
        try{
            BufferedReader br = new BufferedReader(new FileReader("task.json"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("task_temp.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            boolean isTaskFound = false;
            while((line = br.readLine()) != null){
                if(line.contains("taskId") && line.contains(String.valueOf(id))){
                    sb.append(line).append("\n");
                    isTaskFound = true;
                }
                else if(isTaskFound && line.contains("description")){
                    String newLine = "  \"description\": \"" + description + "\",";  
                    sb.append(newLine).append("\n");              
                }
                else if(isTaskFound && line.contains("updatedOn")){
                      String newLine = "  \"updatedOn\": \"" + new Date() + "\"";
                      sb.append(newLine).append("\n");
                      isTaskFound = false;
                  }
                else{
                    sb.append(line).append("\n");
                }
            }

            String newData = sb.toString().trim();
            bw.write(newData);
                      
            br.close();
            bw.close();

            Files.delete(Paths.get("task.json"));
            Files.move(Paths.get("task_temp.json"), Paths.get("task.json"));
        }
        catch(FileNotFoundException e){
            throw new RuntimeException("File not found");
        }
        catch(IOException e){
            throw new RuntimeException("Error reading file");
        }
    }

// DELETE A TASK:
      public void deleteTask(int id){
        try{
            BufferedReader br = new BufferedReader(new FileReader("task.json"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("task_temp.json"));
            StringBuilder sb = new StringBuilder();

            String line;
            boolean isTaskFound = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("taskId") && line.contains(String.valueOf(id))) {
                    isTaskFound = true;
                }
                else if((isTaskFound && line.contains("{"))){
                    isTaskFound = false;
                }
                else if(!isTaskFound){
                    sb.append(line).append("\n");
                }               
            }

            sb.toString().trim();         
            if(sb.toString().trim().endsWith("{")){
                int lastBraces = sb.lastIndexOf("{");
                sb.delete(lastBraces-2, lastBraces+2);
                sb.append("]");
            }
            bw.write(sb.toString().trim());
            br.close();
            bw.close();

            Files.delete(Paths.get("task.json"));
            Files.move(Paths.get("task_temp.json"), Paths.get("task.json"));
        }
        catch(FileNotFoundException e){
            throw new RuntimeException("File not found");
        }
        catch(IOException e){
            throw new RuntimeException("Error reading file");
      }
    }

// LIST ALL TASKS:
      public void listTasks(){
            try{
                BufferedReader br = new BufferedReader(new FileReader("task.json"));
                String line;
                    while((line = br.readLine()) != null){
                        System.out.println(line);
                    }
                br.close();
            }
            catch(FileNotFoundException e){
                throw new RuntimeException("File not found");
            }
            catch(IOException e){
                throw new RuntimeException("Error reading file");
            }
      }

// LIST TASKS BY STATUS:
      public void listTasksByStatus(String status){

      }

// MODIFY TASK STATUS:
      public void modifyTaskStatus(int id, String status){
        try{
            BufferedReader br = new BufferedReader(new FileReader("task.json"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("task_temp.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            boolean isTaskFound = false;
            while((line = br.readLine()) != null){
                if(line.contains("taskId") && line.contains(String.valueOf(id))){
                    sb.append(line).append("\n");
                    isTaskFound = true;
                }
                else if(isTaskFound && line.contains("status")){
                    String newLine = "  \"status\": \"" + status + "\",";  
                    sb.append(newLine).append("\n");              
                }
                else if(isTaskFound && line.contains("updatedOn")){
                      String newLine = "  \"updatedOn\": \"" + new Date() + "\"";
                      sb.append(newLine).append("\n");
                      isTaskFound = false;
                  }
                else{
                    sb.append(line).append("\n");
                }
            }

            bw.write(sb.toString().trim());
            br.close();
            bw.close();

            Files.delete(Paths.get("task.json"));
            Files.move(Paths.get("task_temp.json"), Paths.get("task.json"));
        }
        catch(FileNotFoundException e){
            throw new RuntimeException("File not found");
        }
        catch(IOException e){
            throw new RuntimeException("Error reading file");
        }
      }
}
