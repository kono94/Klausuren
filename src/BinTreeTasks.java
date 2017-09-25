

class Binary {
	public Node m_Root = null;

	static class Node {
		int m_Key;
		Node m_Left = null, m_Right = null;

		public Node(int i) {
			m_Key = i;
		}

	}
	
	//pascal'sche lösung
//	int secondLast(){
//		Node tTempNode = m_Root;
//		Node tSmalNode = null;
//		Node vadder = m_Root;
//		while(true){
//		if (tTempNode.m_Left!=null){
//			vadder = tTempNode;
//			tTempNode = tTempNode.m_Left;
//		}else if (tTempNode.m_Right !=null && tSmalNode == null){
//			vadder = tTempNode;
//			tSmalNode = tTempNode;
//			tTempNode = tTempNode.m_Right;
//		}else if (tTempNode.m_Right !=null){
//			vadder = tTempNode;
//			tTempNode = tTempNode.m_Right;
//			
//		}else if(tTempNode.m_Key<vadder.m_Key && tSmalNode == null){
//			return vadder.m_Key;
//		}else{
//			return tTempNode.m_Key;
//		}
//		}
//	}
	
	int secondLast(){
		Node a = this.m_Root;
		Node b = this.m_Root;
		
		while(a.m_Left != null){
			b = a;
			a = a.m_Left;
		}
		
		if(a.m_Right == null){
			return b.m_Key;
		}else{
			a = a.m_Right;
			while(a.m_Left != null){
				a = a.m_Left;
			}
			return a.m_Key;
		}
	}
	//	float avg() {
//	 int[] sum = new int[2];
//	 avg_helper(m_Root, sum);
//	 return sum[0] / sum[1];
//}
//
//void avg_helper(Node n, int[] sum) {
//	if (n != null) {
//		sum[0] = n.m_Key + sum[0];
//		sum[1]++;
//		avg_helper(n.m_Left, sum);
//		avg_helper(n.m_Right, sum);
//	}			
//}
float avg() {			
	 return  avg_helper(m_Root, 0,0);
}

float avg_helper(Node n, int sum, int count) {
	if (n != null) {
		sum += n.m_Key;
		++count;
		avg_helper(n.m_Left, sum, count);
		avg_helper(n.m_Right, sum,count);
	}		
	return sum/count;
}

	
	public int[]minMax(){
		int[] result = new int[2];
		result[0] = m_Root.m_Key;
		result[1] = m_Root.m_Key;
		
		recursionHelper(m_Root, result);		
		return result;
	}
	
	void recursionHelper(Node node, int[] result){
		System.out.print(node.m_Key+ " ");
		if(node.m_Key < result[0]) result[0] = node.m_Key;
		if(node.m_Key > result[1]) result[1] = node.m_Key;
		if(node.m_Left != null) recursionHelper(node.m_Left, result);
		if(node.m_Right != null) recursionHelper(node.m_Right, result);		
	}
	
	public void deleteLeafs() {
		// deleteHelper(m_Root);
		deleterHelper(m_Root, m_Root.m_Right, true);
		deleterHelper(m_Root, m_Root.m_Left, false);
	}

	void deleteHelper(Node node) {
		if(node != null){
			if(node.m_Left != null && node.m_Left.m_Left == null && node.m_Left.m_Right == null){
				System.out.println(" deleted " + node.m_Left.m_Key);
				node.m_Left = null;
			}
			if(node.m_Right != null && node.m_Right.m_Left == null && node.m_Right.m_Right == null){
				System.out.println(" deleted " + node.m_Right.m_Key);
				node.m_Right = null;	
			}
			deleteHelper(node.m_Left);
			deleteHelper(node.m_Right);
			}
	}
	
	void deleterHelper(Node parent, Node current, boolean rightChild){
		if(current  != null){
			if(current.m_Left == null && current.m_Right == null){
				if(rightChild) parent.m_Right = null;
				else parent.m_Left = null;
			}
			deleterHelper(current, current.m_Left, false);
			deleterHelper(current, current.m_Right, true);
		}
	}
	
}


