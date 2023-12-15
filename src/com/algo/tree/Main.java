package com.algo.tree;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Slf4j
public class Main {

    private static TreeNode root;
    private static TreeNode node2;
    private static TreeNode node3;
    private static TreeNode node4;
    private static TreeNode node5;

    static {
        root = new TreeNode(4);
        node2 = new TreeNode(2);
        node3 = new TreeNode(6);
        node4 = new TreeNode(1);
        node5 = new TreeNode(3);
    }

    public static void main(String[] args) {
        root.setLeft(node2);
        node2.setLeft(node3);
        node2.setRight(node4);
        root.setRight(node5);
        List<Integer> preResult = new ArrayList<>();
        preOrder(root,preResult);
        log.info("前序遍历结果：{}", JSON.toJSONString(preResult));
        List<Integer> inResult = new ArrayList<>();
        inOrder(root,inResult);
        log.info("中序遍历结果：{}", JSON.toJSONString(inResult));
        List<Integer> postResult = new ArrayList<>();
        postOrder(root,postResult);
        log.info("后序遍历结果：{}", JSON.toJSONString(postResult));

        List<Integer> levelResult = levelOrder(root);
        log.info("层序遍历结果：{}",levelResult);

    }


    /**
     * 前序遍历
     * 根->左->右
     */
    private static void preOrder(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        result.add(root.getValue());
        preOrder(root.getLeft(), result);
        preOrder(root.getRight(), result);
    }

    /**
     * 中序遍历
     * 左->根->右
     */
    private static void inOrder(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        inOrder(root.getLeft(), result);
        result.add(root.getValue());
        inOrder(root.getRight(), result);
    }

    /**
     * 后续遍历
     * 左->右->根
     */
    private static void postOrder(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        postOrder(root.getLeft(), result);
        postOrder(root.getRight(), result);
        result.add(root.getValue());
    }


    /**
     * 层序遍历
     * 逐层遍历,利用队列的先进先出特性
     */
    private static List<Integer> levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            //出队
            TreeNode node = queue.poll();
            result.add(node.getValue());
            if (null != node.getLeft()){
                //左节点入队
                queue.offer(node.getLeft());
            }
            if (null != node.getRight()){
                //右节点入队
                queue.offer(node.getRight());
            }
        }
        return result;
    }
}
