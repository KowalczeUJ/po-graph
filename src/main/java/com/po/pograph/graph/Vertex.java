package com.po.pograph.graph;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Vertex implements Comparable<Vertex> {

    private final int id;
    private Vertex previous;
    private String value;
    private String decompressedValue;
    private int valuelength;
    private double minDistance = Double.POSITIVE_INFINITY;
    private List<Edge> adjacencies = new ArrayList<>();

    public Vertex(int id, Vertex previous, String value, int valuelength, String decompressedValue, double minDistance) {
        this.id = id;
        this.previous = previous;
        this.value = value;
        this.valuelength = valuelength;
        this.decompressedValue = decompressedValue;
        this.minDistance = minDistance;
    }

    Vertex(int id) {
        this.id = id;
    }

    void setValueLength() {
        this.valuelength = computeValueLength();
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(minDistance, o.minDistance);
    }

    private int computeValueLength() {
        char[] chars = value.toCharArray();
        StringBuilder builder = new StringBuilder();

        int offset;
        int multiplier;

        for (int index = 0; index < chars.length; index++) {
            if (chars[index] != '(') {
                if (chars[index] != ')') {
                    builder.append(chars[index]);
                }
            }
            else {
                String repeated;

                index += 1;
                offset = value.charAt(index) - '0';

                if (builder.length() == offset) {
                    index += 2;
                    multiplier = value.charAt(index) - '0';

                    repeated = new String(new char[multiplier - 1]).replace("\0", builder);
                    builder.append(repeated);
                } else {
                    index += 2;
                    multiplier = value.charAt(index) - '0';

                    int startIndex = builder.length() - offset;
                    repeated = new String(new char[multiplier - 1]).replace("\0", builder.substring(startIndex, startIndex + offset));
                    builder.append(repeated);
                }
            }
        }
        decompressedValue = builder.toString();
        return builder.length();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", previous=" + previous +
                ", value='" + value + '\'' +
                ", valueLength='" + valuelength + '\'' +
                ", decompressedValue=" + decompressedValue +
                ", minDistance=" + minDistance +
                '}';
    }

}
