// Generic이 적용된 Doubly linked list
public class GenericLinkedList2Iter<T> implements Cloneable {
	
	class Node<T> {
		private T item;
		private Node<T> link; // 다음 노드
		private Node<T> prev; // 이전 노드
		
		// 기본 생성자
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
		
		// 맨 앞으로 iterator를 이동
		public void restart() {
			position = head;
			previous = null;
		}
		
		// 맨 뒤로 iterator를 이동
		public void restart_inverse() {
			Node<T> temp = head;
			while (temp != null && temp.link != null)
				temp = temp.link;
			position = temp;
			previous = temp.prev;			
		}
		
		// 현재 노드 item을 출력하고, 다음 노드로 iterator를 이동
		public T next() {
			if (!hasNext()) return null;
			T ret = position.item;
			previous = position;
			position = position.link;
			return ret;			
		}

		// 현재 노드 item을 출력하고, 이전 노드로 iterator를 이동		
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

		// Head item 출력
		public T peek() {
			if (!hasNext())
				return null;
			return head.item;
		}
		
		// Tail item 출력
		public T end() {
			Node<T> temp = head;
			if (temp == null) 
				return null;
			while (temp.link != null)
				temp = temp.link;
			return temp.item;
		}		
		
		// iterator 위치에 newData item을 가진 노드를 추가합니다.
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
		
		// iterator 위치에 있는 노드를 제거합니다.
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
	
	// Doubly Linked List의 head node
	private Node<T> head;
	
	// Doubly Linked List의 iterator를 만듭니다.
	public List2Iterator iterator() {
		return new List2Iterator();
	}
	// basic constructor
	public GenericLinkedList2Iter() {
		head = null;
	}
	// copy constructor
	public GenericLinkedList2Iter(GenericLinkedList2Iter<T> otherList) {
		if (otherList == null) // 원본의 무결성 체크
			throw new NullPointerException();
		if (otherList.head == null)
			head = null;
		else {
			// All nodes copied.
			head = copyOf(otherList.head);
		}			
	}
	
	// 매개변수를 다른 리스트의 head node를 받아서, 리스트를 복제하도록 합니다.
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
	
	// 리스트 복제 함수
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
		// Integer type을 가진 list와 iterator를 만듭니다.
		GenericLinkedList2Iter<Integer> list = new GenericLinkedList2Iter<>();
		GenericLinkedList2Iter<Integer>.List2Iterator it = list.iterator();
		
		// List에 1부터 10까지 원소를 넣었습니다. List의 head 부분에 원소를 넣습니다.
		for (int i = 1; i <= 10; i++)
			list.addToStart(i);
				
		// 전방향 출력
		it.restart();
		while (it.hasNext())
			System.out.println(it.next());
		System.out.println();
		
		// 역방향 출력
		it.restart_inverse();
		while (it.hasNext())
			System.out.println(it.prev());
		System.out.println();		
		
		// GenericLinkedList clone() 동작
		GenericLinkedList2Iter<Integer> cloneList = list.clone();
		GenericLinkedList2Iter<Integer>.List2Iterator cloneIt = cloneList.iterator();
		cloneIt.restart();
		while(cloneIt.hasNext())
			System.out.println(cloneIt.next());
		System.out.println();	
		
		// equals() 동작 여부		
		if (list.equals(cloneList))
			System.out.println("list and cloneList are same.");
		else
			System.out.println("list and cloneList are not same.");
		
		// 복제 리스트의 head node를 제거합니다.
		cloneList.deleteHeadNode();
		if (list.equals(cloneList))
			System.out.println("list and cloneList are same.");
		else
			System.out.println("list and cloneList are not same.");
				
	}

}