public class BinTreeTasks{
public static void main(String[] args){
	Binary tree = new Binary();
	tree.m_Root = new Binary.Node(10);                                   //      10
	tree.m_Root.m_Left = new Binary.Node(5);                           //  5            15
	tree.m_Root.m_Right = new Binary.Node(15);                        //       7     12    19
 	                                                                 //     6      11
	tree.m_Root.m_Left.m_Right = new Binary.Node(7);   
	tree.m_Root.m_Left.m_Right.m_Left = new Binary.Node(6);
	
	tree.m_Root.m_Right.m_Left = new Binary.Node(12);
	tree.m_Root.m_Right.m_Left.m_Left = new Binary.Node(11);
	tree.m_Root.m_Right.m_Right = new Binary.Node(19);
	System.out.println("Test 1: Second Last is in the right sub tree of lowest \n");
//	System.out.println("\t\t\t \t 10 \n "
//			+ "\t\t 5 \t\t \t\t15\n"
//			+ "\t \t \t 7\t \t 12 \t \t 19 \n"
//			+ " \t\t 6 \t \t \t11");
	System.out.println("Has to be 6, RESULT:" + tree.secondLast());
//	System.out.println("min: " + tree.minMax()[0] + " max:" + tree.minMax()[1]);
	System.out.println(tree.avg());
	tree.deleteLeafs();
	System.out.println("\n");
	tree.minMax();
	System.out.println("\n END");
	Binary tree2 = new Binary();
	tree2.m_Root = new Binary.Node(10);                                   //      10
	tree2.m_Root.m_Left = new Binary.Node(5);                           //  5            15
	tree2.m_Root.m_Right = new Binary.Node(15);                        //             12    19
 	                                                                 //            11
	tree2.m_Root.m_Right.m_Left = new Binary.Node(12);
	tree2.m_Root.m_Right.m_Left.m_Left = new Binary.Node(11);
	tree2.m_Root.m_Right.m_Right = new Binary.Node(19);
	System.out.println("\n Test 2: Lowest is a Leave, Root is second last:  \n ");
//	System.out.println("\t\t\t 10 \n "
//			+ "\t\t 5 \t\t 15\n"
//			+ "\t  \t \t  12 \t \t 19 \n"
//			+ " \t\t\t 11");
	System.out.println(" Has to be 10, RESULT:" + tree2.secondLast());
	
	Binary tree3 = new Binary();
	tree3.m_Root = new Binary.Node(10);                                   //      10
                                                                         //              15
	tree3.m_Root.m_Right = new Binary.Node(15);                        //             12    19
	tree3.m_Root.m_Right.m_Left = new Binary.Node(12);               //            11	
	tree3.m_Root.m_Right.m_Left.m_Left = new Binary.Node(11);
	tree3.m_Root.m_Right.m_Right = new Binary.Node(19);
	System.out.println("\n Test3: no left sub-tree\n");
//	System.out.println("\t\t\t 10 \n "
//			+ "\t\t \t\t 15\n"
//			+ "\t  \t \t  12 \t \t 19 \n"
//			+ " \t\t\t 11");
	System.out.println("Has to be 11, RESULT:" + tree3.secondLast());
	System.out.println("min: " + tree3.minMax()[0] + " max:" + tree3.minMax()[1]);

	
	Binary tree4 = new Binary();
	tree4.m_Root = new Binary.Node(10);                                   //      10
	tree4.m_Root.m_Left = new Binary.Node(5);                           //  5            15
	tree4.m_Root.m_Right = new Binary.Node(15);                        // 4            12    19
 	                                                                 // 3           11
	tree4.m_Root.m_Left.m_Left = new Binary.Node(4);
	tree4.m_Root.m_Left.m_Left.m_Left = new Binary.Node(3);
	
	tree4.m_Root.m_Right.m_Left = new Binary.Node(12);
	tree4.m_Root.m_Right.m_Left.m_Left = new Binary.Node(11);
	tree4.m_Root.m_Right.m_Right = new Binary.Node(19);
	System.out.println("\n Test 4: Lowest is a Leave, Parent (not root) is second last: \n");
//	System.out.println("\t\t\t 10 \n "
//			+ "\t\t 5 \t\t 15\n"
//			+ "\t 4 \t \t  12 \t \t 19 \n"
//			+ "3 \t\t\t 11");
	System.out.println("Has to be 4, RESULT:" + tree4.secondLast());

	System.out.println("min: " + tree4.minMax()[0] + " max:" + tree4.minMax()[1]);

	
}
}





