

public class InvertList {
	private ListElem m_Head;
	private ListElem m_Tail;
	static class ListElem{
		private int m_Elem;
		private ListElem m_Next = null, m_Prev = null;
		public ListElem(int i){
			m_Elem = i;
		}
	}
	
	public void add(int k){
		if(m_Head == null){
			m_Head = new ListElem(k);
			m_Tail = new ListElem(k);
		}else{
			ListElem a = m_Head;		
			while(a != null){
				if(a.m_Next == null){
					a.m_Next = new ListElem(k);
					a.m_Next.m_Prev = a;
					m_Tail = a.m_Next;
					break;
				}
				a = a.m_Next;
			}
		}
		
	}
	public void invert(){
		ListElem b = m_Tail;
		while(b != null){
			ListElem tmp = b.m_Next;
			b.m_Next = b.m_Prev;			
			b.m_Prev = tmp;	
			
			if(b.m_Next == null){
				b = m_Head;
				m_Head = m_Tail;
				m_Tail = b;	
			}
			
			b = b.m_Next;
		}		
	}

	public void switchPairs(){
		ListElem a = m_Head;
		while(a != null && a.m_Next != null){
			
			ListElem b = a.m_Next;	
			b.m_Prev = a.m_Prev;		
			a.m_Prev = b;			
			a.m_Next = b.m_Next;
			b.m_Next = a;
			
			if(a.m_Next != null){
				a.m_Next.m_Prev = a;			
			}
			if(b.m_Prev != null)
				b.m_Prev.m_Next = b;
			
			if(a == m_Head)
				m_Head = b;
			if(b == m_Tail)
				m_Tail = a;
			
			a = a.m_Next;
		}
	
	}
		static InvertList merge(InvertList l1,  InvertList l2){
			InvertList l3 = new InvertList();
			ListElem a = l1.m_Head;
			ListElem b = l2.m_Head;
			ListElem c = null;
			
			while(a != null || b!= null){			
				if(a != null && b == null){
					if(l3.m_Head == null){
						l3.m_Head = a;
						l3.m_Tail = a;						
					}else{
						c.m_Next = a;
						c.m_Next.m_Prev = c;	
						l3.m_Tail = a;
					}
					c = a;
					a = a.m_Next;
				}else if(a == null && b != null){
					if(l3.m_Head == null){
						l3.m_Head = b;
						l3.m_Tail = b;					
					}else{
						c.m_Next = b;
						c.m_Next.m_Prev = c;	
						l3.m_Tail = b;
					}
					c = b;
					b = b.m_Next;
				}else{
					if(a.m_Elem < b.m_Elem){
						if(l3.m_Head == null){
							l3.m_Head = a;
							l3.m_Tail = a;						
						}else{
							c.m_Next = a;
							c.m_Next.m_Prev = c;
							l3.m_Tail = a;
						}
						c = a;	
						a = a.m_Next;
					}else{
						if(l3.m_Head == null){
							l3.m_Head = b;
							l3.m_Tail = b;					
						}else{
							c.m_Next = b;
							c.m_Next.m_Prev = c;	
							l3.m_Tail = b;
						}
						c = b;
						b = b.m_Next;
					}				
				}				
			}
			
			return l3;
		}
		
		
	public static void main(String[] args){
		InvertList invList = new InvertList();

		invList.add(1);
		invList.add(3);
		invList.add(5);
		
		invList.print();
		InvertList kappa = new InvertList();
		kappa.add(2);
		kappa.add(4);
	
		kappa.print();		
		merge(invList, kappa).print();	
//		invList.invert();	
//		invList.print();
//		invList.switchPairs();
//		invList.print();

	}
	public void print(){
		ListElem a = m_Head;
		System.out.print("[");
		while(a != null){
			System.out.print( a.m_Elem + ", ");
			a = a.m_Next;
		}
		System.out.println("]");
		System.out.println("Head = " + m_Head.m_Elem + " \t " + "TAIL = " + m_Tail.m_Elem);
		System.out.println();
	}
}
