

public class BinaryTree {

	private class BinaryNode{
		private Student info;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private BinaryNode parentNode;
		
		public BinaryNode() {
			leftChild = null;
			rightChild = null;
			parentNode = null;
			info = null;
		}
		
		public BinaryNode(BinaryNode parent, Student value,BinaryNode left, BinaryNode right) {
			parentNode = parent;
			info = value;
			rightChild= right;
			leftChild= left;
		}
		 public BinaryNode getLeftChild() {
			 return this.leftChild;
		 }
		 public BinaryNode getRightChild() {
			return rightChild;
		}
		 public Student getInfo() {
			return info;
		}
		 
		 public BinaryNode getParentNode() {
			return parentNode;
		}
		 
		 public void setInfo(Student info) {
			this.info = info;
		}
		 
		 public void setLeftChild(BinaryNode leftChild) {
			this.leftChild = leftChild;
		}
		 public void setParentNode(BinaryNode parentNode) {
			this.parentNode = parentNode;
		}
		 
		 public void setRightChild(BinaryNode rightChild) {
			this.rightChild = rightChild;
		}
		
		  
	}
	
	
	
	protected BinaryNode creatNode(BinaryNode parent, Student value,BinaryNode left,BinaryNode right) {
		return new BinaryNode(parent, value,left,right);
		
	}
	
	
	
	private	BinaryNode root = null;
	private int size = 0;
	
	
	public BinaryTree() {
		root = null;
		this.size = 0;	
	}
	
	public void addEllement(Student student) {
		if(this.root == null) {
			root = new BinaryNode();
			root.info= student;
			size +=1;
		}
		else {
			BinaryNode parent =searchFinalPosition(root ,student.getSidc());
			int child = compareKey(parent, student.getSidc());
				if(child == -1)addLeft(parent , student);
				else if(child == 1)addRight(parent ,student);
				else if(child == 0) System.out.println("The sstudent is already present in the record");
		}
	}
	
	public void addLeft(BinaryNode parent, Student value) {
		if(parent.getLeftChild() == null) {
			parent.setLeftChild(creatNode(parent ,value, null, null));
			size += 1;
		}else {
			System.out.println("Invalid left child");
		}
	}
	
	public void addRight(BinaryNode parent, Student value) {
		if(parent.getRightChild() == null) {
			parent.setRightChild(creatNode(parent ,value, null, null));
			size += 1;
		}else {
			System.out.println("Invalid Right child");
		}
	}
	
	// comparing the desired key to the key value of the selected node
	public int compareKey(BinaryNode node, long key) {
		long sidc =node.info.getSidc();
		if( sidc< key) return 1;
		else if(sidc > key) return -1;
		else  return 0;
	}
	
	
	// return the parent of the node  we are searching for Mostly to add
	public BinaryNode searchFinalPosition(BinaryNode node, long key) {
		if(compareKey(node, key) == -1) {
			if(node.leftChild != null) return searchFinalPosition(node.leftChild, key);
			else return node;
		}
		else if (compareKey(node, key) == 1) {
			if(node.rightChild != null)return searchFinalPosition(node.rightChild, key);
			else return node;
		}
		
		return node;
		
	}
	
