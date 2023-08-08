package comp5200m.sc22ao.project.tracingdemo.visualisation;

import comp5200m.sc22ao.project.tracingdemo.service.TracingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceTreeTraversal {
    final static Logger logger = LoggerFactory.getLogger(TracingService.class);

    public static String dfsTraversal(TraceTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatMessageForDepth(node, depth));
        for (TraceTreeNode child : node.getChildren()) {
            sb.append(dfsTraversal(child, depth + 1));
        }
        return sb.toString();
    }

    private static String formatMessageForDepth(TraceTreeNode node, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }
        sb.append(node.getSpanName()).append(" [").append(node.getDuration()).append("ms]").append("\n");
        return sb.toString();
    }
}
