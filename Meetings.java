//Given n meetings, (start_i, duration_i) find the minimum number of rooms to
//schedule them in so that they don't overlap. 

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class Interval implements Comparable<Interval>{
    int start;
    int end;
    int duration;

    Interval(int start, int duration){
	this.start =  start;
	end = start + duration;
    }

   public int  compareTo(Interval interval){
       if (this.start < interval.start) return -1;
       else if (this.start > interval.start) return 1;
       else return 0;
    }
}



class ByEnd implements Comparator<Interval>{

   public int compare(Interval a, Interval b){
       return a.end - b.end;
   }
}

public class Meetings{
    
    public int minRooms(List<Interval> start){
	List<Interval> end =  new ArrayList<Interval>(start);
	Collections.sort(start);
	Collections.sort(end, new ByEnd());

	//mergelike
	int room = 0;
	int  max = 0;
	while (!start.isEmpty()){
	    Interval a = start.get(0);
	    Interval b = end.get(0);
	    if (a.start < b.end){
		room++;
		start.remove(0);
	    }
	    else{ //if start >= end
		room--;
		end.remove(0);
	    }
	    if (max < room) max = room;
	}
	return max;
    }
    
    public static void main(String [] str){
	List<Interval> start  = new ArrayList<Interval>();
        /*start.add(new Interval(1,3));
        start.add(new Interval(3,2));
        start.add(new Interval(2,1));
        start.add(new Interval(5,3));
	start.add(new Interval(4,2));*/


	start.add(new Interval(10,2));
        start.add(new Interval(18,2));
        start.add(new Interval(8,6));
        start.add(new Interval(15,4));
	start.add(new Interval(6,1));
	start.add(new Interval(8,3));


	Meetings meetings = new Meetings();
	int rooms = meetings.minRooms(start);
	System.out.println("mininum rooms required " + rooms);       
	
    }
}