	public BinaryNode findKey(long key) {
		return keyFinder(root,key);
	}
	
	
	public BinaryNode keyFinder(BinaryNode node, long key) {
		int checkValue= compareKey(node, key);
		if(checkValue == -1) {
			if(node.leftChild != null) return keyFinder(node.leftChild, key);
	
		}
		else if (checkValue == 1) {
			if(node.rightChild != null)return keyFinder(node.rightChild, key);	
		}
		else if (checkValue == 0)  {
			return node;
		}
		
		System.out.println("No corresponding value in the data base (inside KeyFinder)");
		return null;
		
			
	}
	public void removeNode(long key) {
		BinaryNode toRemove = keyFinder(root, key);
		int parentLink= 0;
		int childLink = 0;
		BinaryNode lowValue = null;
		
		// find what is the relation between the node to remove and his parent
		if(toRemove != null && toRemove.parentNode != null&&toRemove.parentNode.leftChild==toRemove) {
			parentLink= -1;
		}else {
			parentLink = 1;
		}
		
		
		// finding the number of child
		if(toRemove != null) {
			//if only 1 child , we will just replace the node with is child
			if(hasChild(toRemove) == 1) {
				if(toRemove.leftChild != null) childLink = -1;
				if(toRemove.rightChild != null) childLink = 1;
				
				//replacing the good parent child with the good child
				if(childLink == -1) {
					if(parentLink == 1 && toRemove.parentNode== null) this.root  =toRemove.getLeftChild();
					else if(parentLink == -1&& toRemove.parentNode== null) this.root  =toRemove.getLeftChild();
					else if (parentLink == 1) toRemove.parentNode.setRightChild(toRemove.getLeftChild());
					else if (parentLink == -1 ) toRemove.parentNode.setLeftChild(toRemove.getLeftChild());
					
				}
				else {
					if (parentLink == 1 && toRemove.parentNode== null) this.root=toRemove.getRightChild();
					else if (parentLink == -1 && toRemove.parentNode== null) this.root =toRemove.getRightChild();
					else if (parentLink == 1) toRemove.parentNode.setRightChild(toRemove.getRightChild());
					else if (parentLink == -1) toRemove.parentNode.setLeftChild(toRemove.getRightChild());
				
				}
			}
			// if o child we can just remove it 
			if(hasChild(toRemove) == 0) {
				 childLink = 0;
				 if (parentLink == 1) toRemove.parentNode.setRightChild(null);
				 if (parentLink == -1) toRemove.parentNode.setLeftChild(null);
				 toRemove.setInfo(null);
				 
			}
			 // if 2 child we  have t find the lowest value of the right child
			if(hasChild(toRemove) == 2) {
				lowValue =getLowestValue(toRemove.rightChild);
				long lowValuekey = lowValue.info.getSidc();
				childLink = 2;
				toRemove.setInfo(lowValue.getInfo());
				if(lowValue.getParentNode().getParentNode() == null) {
					lowValue.parentNode.rightChild = null;
				}else {
					lowValue.parentNode.leftChild = null;
						
				}
				
	
			}
			
			this.size--;
			
		}
	}
	
	public int hasChild (BinaryNode node) {
		
		int number = 0;
		if(node.leftChild != null ) number++;
		if(node.rightChild != null ) number++;
		return number;
	}
	
	
	public boolean isLeftChild(BinaryNode position) {
		if(position.getParentNode()!= null && position.getParentNode().getLeftChild() == position) return true;
		else return false;
	}
	
	public boolean isRightChild(BinaryNode position) {
		if(position.getParentNode()!= null && position.getParentNode().getRightChild() == position) return true;
		else return false;
	}
	
	public BinaryNode getLowestValue(BinaryNode node) {
		if(node.leftChild!= null) return getLowestValue(node.leftChild);
		else return node;
	}
	public BinaryNode getHighestValue(BinaryNode node) {
		if(node.rightChild!= null) return getHighestValue(node.rightChild);
		else return node;
	}
	

	
	
	public long nextKey(long key) {
		
		BinaryNode position = this.keyFinder(root, key);
		long nextKey = -1;
		
		if(position == null)return -1;
		
		//check for higestValue of left subtree
		if(position == root) {
			if(position.rightChild!= null) return position.rightChild.getInfo().getSidc();
		}
		if(this.getHighestValue(root.leftChild)== position) {
		
			return root.getInfo().getSidc();
		}
		
		//check for higest value of right subtree 
		if(this.getHighestValue(root)== position) {
			return -1;
		}

		//checking if it have a right child
		else if(position.rightChild!= null) {
			BinaryNode smallerChild = this.getLowestValue(position.rightChild);
			nextKey= smallerChild.getInfo().getSidc();
		}
		else if(position.parentNode.leftChild == position) {
			nextKey= position.parentNode.info.getSidc();
			
		}
		else  {
			//cheeking if the the node is root or not 
			if(position.parentNode == null ) {
				return -1;
			}
			//cheking if the gandparent is the root. if it is not then set it as the next node. 
			else  if(position.parentNode.parentNode != null){
				nextKey = position.parentNode.parentNode.info.getSidc();
				
			}
		}
		return nextKey;
	}
	
	
	public long prevKey(long key) {
		BinaryNode position = this.keyFinder(root, key);
		
		// lowest value
		if (this.getLowestValue(root).getInfo().getSidc()== key ) {
			 System.out.println("in lowestValue");
			return -1;
		}
		// lowest of right sub tree pass from low to root
		else if (root.rightChild!= null) {
			
			if (this.getLowestValue(root.rightChild).getInfo().getSidc() == key)return root.getInfo().getSidc();
		}
		// if we want the previous value from the root we go grab the biggest value of the left subtree
		if(root == position) {
			return this.getHighestValue(root.getLeftChild()).getInfo().getSidc();
		}
		
		if (position.parentNode != null) {
			// When the node have a left child return it.
			if(position.leftChild != null)return position.leftChild.getInfo().getSidc();
			
			//if the node do not have a left child return the parent 
			else {
				while(this.isRightChild(position) == false) {
					position = position.getParentNode(); 
				}
				return position.getParentNode().getInfo().getSidc();
			}
		}
		
		else if (position.parentNode == null) {
			//if the root have no leftChild previus node will not exist
			if(position.leftChild == null) {
				return -1;
				
			}
			
		}
		System.out.println("go nowere");
		return -1;
	}
	
	
	
