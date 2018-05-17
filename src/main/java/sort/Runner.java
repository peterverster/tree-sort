package sort;

import model.Node;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.JsonUtils.writeJsonFile;

public class Runner {

    public static void main(String[] args){

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < 21; i++) {

            nodes.add(Node.builder().id(i).parent(mapParent(i)).label(UUID.randomUUID().toString()).nodes(new ArrayList<>()).distribution(i).build());

        }

        //root nodes are nodes that has no parent int he collection (apart form itself)

        List<Node> root = nodes.stream().filter(n -> n.getId().equals(n.getParent()) || nodes.stream().noneMatch(m -> m.getId().equals(n.getParent()))).collect(Collectors.toList());

        for (Node node: nodes)
        {
            nodes.stream().filter(n -> n.getId().equals(node.getParent()) && root.stream().noneMatch(o-> node.getId().equals(o.getId()))).findFirst().ifPresent( m -> m.getNodes().add(node));
        }

        int evaluate = root.get(0).evaluate(null);

        //Optional<Node> root = nodes.stream().filter(n -> n.getId().equals(n.getParent())).findFirst();




        try {
            writeJsonFile("sample.json", root);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int size = nodes.size();

    }




    private static int mapParent(int i )
    {


        //zero is parent of itself aka root
       if(i == 0 ) return i;

       // 1 and two has to be children of 0
       if(i == 1 || i == 2) return 0;

       //split arbitrary odds and evens
       if(i%2 == 0)
       {
           return 2;
       }
       else
       {
           return 1;

       }
    }
}
