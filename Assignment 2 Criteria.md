﻿
d) A TaskFactory class was created in which different types of Task objects such as DeliveryTask, DropOffTask etc can be created and returned. I’ve used dependency injection for observers and tasks as they will have an instance of the BikeShop class, and the current BikeShop instance is passed through the parameter.

TaskObserverClass

private BikeShop shop; // BikeShop object

`    `public TaskObserver(BikeShop pShop) {

`        `this.shop = pShop;

`    `}

// shop instance of BikeShop class is injected to the TaskObserver constructor.

TaskObserver taskObserver = new TaskObserver(shop);

This has also been done for other observers and tasks. 

Throughout the program no dependency has been hard coded. All dependencies have been injected using dependency injection. The above example is one of those instances.

e) I’ve used observer pattern to handle all types of messages such as the tasks (Delivery, Pickup, Purchase, Drop off), paying the employee, failures and successful completion of tasks. In order to do so I have created a MessageObserver Interface which all observers will implement. The MessageObserver interface will have one method, update() method do update the program when an event occurs. Four implementations of the MessageObserver interface were done to handle all types of messages. They are as follows TaskObserver, EmployeeObserver, FailureObserver, SuccessObserver.

TaskObserver- The TaskObsever is responsible for handling the four different types of task messages that arrive from the BikeShopInput class such as delivery, pickup, drop off and pickup. When a valid message is sent from the BikeShopInput class the TaskObserver is notified using the notifyTaskObserver(String message) method in the BikeShop class. The TaskObserver class checks the message to check that it doesn’t start with “E”. If it does it means it’s and indication to pay the employee and its handled separately by the EmployeeObserver. If the message doesn’t start with “E” the TaskObbserver class handles that task. The TaskObserver class will use the TaskFactory class to create task objects to handle each of the scenarios. Once an instance of the Task class is returned, the doTask() method of that task is called and the task is handled.

EmployeeObserver- The EmployeeObserver class is responsible for paying the employee every seven days. This is done by the ScheduledExecutorService that sends a message every 7 seconds. When the message is sent the notifyTaskObservers(String message) method is used to notify the EmployeeObserver. The EmployeeObserver class check if the message starts with an “E” which indicates that it is time to pay the employee. If the message starts with an “E” the employee is paid.

FailureObserver- The FailureObserver class is responsible for reporting failures. When a failure is reported such as an invalid message, no bikes being available for purchase, no space in the bikeshop the FailureObserver handles it. When a failure occurs the notifyFailureObserver(String message) method is called and the failure is displayed and written to the file.

SuccessObserver – The SuccessObserver is responsible for reporting when a task has been successfully completed. When a task is completed the notifySuccessObserver(String message) method is called and the message is displayed and written to the file.

f) I implemented three states using the state pattern in this program.

` `1. BikePurchaseState- This state is indicates if the shop has bikes to purchase or not. This was done by implementing a BikePurchaseState interface. Two implementations of the interface were made to indicate the different representations of the state. The PurchasableState indicates the bikeshop has bikes to sell and the UnpurchasableState indicates the bikeshop does not have bikes to sell.  The bikeshop is initially in a BikePurchaseState of PurchasableState as the shop has 50 bikes to sell. Once the bikes are sold out a state transition occurs and the BikePurchaseState changes to a UnpurchasableState. Once a delivery is made and if the state is in a UnpurchasableState a state transition happens and it will transition to a PurchasableState.

2\. DeliveryState- This state indicates if the shop is accepting deliverys or not. This was done by implementing a DeliveryState interface. Two implementations of the interface were made to indicate the different representations of the state. The AcceptingDeliveryState indicates that the bikeshop is currently accepting deliveries. The RejectingDeliveryState indicates the bikeshop is currently rejecting deliveries. Initially the bikeshop is in an AcceptingDeliveryState as there are only 50 bikes in the shop so there is space for more. If the number of bikes in the shop exceed 90 and the amount of cash is below 10000 a state transition occurs and transitions to a RejectingDeliveryState. Once number of bikes falls below 90 and cash is at least 10000 or above a state transition occurs and transitions to an AcceptingDeliveryState.

3\. ServicingState – This state indicates if the bikeshop is accepting bike servicing or not. This was done by implementing a ServicingState interface. Two implementations of this interface were made to indicate the different representations of the state. The AcceptingServicing state indicates the bike shop is accepting bikes for servicing while the RejectingServicingState indicates the bikeshop is rejecting servicing bikes. Initially the bikeshop is in a state of AcceptingServicingState as there is only 50 bikes in the shop and there is a capacity of 100 bikes to be stored. If the number of bikes in the store exceed 100 then a state transition occurs and transitions to a RejectingServicingState. Once the number of bikes in the shop drops below 100 a state transition occurs and transitions to a RejectingServicingState.
