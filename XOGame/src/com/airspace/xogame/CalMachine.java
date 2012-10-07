package com.airspace.xogame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalMachine {
	List<int[][]> stepList;
	int[] spaceSymbol;

	public CalMachine() {
		stepList = new ArrayList<int[][]>();
		//stepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});
		spaceSymbol = new int[]{0,0};
	}
	
	public String GetSymbolbyID(int id){
		if (id == 0 )
			return "_";
		else if (id == 1)
			return "X";
		else if (id == 2)
			return "O";
		else
			return "E";		
	}
	
	public boolean exchangeSymbol(int[][] currentStep, int posSpace, int posSymbol){
		int[] tempSymbol;
		tempSymbol = currentStep[posSymbol];
		currentStep[posSymbol] = currentStep[posSpace];
		currentStep[posSpace] = tempSymbol;
		return true;
	}
	
	public void Calculate(int[][] currentStep){
		// get the list of step
		List<int[][]> nextStepList = new ArrayList<int[][]>();
		int[][] nextStep;
		

		//int spaceIndex = Arrays.asList(currentStep).indexOf(new int[]{0,0});
		int spaceIndex = -1;
		for (int i=0;i<currentStep.length;i++){
			//if (currentStep[i][0] == 0)
			if (Arrays.equals(currentStep[i], spaceSymbol))
				spaceIndex = i;
		}	
		// 4 condition exchange: l1, l2, r1, r2
		//l1
		nextStep = currentStep.clone();	
		if (exchangeSymbol(nextStep, spaceIndex, spaceIndex -1))
			stepList.add(nextStep);
		
		nextStep = currentStep.clone();	
		exchangeSymbol(nextStep, spaceIndex, spaceIndex -2);
		stepList.add(nextStep);
		
		nextStep = currentStep.clone();	
		exchangeSymbol(nextStep, spaceIndex, spaceIndex +1);
		stepList.add(nextStep);
		
		nextStep = currentStep.clone();	
		exchangeSymbol(nextStep, spaceIndex, spaceIndex +2);
		stepList.add(nextStep);		
		
		
		//printStep(currentStep);		
		nextStepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});

		//System.out.println(spaceIndex);
		// loop the step
		stepList.add(new int[][] {{1,5},{1,4},{1,3},{1,2},{1,1},{0,0},{2,1},{2,2},{2,3},{2,4},{2,5}});		
		
	}
	
	void printStepList(List<int[][]> showList){
		for (int i = 0; i < showList.size(); i++) {
			for(int j=0; j< ((int[][])showList.get(i)).length; j++){
				System.out.print(GetSymbolbyID(showList.get(i)[j][0])+showList.get(i)[j][1]+",");
			}
			System.out.println();
		}
	}
	
	void printStep(int[][] showStep){
		for(int j=0; j< showStep.length; j++){
			System.out.print(GetSymbolbyID(showStep[j][0])+showStep[j][1]+",");
		}
		System.out.println();
	}	

}