	public long rangeKey(long keyOne, long keyTwo) {
		long keyIntheRange = 0;
		boolean looping = true;
		long folowUpKey; 
		BinaryNode masterKeyOne = this.findKey(keyOne);
		BinaryNode masterKeyTwo = this.findKey(keyTwo);
		
		
		if(masterKeyTwo != null && masterKeyOne != null)
		
		if( this.keyFinder(masterKeyOne, keyTwo)!= null) {
			//key 2 is in key 1 subtree
			
			folowUpKey=keyOne;
			while (looping) {
				folowUpKey = this.nextKey(folowUpKey);
				if(folowUpKey == keyTwo) {
					looping = false;
					return keyIntheRange;
				}else {
					keyIntheRange ++ ;
					System.out.println(this.getValue(folowUpKey));
					
					if ( keyIntheRange >= 10) looping= false; 
				}
				
			}
			
		}else if(this.keyFinder(masterKeyTwo, keyOne)!= null) {
				// key 1 is in key 2 sub tree
			
			folowUpKey=keyTwo;
			while (looping) {
				folowUpKey = this.nextKey(folowUpKey);
				if(folowUpKey == keyOne) {
					looping = false;
					return keyIntheRange;
				}else {
					keyIntheRange ++ ;
					System.out.println(this.getValue(folowUpKey));
					
					if ( keyIntheRange >= 10) looping= false; 
				}
				
			}
			
		}
		else {
			// if both key are in different sub tree 
			//start from left then go to right
			if (keyOne < keyTwo) {
				
				folowUpKey=keyOne;
				while(looping) {
					folowUpKey = this.nextKey(folowUpKey);
					if(folowUpKey == keyTwo) {
						looping = false;
						return keyIntheRange;
					}else {
						keyIntheRange ++ ;
						System.out.println(this.getValue(folowUpKey));
						
						if ( keyIntheRange >= 10) looping= false; 
					}
				}
				
			}
			
			else {
				
				folowUpKey=keyOne;
				while(looping) {
					folowUpKey = this.prevKey(folowUpKey);
					if(folowUpKey == keyTwo) {
						
						return keyIntheRange;
					}else {
						keyIntheRange ++ ;
						System.out.println(this.getValue(folowUpKey));
						
						if ( keyIntheRange >= 10) looping= false; 
					}
				}
			}
		}		
		return keyIntheRange;
	}
	
	public long[] allKeys(long size) {
		long[] sequences = new long[(int) size];
		
		 long lowest =this.getLowestValue(root).getInfo().getSidc();
		 long biggest = this.getHighestValue(root).getInfo().getSidc();
		 boolean looping = true;
		 int index = 1;
		 long followUpKey = lowest;
		 sequences[0] = lowest;
		 while(looping) {
			 followUpKey =this.nextKey(followUpKey);
			 if(followUpKey == biggest) {
				 sequences[index] = biggest;
				 looping = false;
			 }else {
				 sequences[index] = followUpKey;
				 index++;
			 }
		 }
		return sequences;
	}
	
	public int getSize() {
		return size;
	}
	public BinaryNode getRoot() {
		return root;
	}
	//return the string value if the value of the givven key
	public String getValue(long key) {
		 BinaryNode tempo =this.keyFinder(getRoot(), key);
		if(tempo != null) {
			return tempo.getInfo().getInfo();
		}
		return null;
	}
}
