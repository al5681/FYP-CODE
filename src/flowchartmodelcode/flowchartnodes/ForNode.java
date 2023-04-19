package flowchartmodelcode.flowchartnodes;

/**
 * Represents a for (loop) node
 */
public class ForNode extends ParentNode {

    public String indexer;
    public String from;
    public String to;
    public String toOperator;

    /**
     * Creates a for node
     *
     * @param indexer    The variable for iterating through the for loop
     * @param from       The starting int value of the for loop
     * @param to         The terminating int value of the for loop
     * @param toOperator The operator for the for loop can be '=' or '<'
     */
    public ForNode(String indexer, String from, String to, String toOperator) {
        this.indexer = indexer;
        this.from = from;
        this.to = to;
        this.toOperator = toOperator;
    }

    @Override
    public String output() {
        return "for " + indexer + " in range(" + from + "," + to + ")" + ":" + "\n";
    }

    @Override
    public String getFlowchartText() {
        if (toOperator == ("=")) {
            int decrement = Integer.valueOf(to);
            decrement--;
            return "FOR " + indexer + " = " + from + " to " + indexer + " " + toOperator + " " + decrement + " do";
        } else {
            return "FOR " + indexer + " = " + from + " to " + indexer + " < " + to + " do";
        }
    }

    /**
     * Gets the indexer value
     *
     * @return indexer
     */
    public String getIndexer() {
        return indexer;
    }

    /**
     * Sets the value of the indexer
     *
     * @param indexer the value for the indexer to take
     */
    public void setIndexer(String indexer) {
        this.indexer = indexer;
    }

    /**
     * Gets the from value
     *
     * @return from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of from
     *
     * @param from the value for from to take
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the to value
     *
     * @return to
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of to
     *
     * @param to the value for to to take
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the toOperator value
     *
     * @return toOperator
     */
    public String getToOperator() {
        return toOperator;
    }

    /**
     * Sets the value of toOperator
     *
     * @param toOperator the value for toOperator to take
     */
    public void setToOperator(String toOperator) {
        this.toOperator = toOperator;
    }
}
