package com.danggui.wechat.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class ChexBoxTreeModel extends DefaultTreeModel {

    public ChexBoxTreeModel(DefaultMutableTreeNode root) {
        super(root);
        final DefaultMutableTreeNode accessibility =add(root, "Accessibility", true);
        add(accessibility, "Move system caret with focus/selection changes", false);
        add(accessibility, "Always expand alt text for images", true);
        root.add(accessibility);

        final DefaultMutableTreeNode browsing =
                new DefaultMutableTreeNode("Browsing");
        add(browsing, "Notify when downloads complete", true);
        add(browsing, "Disable script debugging", true);
        add(browsing, "Use AutoComplete", true);
        add(browsing, "Browse in a new process", false);
        root.add(browsing);

    }

    private static DefaultMutableTreeNode add(
            final DefaultMutableTreeNode parent, final String text,
            final boolean checked)
    {
        final CheckBoxNodeData data = new CheckBoxNodeData(text, checked);
        final DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
        parent.add(node);
        return node;
    }
}
