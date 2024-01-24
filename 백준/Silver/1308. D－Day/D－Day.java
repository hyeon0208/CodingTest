import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
        
		st = new StringTokenizer(br.readLine());
		int f_year = Integer.parseInt(st.nextToken());
		int f_month = Integer.parseInt(st.nextToken());
		int f_day = Integer.parseInt(st.nextToken());
		boolean leap = false;
		int dday = 0;
		if (is_gg(year, month, day, f_year, f_month, f_day)) {
            System.out.println("gg");
		} else {
			dday= check_days(f_year,f_month-1,f_day) - check_days(year,month-1,day);
            System.out.println("D-" + dday);
		}
	}
    
	public static int check_days(int year,int month,int day) {
		int[] m_day = {31,28,31,30,31,30,31,31,30,31,30,31};
		int days = 0;
		for(int i=0;i<year; i++) {
			days+=365;
			if(check_leap(i)==true) {
				days+=1;
			}
		}
		for(int i=0;i<month; i++) {
			days+=m_day[i];
			if(i==1&&check_leap(year)==true) {
				days+=1;
			}
		}
		
		days+=day;
		
		return days;
	}

	public static boolean is_gg(int year, int month, int day, int f_year, int f_month, int f_day) {

		if (f_year - year > 1000) {
			return true;
		} else if (f_year - year == 1000) {
			if (f_month > month) {
				return true;
			} else if (f_month == month) {
				if (f_day >= day) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean check_leap(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}