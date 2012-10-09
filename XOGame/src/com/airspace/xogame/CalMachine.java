package com.airspace.xogame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CalMachine {
	// mark all history step, avoid meet duplication step;
	List<int[][]> historyList;
	// the main step list;
	List<int[][]> stepList;
	// mark the "final" result step
	List<int[][]> destinationStepList;
	int[] spaceSymbol;
	int[][] destinationStep;

	public CalMachine() {
		historyList = new ArrayList<int[][]>();
		stepList = new ArrayList<int[][]>();
		destinationStepList = new ArrayList<int[][]>();
		//stepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});
		spaceSymbol = new int[]{0,0};
		destinationStep = new int[][]{{2,1},{2,2},{2,3},{2,4},{2,5},{0,0},{1,5},{1,4},{1,3},{1,2},{1,1}};
	}
	
	public String GetSymbolbyID(int id){
		if (id == 0 )
			return "_";
		else if (id == 1)
			return "O";
		else if (id == 2)
			return "X";
		else
			return "E";		
	}
	
	public String GetSymbolbyID2(int[] id){
		String outputString;
		
		if (id[0] == 0 )
			outputString = "_";
		else if (id[0] == 1)
			outputString = "O";
		else if (id[0] == 2)
			outputString = "X";
		else
			outputString = "E";
		
		if (id[1] == 0)
			outputString += "_";
		else
			outputString += String.valueOf(id[1]);
		
		return outputString;		
	}	
	
	public boolean checkSequenceOValid(int[][] currentStep){
		int currentIndex = 5;
		for(int i=0;i<currentStep.length;i++){
			if (currentStep[i][0] == 1){  // 1 means O
				if (currentStep[i][1] > currentIndex)
					return false;
				else
					currentIndex = currentStep[i][1];
			}				
		}
		return true;
	}
	
	public boolean checkSequenceXValid(int[][] currentStep){
		int currentIndex = 5;
		for(int i=currentStep.length-1;i>=0;i--){
			if (currentStep[i][0] == 2){  // 1 means O
				if (currentStep[i][1] > currentIndex)
					return false;
				else
					currentIndex = currentStep[i][1];
			}				
		}
		return true;		
	}	
	
	public boolean exchangeSymbol(int[][] currentStep, int posSpace, int posSymbol){
		// check valid condition return false
		if (posSymbol <0 || posSymbol > 10 || posSpace < 0 || posSpace > 10)
			return false;
		
		// o cannot move left
		if (currentStep[posSymbol][0]==1 && posSpace < posSymbol){
			return false;
		}
		//x cannot move to right
		if (currentStep[posSymbol][0]==2 && posSpace > posSymbol){
			return false;
		}		
		
		int[] tempSymbol;
		tempSymbol = currentStep[posSymbol];
		currentStep[posSymbol] = currentStep[posSpace];
		currentStep[posSpace] = tempSymbol;
		
		// broken the sequence rule
		if (!(checkSequenceOValid(currentStep) && checkSequenceXValid(currentStep))){
			return false;
		}
		
		return true;
	}
	
	public boolean CheckHistoryExistStep(int[][] currentStep){
		Iterator<int[][]> itr = this.historyList.iterator();
		while (itr.hasNext()) {
		    int[][] element = itr.next();
		    if (Arrays.equals(element, currentStep))
		    	return true;
		}

		return false;
	}
	
	public void Calculate(int[][] currentStep){
		//System.out.print("check:");
		//printStep(currentStep);
		//System.out.print(this.historyList.size());
		//System.out.println();
		
		// get the list of step
		//List<int[][]> nextStepList = new ArrayList<int[][]>();
		int[][] nextStep;
			
		if (Arrays.deepEquals(currentStep, this.destinationStep)){
			destinationStepList = new ArrayList<int[][]>(this.stepList);
			destinationStepList.add(currentStep);
			//System.out.println("-----------------------start");
			//printStepList(this.stepList);
			//System.out.println("-----------------------end");
			//return;
		}
		
		if (destinationStepList.size() > 0){
			return;
		}
			

		//int spaceIndex = Arrays.asList(currentStep).indexOf(new int[]{0,0});
		int spaceIndex = -1;
		for (int i=0;i<currentStep.length;i++){
			//if (currentStep[i][0] == 0)
			if (Arrays.equals(currentStep[i], spaceSymbol))
				spaceIndex = i;
		}
		
		this.historyList.add(currentStep);
		
		this.stepList.add(currentStep);
		// 4 condition exchange: l1, l2, r1, r2
		//l1	
		nextStep = currentStep.clone();	
		if (exchangeSymbol(nextStep, spaceIndex, spaceIndex -1) &&
			!CheckHistoryExistStep(nextStep)){
			//this.printStep(nextStep);
			Calculate(nextStep);						
		}
		
		nextStep = currentStep.clone();	
		if (exchangeSymbol(nextStep, spaceIndex, spaceIndex -2) &&
			!CheckHistoryExistStep(nextStep)){
			//this.printStep(nextStep);
			Calculate(nextStep);
		}			
		
		nextStep = currentStep.clone();	
		if (exchangeSymbol(nextStep, spaceIndex, spaceIndex +1) &&
			!CheckHistoryExistStep(nextStep)){
			//this.printStep(nextStep);
			Calculate(nextStep);
		}			
		
		nextStep = currentStep.clone();	
		if (exchangeSymbol(nextStep, spaceIndex, spaceIndex +2) &&
			!CheckHistoryExistStep(nextStep)){
			//this.printStep(nextStep);
			Calculate(nextStep);
		}				
		this.stepList.remove(this.stepList.size()-1);
		
		//printStep(currentStep);		
		//nextStepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});
		//printStepList(nextStepList);

		//System.out.println(spaceIndex);
		// loop the step
		//stepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});		
		
	}
	
	void printStepList(List<int[][]> showList){
		for (int i = 0; i < showList.size(); i++) {
			for(int j=0; j< ((int[][])showList.get(i)).length; j++){
				//System.out.print(GetSymbolbyID(showList.get(i)[j][0])+showList.get(i)[j][1]+",");
				System.out.print(GetSymbolbyID2(showList.get(i)[j])+",");
				
			}
			System.out.println();
		}
	}
	
	void printStep(int[][] showStep){
		for(int j=0; j< showStep.length; j++){
			//System.out.print(GetSymbolbyID(showStep[j][0])+showStep[j][1]+",");
			System.out.print(GetSymbolbyID2(showStep[j])+",");
		}
		//System.out.println();
	}	

}
