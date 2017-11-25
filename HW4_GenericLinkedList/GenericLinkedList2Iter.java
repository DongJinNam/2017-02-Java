// Generic�� ����� Doubly linked list
public class GenericLinkedList2Iter<T> implements Cloneable {
	
	class Node<T> {
		private T item;
		private Node<T> link; // ���� ���
		private Node<T> prev; // ���� ���
		
		// �⺻ ������
		public Node() {
			item = null;
			link = null;	
			prev = null;
		}
		public Node(T s,Node<T> p, Node<T> n) {
			item = s;
			prev = p;
			link = n;
		}
	} // End of Node inner class
	
	public class List2Iterator {
		private Node<T> position;
		private Node<T> previous;
		
		public List2Iterator() {
			position = null;
			previous = null;
		}
		
		// �� ������ iterator�� �̵�
		public void restart() {
			position = head;
			previous = null;
		}
		
		// �� �ڷ� iterator�� �̵�
		public void restart_inverse() {
			Node<T> temp = head;
			while (temp != null && temp.link != null)
				temp = temp.link;
			position = temp;
			previous = temp.prev;			
		}
		
		// ���� ��� item�� ����ϰ�, ���� ���� iterator�� �̵�
		public T next() {
			if (!hasNext()) return null;
			T ret = position.item;
			previous = position;
			position = position.link;
			return ret;			
		}

		// ���� ��� item�� ����ϰ�, ���� ���� iterator�� �̵�		
		public T prev() {
			if (!hasNext()) return null;
			T ret = position.item;
			position = previous;
			if (previous != null)
				previous = previous.prev;									
			return ret;					
		}
		
		public boolean hasNext() {
			return (position != null);
		}

		// Head item ���
		public T peek() {
			if (!hasNext())
				return null;
			return head.item;
		}
		
		// Tail item ���
		public T end() {
			Node<T> temp = head;
			if (temp == null) 
				return null;
			while (temp.link != null)
				temp = temp.link;
			return temp.item;
		}		
		
		// iterator ��ġ�� newData item�� ���� ��带 �߰��մϴ�.
		public void addHere(T newData) {
			if (position == null && previous != null) {
				// at end of list
				Node<T> temp = new Node<T>(newData,null,null);
				Node<T> end = head;
				temp.link = null;
				
				while (end.link != null)
					end = end.link;
				end.link = temp;
				temp.prev = end;
				
			}
			else if (position == null || previous == null) {
				// at head of list
				GenericLinkedList2Iter.this.addToStart(newData);
			}
			else {
				// Between nodes
				Node<T> temp = new Node<T>(newData,previous,position);
				previous.link = temp;
				position.prev = temp;
			}
		}			
		
		// iterator ��ġ�� �ִ� ��带 �����մϴ�.
		public void delete() {
			if (position == null) {
				// at end of list
				Node<T> end = head;
				while (end.link != null)
					end = end.link;				
				end.prev.link = null;								
			}
			else if (previous == null) {												
				head = head.link;				
				head.prev = null;
				
				position = head;
			}
			else {
				previous.link = position.link;
				position.link.prev = previous;
				
				position = position.link;
			}
		}
	}
	
	// Doubly Linked List�� head node
	private Node<T> head;
	
	// Doubly Linked List�� iterator�� ����ϴ�.
	public List2Iterator iterator() {
		return new List2Iterator();
	}
	// basic constructor
	public GenericLinkedList2Iter() {
		head = null;
	}
	// copy constructor
	public GenericLinkedList2Iter(GenericLinkedList2Iter<T> otherList) {
		if (otherList == null) // ������ ���Ἲ üũ
			throw new NullPointerException();
		if (otherList.head == null)
			head = null;
		else {
			// All nodes copied.
			head = copyOf(otherList.head);
		}			
	}
	
	// �Ű������� �ٸ� ����Ʈ�� head node�� �޾Ƽ�, ����Ʈ�� �����ϵ��� �մϴ�.
	public Node<T> copyOf(Node<T> otherHead) {
		Node<T> position = otherHead;
		Node<T> newHead = null;
		Node<T> end = null;
		
		newHead = new Node<T>(position.item,null,position.link);
		end = newHead;
		position = position.link;
		
		while (position.link != null) {
			end.link = new Node<T>(position.item,position.prev,position.link);
			end = end.link;
			position = position.link;
		}
		return newHead;
	}
	
	// ����Ʈ ���� �Լ�
	public GenericLinkedList2Iter<T> clone() {		
		try {
			GenericLinkedList2Iter<T> copy = (GenericLinkedList2Iter<T>) super.clone();
			if (head == null)
				copy.head = null;
			else
				copy.head = copyOf(head);
			return copy;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	/**
	Adds a node at the start of the list with the specified data.
	The added node will be the first node in the list.
	 */	
	public void addToStart(T itemName) {
		Node<T> temp = new Node<T>(itemName,null,null);
		temp.link = head;
		temp.prev = null;		
		if (head != null)
			head.prev = temp;
		head = temp;
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
		Node<T> position = head;
		while (position != null) {
			count++;
			position = position.link;
		}
		return count;
	}
	
	public boolean contains(T item) {
		return (find(item) != null);
	}
	
	/**
	Finds the first node containing the target item. and returns a reference to that node.
	If target is not in the list, null is returned.
	 */
	
	private Node<T> find(T target) {
		Node<T> position = head;
		T itemAtPosition;
		while (position != null) {
			itemAtPosition = position.item;
			if (itemAtPosition.equals(target))
				return position;
			position = position.link;
		}
		return null;
	}
	
	public void outputList() {
		Node<T> position = head;
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
			GenericLinkedList2Iter<T> otherList = (GenericLinkedList2Iter) other;
			if (size() != otherList.size()) // size different
				return false;
			Node<T> position = head;
			Node<T> otherPosition = otherList.head;
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
		// Integer type�� ���� list�� iterator�� ����ϴ�.
		GenericLinkedList2Iter<Integer> list = new GenericLinkedList2Iter<>();
		GenericLinkedList2Iter<Integer>.List2Iterator it = list.iterator();
		
		// List�� 1���� 10���� ���Ҹ� �־����ϴ�. List�� head �κп� ���Ҹ� �ֽ��ϴ�.
		for (int i = 1; i <= 10; i++)
			list.addToStart(i);
				
		// ������ ���
		it.restart();
		while (it.hasNext())
			System.out.println(it.next());
		System.out.println();
		
		// ������ ���
		it.restart_inverse();
		while (it.hasNext())
			System.out.println(it.prev());
		System.out.println();		
		
		// GenericLinkedList clone() ����
		GenericLinkedList2Iter<Integer> cloneList = list.clone();
		GenericLinkedList2Iter<Integer>.List2Iterator cloneIt = cloneList.iterator();
		cloneIt.restart();
		while(cloneIt.hasNext())
			System.out.println(cloneIt.next());
		System.out.println();	
		
		// equals() ���� ����		
		if (list.equals(cloneList))
			System.out.println("list and cloneList are same.");
		else
			System.out.println("list and cloneList are not same.");
		
		// ���� ����Ʈ�� head node�� �����մϴ�.
		cloneList.deleteHeadNode();
		if (list.equals(cloneList))
			System.out.println("list and cloneList are same.");
		else
			System.out.println("list and cloneList are not same.");
				
	}

}