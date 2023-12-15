package com.algo.tree;


import lombok.Data;

@Data
class TreeNode {

    /**
     * 节点
     */
    private Integer value;

    /**
     * 左节点
     */
    private TreeNode left;

    /**
     * 右节点
     */
    private TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
    }
}
