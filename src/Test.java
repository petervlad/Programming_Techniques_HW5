import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;

public class Test {
	
	public static void main(String[] args){
		
	
	Stream<String> stream = null;
	try {
		stream = Files.lines(Paths.get("Activities.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	List<MonitoredData> lines = stream.map(str->{
		
		String[] splitted = str.split("\t\t");
		
//		System.out.println(splitted[0]);
//		System.out.println(splitted[1]);
//		System.out.println(splitted[2]);
		
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		 
		 LocalDateTime start = LocalDateTime.parse(splitted[0], formatter);
		 LocalDateTime end = LocalDateTime.parse(splitted[1], formatter);
		 	
		 MonitoredData activity = new MonitoredData(start, end, splitted[2]);
		 
		 return activity;
		 
	}).collect(Collectors.toList());
		 
		 
		 //ex1...Count the distinct days that appear in the monitoring data
		 
		 int ex1 = (int) lines.stream().map(days->days.getStartTime()).distinct().count();
		 
		 System.out.println("Distinct days that appear in monitoring data: " + ex1);
		 
		 //ex2
		 /*
		  Determine a map of type <String, Integer> that maps to each distinct action type the number of
		  occurrences in the log. Write the resulting map into a text file.
		  */
		 
		 Map<String, Long> ex2 = lines.stream().collect(Collectors.groupingBy(name->name.getActivityLabel(), Collectors.counting()));
		 
		 PrintWriter writer_ex2;
			try {
				writer_ex2 = new PrintWriter( new File("ex2.txt") );
				
				ex2.forEach((key, value)->
				 {
					 writer_ex2.println(key + value);
				 });
				
				writer_ex2.close();
				 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 //ex3
		 /*
		  Generates a data structure of type Map<Integer, Map<String, Integer>> that contains the activity
		  count for each day of the log (task number 2 applied for each day of the log) and writes the result
          in a text file.
		  */
		 
		 Map<LocalDate, Map<String, Long>> ex3 = lines.stream().collect(Collectors.groupingBy(occurencesByDay -> occurencesByDay.getStartTimeByDays(), 
				                                                                                              Collectors.groupingBy(occurencesByDay -> occurencesByDay.getActivityLabel(), 
				                                                                                              Collectors.counting())));
		 PrintWriter writer_ex3;
			try {
				writer_ex3 = new PrintWriter( new File("ex3.txt") );
				
				ex3.forEach((key, value)->{
				 	for(String x:value.keySet()){
				 		writer_ex3.println(key +" " + x + " " + value.get(x));
				 	}
				 	});
				
				writer_ex3.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//ex4
			/*
			 Determine a data structure of the form Map<String, DateTime> that maps for each activity the total
			duration computed over the monitoring period. Filter the activities with total duration larger than
			10 hours. Write the result in a text file.
			 */
			
//			Map<String, Integer> ex4 = lines.stream().filter(x -> x.getDiffHours(start, end)  > 10).collect(Collectors.toMap(x->x.getKey, x->x.getDiffHours(start, end)));
//			
//			PrintWriter writer_ex4;
//			try {
//				writer_ex4 = new PrintWriter( new File("ex4.txt") );
//				
//				ex4.forEach((key, value)->{
//					writer_ex4.println(key + "   " + value);
//				)};
//				
//				writer_ex4.close();
//				
//			}catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		 
	
	}
}