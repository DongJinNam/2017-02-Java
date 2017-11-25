public class GenericBST<T extends Comparable<T>> implements Cloneable
{

	private class TreeNode<T>
	{
		private T data;
		private TreeNode<T>  left;
		private TreeNode<T>  right;
		
		//생성자
		public TreeNode( T newdata, TreeNode l, TreeNode r)
		{
			data = newdata;
			left = l;
			right = r;
		}	
	}// TreeNode 클래스의 끝
	
	private TreeNode<T> root;
	
	public GenericBST()
	{
		root = null;
	}
	
	public void insert( T item )
	{
		root = insertItem( item, root );
	}
	
	private TreeNode<T> insertItem( T item, TreeNode<T> subTree)
	{ // new TreeNode를 통해서 트리에 data를 삽입 함.
		if( subTree == null) // 모든 데이타는 맨끝(leaf)에 매달리게 됨.
			return new TreeNode<T>(item, null, null);
		else if ( item.compareTo(subTree.data) < 0 )
		{
			subTree.left = insertItem( item, subTree.left );
			return subTree;	
		}
		else	// item >= subTree.data
		{
			subTree.right = insertItem( item, subTree.right );
			return subTree;	
		}				
	}
	
	public void printBST()
	{
		System.out.println("\n inorder");
		printInOrder(root);
		
		System.out.println("\n preorder");
		printPreOrder(root);
		
		System.out.println("\n postorder");
		printPostOrder(root);
		
	}
	
	private void printInOrder( TreeNode<T> subRoot )
	{// inorder로 출력하면 정렬된 순서로 나오게 됨.
		if( subRoot != null)
		{
			printInOrder(subRoot.left);
			System.out.print( subRoot.data + ", " );
			printInOrder(subRoot.right);	
		}// else do nothing.	
	}
	private void printPreOrder( TreeNode<T> subRoot )
	{
		if( subRoot != null)
		{
			System.out.print( subRoot.data + ", " );
			printInOrder(subRoot.left);
			printInOrder(subRoot.right);	
		}// else do nothing.	
	}
	private void printPostOrder( TreeNode<T> subRoot )
	{
		if( subRoot != null)
		{
			printInOrder(subRoot.left);			
			printInOrder(subRoot.right);	
			System.out.print( subRoot.data + ", " );
		}// else do nothing.	
	}
	
	public boolean contains( T item )
	{
			return IsItem( item, root );
	}
	
	private boolean IsItem( T item, TreeNode<T> subTree )
	{
		if( subTree == null) // (1) root가 널일때,
			return false;  // (2) 끝까지 찾았는데도 없는 경우. 원하는 item을 못 찾고 leaf노드를 만난 경우.		
		else if( item.compareTo(subTree.data) == 0  )
			return true;
		else if ( item.compareTo(subTree.data) < 0  )
		{
			return IsItem( item, subTree.left);
		}
		else	// item >= subTree.data
		{
			return IsItem( item, subTree.right);		
		}		
	}
	public T max()
	{		
		TreeNode temp = root;
		T maxData = (T)temp.data;
		
		while(temp!=null)
		{
			maxData = (T)temp.data;
			temp = temp.right;
		}	
		return maxData;
	}
	
	public void remove(T item) {
		if (IsItem(item, root)) {
			root = removeNode(root,item);
		}		
	}
	
	// root node를 기준으로 가장 작은값을 가진 노드 찾기. (remove 연산을 후행자 처리하기 때문에 했습니다.
	private TreeNode<T> minNode(TreeNode<T> node) {
		TreeNode<T> cur = node;
		while (cur.left != null)
			cur = cur.left;
		return cur;
	}
	
	private TreeNode<T> removeNode(TreeNode<T> root, T item) {
		if (root == null) return null;
		
		if (item.compareTo(root.data) < 0)
			root.left = removeNode(root.left,item);
		else if (item.compareTo(root.data) > 0)
			root.right = removeNode(root.right,item);
		else {
			if (root.left == null) {
				TreeNode<T> temp = root.right;
				return temp;
			}
			else if (root.right == null) {
				TreeNode<T> temp = root.left;
				return temp;				
			}
			TreeNode<T> temp = minNode(root.right);
			root.data = temp.data;
			root.right = removeNode(root.right, temp.data);
		}
		return root;
	}
	// BST Clone 함수
	public GenericBST<T> clone() {		
		try {
			GenericBST<T> copy = (GenericBST<T>)super.clone();
			if (root == null)
				copy.root = null;
			else {
				copy.root = copyOf(root);
			}
			return copy;
				
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}	
	// Root Tree Node를 복사해서 재귀를 사용합니다.
	public TreeNode<T> copyOf(TreeNode<T> oldRoot) {
		if (oldRoot == null) return oldRoot;
		
		TreeNode<T> temp = new TreeNode<T>(oldRoot.data,null,null);
		temp.left = copyOf(oldRoot.left);
		temp.right = copyOf(oldRoot.right);
		return temp;
	}

	public static void main( String [] args)
	{
		GenericBST<Integer> bst = new GenericBST<Integer>();
		
		/* BST 구조
		        5
		     /     \
		    3       8
		   /  \    /  \
		 1     4  7   23
		      			\
		   				45
		 */
				
		bst.insert(5);
		bst.insert(8);
		bst.insert(3);
		bst.insert(23);
		bst.insert(45);
		bst.insert(7);
		bst.insert(1);
		bst.insert(4);
		
		bst.printBST();
		
		GenericBST<Integer> cloneBST = bst.clone();
		System.out.print("\n\nclone list Print!!");
		cloneBST.printBST();

		System.out.print("\n\nAfter Remove 1!!");
		// remove 연산
		/* BST 구조
		        5
		     /     \
		    3       8
		      \    /  \
		       4  7   23
		      			\
		   				45
		 */		
		bst.remove(1);	
		bst.printBST();
		System.out.print("\n\nclone list Print!!");
		cloneBST.printBST();
		
		// remove 연산
		/* BST 구조
		        7
		     /     \
		    3       8
		      \      \
		       4      23
		      			\
		   				45
		 */	
		bst.remove(5);	
		bst.printBST();
		System.out.print("\n\nclone list Print!!");
		cloneBST.printBST();		
	}
}