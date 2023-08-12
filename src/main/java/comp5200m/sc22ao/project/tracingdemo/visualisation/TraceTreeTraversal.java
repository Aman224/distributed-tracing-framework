package comp5200m.sc22ao.project.tracingdemo.visualisation;


public class TraceTreeTraversal {
    private Integer durationNormFactor;
    private final Integer DEFAULT_DURATION_NORMALISATION_FACTOR = 1000;

    public TraceTreeTraversal(Long duration) {
        Integer durationNormFactor = findDurationNormFactor(duration);
        if (durationNormFactor != null) {
            this.durationNormFactor = durationNormFactor;
        } else {
            this.durationNormFactor = DEFAULT_DURATION_NORMALISATION_FACTOR;
        }
    }

    public String dfsTraversal(TraceTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatMessageForDepth(node, depth));
        for (TraceTreeNode child : node.getChildren()) {
            sb.append(dfsTraversal(child, depth + 1));
        }
        return sb.toString();
    }

    private String formatMessageForDepth(TraceTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder()
                .append("    ".repeat(depth))
                .append(node.getSpanName())
                .append("\n")
                .append("    ".repeat(depth))
                .append("Service: ")
                .append(node.getService())
                .append("\n")
                .append("    ".repeat(depth))
                .append("Duration: [")
                .append(node.getDuration() / 1000)
                .append("ms]")
                .append("\n")
                .append("    ".repeat(depth))
                .append("=".repeat(Math.toIntExact(node.getDuration() / durationNormFactor)))
                .append("\n");

        return sb.toString();
    }

    private Integer findDurationNormFactor(Long duration) {
        if (duration < 1000) {
            return 10;
        } else if (duration < 5000) {
            return 100;
        } else if (duration < 20000) {
            return 500;
        } else {
            return Math.toIntExact(duration / 100);
        }
    }
}
