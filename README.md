# RbtTransplantSearch

Explaination:-
RBT Transplant means Merging two Rbt tree.
RBT Transplant means Merging two Rb tree. For transplant to take place, both the RB tree is traversed in InOrder way and the data values of that RB tree will be stored in an ArrayList. Both the mentioned InOrders when merged will give a resultant InOrder which is resultant of merged RB trees and then successively the searching of element takes place. The problem lies in the time complexity while Merging two ArrayList. To reduce this complexity we used Set Data Structure which reduces it to O(logn).

Output:-
The following is the output for the code:-
run:
		Searching in Red Black Tree 
****************************************************
Insert into Tree1
Enter element to insert(insert -9999 to terminate)
1
2
3
4
5
6
7
-9999
Inorder of Tree1
 1 2 3 4 5 6 7
Insert into Tree2
Enter element to insert(insert -9999 to terminate)
8
88
564
34
34
22
1
-9999
Inorder of Tree2
 1 8 22 34 34 88 564
Choose from Following

1)Without Using Set datastructure	2)With Using Set datastructure	3)Exit
1
Merging
Enter Element to search:
	1
1 Found
Time spend to Search 81000nanosec

Choose from Following

1)Without Using Set datastructure	2)With Using Set datastructure	3)Exit
2
Merging
Enter Element to search:
1
1 Found
Time spend to Search 68400 nanoseconds

Choose from Following

1)Without Using Set datastructure	2)With Using Set datastructure	3)Exit
3
BUILD SUCCESSFUL (total time: 46 minutes 39 seconds)

Observation:-
The difference between both times to search an element clearly proves that Set data structure takes less time to execute


