package model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Region.class, name = "Region"),
        @JsonSubTypes.Type(value = Country.class, name = "Country"),
        @JsonSubTypes.Type(value = Channel.class, name = "Channel"),
})
@Getter
@Setter
@Builder
public class Node {

    private Integer id;
    private Integer parent;
    private String label;
    private int distribution;
    private int result;
    private List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {

        if(nodes == null)
            nodes = new ArrayList<>();

        return nodes;
    }

    public int evaluate(HashMap<String, Object> ctx)
    {


        if(nodes.size() > 0)
        {
            for(Node node : nodes)
            {
                result = result + node.evaluate(null);

            }
        }
        else
        {

            result =  distribution;
        }

        return result;
    }
}
