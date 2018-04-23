import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MonitoredData {
	
	LocalDateTime startTime;
	LocalDateTime endTime;
	String activityLabel;
	
	public MonitoredData(LocalDateTime start, LocalDateTime end, String activity){
		
		this.startTime = start;
		this.endTime = end;
		this.activityLabel = activity;
	}
	
	public 	void setStartTime(LocalDateTime date){
		this.startTime = date;
	}
	
	public LocalDateTime getStartTime(){
		return startTime;
	}
	
	public LocalDate getStartTimeByDays(){
		
		String str;
		
		if(startTime.getDayOfMonth() <= 9){
			str = startTime.getYear() + "-" + startTime.getMonthValue() + "-0" + startTime.getDayOfMonth();
		}
		else{
			str = startTime.getYear() + "-" + startTime.getMonthValue() + "-" + startTime.getDayOfMonth();
		}
				 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 
		 LocalDate dateTime = LocalDate.parse(str, formatter);
		 
		 return dateTime; 
	}
	
	public void setEndTime(LocalDateTime date){
		this.endTime = date;
	}
	
	public LocalDateTime getEndTime(){
		return endTime;
	}
	
	public void setActivityLabel(String activity){
		this.activityLabel = activity;
	}
	
	public String getActivityLabel(){
		return activityLabel;
	}
	
	public int getDiffHours(LocalDateTime a, LocalDateTime b){
		int ResultInHours = 0;
		
		LocalDateTime fromDateTime = a;
		LocalDateTime toDateTime = b;
		
		LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );
		
		long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );

		long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );

		long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );


		long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );

		long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );
		
		ResultInHours += tempDateTime.getYear() * 2080;
		ResultInHours += tempDateTime.getMonthValue() * 720;
		ResultInHours += tempDateTime.getDayOfMonth();
		ResultInHours += tempDateTime.getHour();
		
		return ResultInHours;
	}
	
	
}