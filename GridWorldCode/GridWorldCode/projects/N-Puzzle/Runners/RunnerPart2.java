package Runners;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.IOException;

public class RunnerPart2 {
	public static void main(String[] args) throws IOException {

		if(JigsawNode.getDimension() != 5){
			System.out.print("节点的维数错误，请将对应的维数改为5");
			return;
		}
		int avr = 0;
        for (int i = 0; i < 20; i++) {
            JigsawNode destNode = new JigsawNode(new int[] { 25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0 });

            JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
            Jigsaw jigsaw = new Jigsaw(startNode, destNode);

            jigsaw.ASearch();
			avr += jigsaw.getSearchedNodesNum();
        }
        avr = avr /20;
        System.out.println(avr);
	}
}
