import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Interval implements Comparable<Interval>{
    int left;
    int right;
    
    Interval(int l, int r){
	left = l;
	right = r;
    }
    public int compareTo(Interval that ){
	if (this.left < that.left) return -1;
        if (this.left > that.left) return 1;
        return 0;
    }

}

public class IntervalUnion{
    public List<Interval> unionOfIntervals(List<Interval> intervals){
	Collections.sort(intervals);  
	
        List<Interval>  result = new ArrayList<Interval>(); 
      
	int union_left = intervals.get(0).left;
	int union_right = intervals.get(0).right;

        for(Interval i:intervals){
            int current_right = i.right;
            int current_left = i.left;
            if(current_left <= union_right && union_right < current_right ){
              union_right = current_right;
	      }
           
            if(union_right < current_left ){
		result.add(new Interval(union_left,union_right));
		union_left = current_left;
		union_right = current_right;
		
	    }
        }
        result.add(new Interval(union_left,union_right));
	

        return   result;
    }

    public static void main(String [] args){
	ArrayList<Interval> a  = new ArrayList<Interval>();
        a.add(new Interval(1,4));
        a.add(new Interval(3,5));
        a.add(new Interval(2,3));
        a.add(new Interval(7,8));
       
        IntervalUnion iu = new IntervalUnion();
	List<Interval>  union =  iu.unionOfIntervals(a);
        for (Interval i : union) System.out.println(i.left + " -- " + i.right);
                

    }
}
