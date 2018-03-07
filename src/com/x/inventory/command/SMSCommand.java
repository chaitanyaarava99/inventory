package com.x.inventory.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.x.inventory.LineItem;
import com.x.inventory.ProfitCalculator;
import com.x.inventory.error.SMSException;

public class SMSCommand {
	String[] validCommands = {"help", "report", "create", "delete", "updateBuy", "updateSell"};
	Map<String, LineItem> inventory = new HashMap<String, LineItem>();
	
	ProfitCalculator profit = new ProfitCalculator();
	
	
	public void parseSMSCommand(String sms)throws SMSException{
		if(null == sms || sms.isEmpty()){
			throw new SMSException("Invalid command, type 'help' for details");
		}
		String commArgs[] = sms.split(" ");
		validateCommand(sms);
		
		//check if starts with create
		if("create".equalsIgnoreCase(commArgs[0])){
			LineItem item = createLineItem(commArgs);
			System.out.println(item);
		}else if("updateBuy".equalsIgnoreCase(commArgs[0]) ||
				"updateSell".equalsIgnoreCase(commArgs[0]) ){
			updateLineItem(commArgs);
		}else if("delete".equalsIgnoreCase(commArgs[0])){
			deleteLineItem(commArgs[1]);
		}else if("report".equalsIgnoreCase(commArgs[0])){
			printReport();
		}else if("help".equalsIgnoreCase(commArgs[0])){
			showHelp();
		}else{
			throw new SMSException("unsupported command, please type 'help' for more details");
		}
	}


	
	private void showHelp() {
		System.out.println("below commands are allowed : ");
		for (String command : validCommands) {
			System.out.println("      "+command);
		}
	}

	private void printReport() {
		
		System.out.println("\t\t INVENTORY REPORT  ");
		System.out.println("Item Name 	Bought At    	Sold At       	AvailableQty    	Value");
		System.out.println("--------- 	---------    	-------       	-----------     	-------");
		for (Map.Entry<String, LineItem> item : inventory.entrySet()){
			System.out.println(item.getValue().printLineItemForReport());
		}
		System.out.println();
		System.out.println("Profit since previous report \t\t\t\t\t\t"+profit.printProfitForReport());
	}

	public void deleteLineItem(String product) throws SMSException{
		
		LineItem item = inventory.remove(product);
		
		if(item == null){
			throw new SMSException("No product exist, please check the product you are trying to remove");
		}
		profit.deleteItem(item);
		
	}
	
	public void updateLineItem(String[] args) throws SMSException{
		String product = args[1];
		int qty = Integer.parseInt(args[2]);
		LineItem item = inventory.get(product);
		
		if("updateBuy".equalsIgnoreCase(args[0])){
			if(item != null){
				item.setQuantity(item.getQuantity()+qty);
				System.out.println("added qty"+item);
			}else{
				throw new SMSException("No product exist, please create the product first");
			}
		}else if("updateSell".equalsIgnoreCase(args[0])){
			if(item != null){
				int netQty = item.getQuantity()-qty;
				if(netQty < 0){
					throw new SMSException("Not enough qty to sell, total available qty is "+item.getQuantity());
				}
				profit.sellItem(item, qty);
				item.setQuantity(netQty);
				
				System.out.println("removed qty"+item);
				
			}else{
				throw new SMSException("No product exist, please create the product first");
			}
		}else{
			throw new SMSException("Not valid command : "+args[0]);
		}

	}
	
	public LineItem createLineItem(String[] args){
		LineItem item = new LineItem();
		item.setProduct(args[1]);
		Double buy = Double.parseDouble(args[2]);
		Double sell = Double.parseDouble(args[3]);
		item.setCostPrice(buy);
		item.setSellingPrice(sell);
		inventory.put(item.getProduct(), item);
		return item;
	}
	public boolean validateCommand(String sms) throws SMSException{
		boolean valid = false;
		for (String command : validCommands) {
			String commArgs[] = sms.split(" ");
			if(command.equalsIgnoreCase(commArgs[0])){
				return true;
			}
		}
		
		throw new SMSException("Invalid command to start, type 'help' for details");
	}
	
	public static void main(String[] args)  {
		SMSCommand command = new SMSCommand();
		
		while(true){
			Scanner scan = new Scanner(System.in);
	        String sms = scan.nextLine();
	        System.out.println("Command: " + sms);
		
	        try {
				command.parseSMSCommand(sms);
			} catch (SMSException e) {
				System.err.println("error in command, retry");
				e.printStackTrace();
			}
		}
	}
}
