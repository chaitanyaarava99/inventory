# Inventory Management 
- Supports below commands
  -  help
  -  report
  -  create 
  -  delete
  -  updateBuy
  -  updateSell
  
 Maintains the inventory transactions
 prints the report
 prints the profit and loss since last report generated
 
 SMSCommand. java is main class to execute and use the utility
 
 example command
 java SMSCommand
 create Book01 10.50 13.79
create Food01 1.47 3.98
create Med01 30.63 34.29
create Tab01 57.00 84.98
updateBuy Tab01 100
updateSell Tab01 2
updateBuy Food01 500
updateBuy Book01 100
updateBuy Med01 100
updateSell Food01 1
updateSell Food01 1
updateSell Tab01 2
report

last command, report will return the report along with profit
#Output
              	INVENTORY REPORT
Item Name 	Bought At    	Sold At       	AvailableQty    	Value
--------- 	---------    	-------       	-----------     	-------
Book01    	10.50          	13.79               	100    	1050.00
Food01     	1.47           	3.98               	498     	732.06
Med01     	30.63          	34.29               	100    	3063.00
Tab01     	57.00          	84.98                	96    	5472.00
---------------------------------------------------------------------------
Total value                                                     	10317.06
Profit since previous report                                      	116.94

