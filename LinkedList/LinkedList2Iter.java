
public class LinkedList2Iter {
	
	class Node {
		private String item;
		private Node link;
		
		public Node() {
			item = null;
			link = null;					
		}
		public Node(String s,Node n) {
			item = s;
			link = n;
		}
	} // End of Node inner class
	
	public class List2Iterator {
		private Node position;
		private Node previous;
		
		public List2Iterator() {
			position = null;
			previous = null;
		}
		
		public void restart() {
			position = head;
			previous = null;
		}
		
		public String next() {
			if (!hasNext()) return "";
			String ret = position.item;
			previous = position;
			position = position.link;
			return ret;			
		}
		
		public boolean hasNext() {
			return (position != null);
		}
		
		public String peek() {
			if (!hasNext())
				return "";
			return head.item;
		}
		
		public void addHere(String newData) {
			if (position == null && previous != null) {
				// at end of list
				previous.link = new Node(newData,null);
			}
			else if (position == null || previous == null) {
				// at head of list
				LinkedList2Iter.this.addToStart(newData);
			}
			else {
				// Between nodes
				Node temp = new Node(newData,position);
				previous.link = temp;
				previous = temp;
			}
		}			
		
		public void delete() {
			if (position == null) {
				
			}
			else if (previous == null) {
				head = head.link;
				position = head;
			}
			else {
				previous.link = position.link;
				position = position.link;
			}
		}
	}
	
	private Node head;
	
	public List2Iterator iterator() {
		return new List2Iterator();
	}
	
	public LinkedList2Iter() {
		head = null;
	}
	
	/**
	Adds a node at the start of the list with the specified data.
	The added node will be the first node in the list.
	 */	
	public void addToStart(String itemName) {
		head = new Node(itemName,head);
	}
	
	/**
	Removes the head node and returns true if the list contains at least
	one node. Returns false if the list is empty.
	 */
	public boolean deleteHeadNode() {
		if (head != null) {
			head = head.link;
			return true;
		}
		return false;
	}
	
	/**
	Returns the number of nodes in the list.
	 */	
	public int size() {
		int count = 0;
		Node position = head;
		while (position != null) {
			count++;
			position = position.link;
		}
		return count;
	}
	
	public boolean contains(String item) {
		return (find(item) != null);
	}
	
	/**
	Finds the first node containing the target item. and returns a reference to that node.
	If target is not in the list, null is returned.
	 */
	
	private Node find(String target) {
		Node position = head;
		String itemAtPosition;
		while (position != null) {
			itemAtPosition = position.item;
			if (itemAtPosition.equals(target))
				return position;
			position = position.link;
		}
		return null;
	}
	
	public void outputList() {
		Node position = head;
		while (position != null) {
			System.out.println(position.item);
			position = position.link;
		}
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public void clear() {
		head = null;
	}
	
	/*
	 For two lists to be equal they must contain the same data items in the same order.
	 */
	
	public boolean equals(Object other) {
		if (other == null) // other is null
			return false;
		else if (getClass() != other.getClass()) // class name different
			return false;
		else {
			LinkedList2Iter otherList = (LinkedList2Iter) other;
			if (size() != otherList.size()) // size different
				return false;
			Node position = head;
			Node otherPosition = otherList.head;
			while (position != null) {
				if (!(position.item.equals(otherPosition.item)))
						return false;
				position = position.link;
				otherPosition = otherPosition.link;
			}
			return true;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList2Iter list = new LinkedList2Iter();
		LinkedList2Iter.List2Iterator it = list.iterator();
		
		list.addToStart("shoes");
		list.addToStart("orange juice");
		list.addToStart("coat");
		
		System.out.println("List contains");
		it.restart();
		while (it.hasNext())
			System.out.println(it.next());
		System.out.println();
		
		it.restart();
		it.next();
		it.delete();
		
		System.out.println("List now contains");
		it.restart();
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println();
		
		it.restart();
		it.next();
		it.addHere("socks");
		
		it.restart();
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println();		
		
	}

}
