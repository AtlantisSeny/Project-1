package Runners;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.IOException;

public class RunnerDemo {

	public static void main(String[] args) throws IOException {
		if(JigsawNode.getDimension() != 3){
			System.out.print("节点的维数错误，请将对应的维数改为3");
			return;
		}
		
		JigsawNode destNode = new JigsawNode(new int[]{9,1,2,3,4,5,6,7,8,0});
		
		JigsawNode startNode = new JigsawNode(new int[]{5,1,5,2,7,0,4,6,3,8});

		Jigsaw jigsaw = new Jigsaw(startNode, destNode);
		
		jigsaw.ASearch();
	}
}
