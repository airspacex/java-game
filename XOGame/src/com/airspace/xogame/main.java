package com.airspace.xogame;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalMachine calMachine = new CalMachine();
		calMachine.Calculate(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});
		calMachine.printStepList(calMachine.stepList);
		
		
		//System.out.println(args.length);
	    if(args.length == 0) {
	        // Do default here--no options specified
	    	System.out.println("arg 0");
	    } else if(args.length > 1) {
	        // Complain that there are too many args, or implement multi-args
	    	System.out.println(">1 arg");
	    } else 
		{
	    	System.out.println("one arg");
//	    	if(args[0].equals("option1")) {
//		           // call the main of your first app
//	    		System.out.println("option1");
//		    } else if(args[0].equals("option2")) {
//		           // start your second app
//	    		System.out.println("option2");		    	
//		   }
	    }
	    
	    
		
	}


}
