package jigsaw;

public class JigsawNode {
	private static final int dimension = 3;
	private int[] nodesState;
	private int nodeDepth;
	private JigsawNode parent;
	private int estimatedValue;

	public JigsawNode(int[] data) {
		if(data.length == this.dimension*dimension+1)
		{
			this.nodesState = new int[data.length];
			for (int i = 0; i < this.nodesState.length; i++)
				this.nodesState[i] = data[i];
			this.nodeDepth = 0;
			this.parent = null;
			this.estimatedValue = 0;
		}
		else
			System.out.println("传入参数错误：当前的节点维数为3.请传入长度为" + (dimension * dimension + 1) + "的节点状态数组，或者调整Jigsaw类中的节点维数dimension");
	}

	public JigsawNode(JigsawNode jNode) {
		this.nodesState = new int[dimension * dimension + 1];
		this.nodesState = (int[]) jNode.nodesState.clone();
		this.nodeDepth = jNode.nodeDepth;
		this.parent = jNode.parent;
		this.estimatedValue = jNode.estimatedValue;
	}

	public static int getDimension() {
		return dimension;
	}

	public int[] getNodesState() {
		return nodesState;
	}

	public int getNodeDepth() {
		return nodeDepth;
	}

	public JigsawNode getParent() {
		return parent;
	}

	public int getEstimatedValue() {
		return estimatedValue;
	}

	public void setEstimatedValue(int estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public void setInitial() {
		this.estimatedValue = 0;
		this.nodeDepth = 0;
		this.parent = null;
	}

	public boolean equals(Object obj) {
		for (int i = 0; i < this.nodesState.length; i++) {
			if (this.nodesState[i] != ((JigsawNode) obj).nodesState[i])
				return false;
		}
		return true;
	}

	public String toString() {
		String str = new String();
		str += "{" + this.nodesState[0];
		for(int index = 1; index <= dimension * dimension; index++)
			str += "," + this.nodesState[index];
		str += "}";
		return str;
	}

	public String toMatrixString() {
		String str = new String();
		for (int x = 1,index = 1; x <= dimension; x++)
		{
			for (int y = 1; y <= dimension; y++,index++){
				str += this.nodesState[index] + "  ";
			}
			str += "\n";
		}
		return str;
	}

	public int[] canMove()
	{
		int[] movable = new int[] { 0, 0, 0, 0 };
		if (this.nodesState[0] > dimension && this.nodesState[0] <= dimension * dimension)
			movable[0] = 1;
		if (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension * (dimension - 1))
			movable[1] = 1;
		if (this.nodesState[0] % dimension != 1)
			movable[2] = 1;
		if (this.nodesState[0] % dimension != 0)
			movable[3] = 1;
		return movable;
	}

	public boolean canMoveEmptyUp() {
		return (this.nodesState[0] > dimension && this.nodesState[0] <= dimension * dimension);
	}

	public boolean canMoveEmptyDown() {
		return (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension * (dimension - 1));
	}

	public boolean canMoveEmptyLeft() {
		return (this.nodesState[0] % dimension != 1);
	}

	public boolean canMoveEmptyRight() {
		return (this.nodesState[0] % dimension != 0);
	}

	public boolean move(int direction) {
		switch (direction) {
		case 0:
			return this.moveEmptyUp();
		case 1:
			return this.moveEmptyDown();
		case 2:
			return this.moveEmptyLeft();
		case 3:
			return this.moveEmptyRight();
		default:
			return false;
		}
	}

	public boolean moveEmptyUp() {
		int emptyPos = this.nodesState[0];

		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] > dimension && this.nodesState[0] <= dimension * dimension) {
			this.parent = new JigsawNode(this);
			this.nodeDepth++;
			this.nodesState[emptyPos] = this.nodesState[emptyPos - dimension];
			this.nodesState[emptyPos - dimension] = emptyValue;
			this.nodesState[0] = emptyPos - dimension;

			return true;
		}
		return false;
	}

	public boolean moveEmptyDown() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension * (dimension - 1))
		{
			this.parent = new JigsawNode(this);
			this.nodeDepth++;

			this.nodesState[emptyPos] = this.nodesState[emptyPos + dimension];
			this.nodesState[emptyPos + dimension] = emptyValue;
			this.nodesState[0] = emptyPos + dimension;
			return true;
		}
		return false;
	}

	public boolean moveEmptyLeft() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] % dimension != 1)
		{
			this.parent = new JigsawNode(this);
			this.nodeDepth++;

			this.nodesState[emptyPos] = this.nodesState[emptyPos - 1];
			this.nodesState[emptyPos - 1] = emptyValue;
			this.nodesState[0] = emptyPos - 1;
			return true;
		}
		return false;
	}

	public boolean moveEmptyRight() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] % dimension != 0)
		{
			this.parent = new JigsawNode(this);
			this.nodeDepth++;

			this.nodesState[emptyPos] = this.nodesState[emptyPos + 1];
			this.nodesState[emptyPos + 1] = emptyValue;
			this.nodesState[0] = emptyPos + 1;
			return true;
		}
		return false;
	}
}
