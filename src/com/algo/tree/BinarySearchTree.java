package com.algo.tree;


import cn.hutool.core.util.ObjectUtil;
import com.algo.util.TimerUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
class BinarySearchTree {

    /**
     * 根节点
     */
    private TreeNode root;


    /**
     * 插入节点
     */
    public Boolean insert(Integer val) {
        if (ObjectUtil.isNull(root)){
            root = new TreeNode(val);
            return true;
        }
        TreeNode curNode = root,preNode = null;
        while (ObjectUtil.isNotNull(curNode)){
            if (val.equals(curNode.getValue())){
                //节点唯一
                return false;
            }
            preNode = curNode;
            if (val < curNode.getValue()){
                curNode = curNode.getLeft();
            }else {
                curNode = curNode.getRight();
            }
        }
        TreeNode node = new TreeNode(val);
        Objects.requireNonNull(preNode);
        if (val < preNode.getValue()){
            preNode.setLeft(node);
        }else {
            preNode.setRight(node);
        }
        return true;
    }


    /**
     * 搜索
     */
    public Boolean search(Integer val) {
        TimerUtil.start("searchWithIterate");
        Boolean b1 = searchWithIterate(root, val);
        TimerUtil.stop("searchWithIterate");
        TimerUtil.start("searchWithRecursion");
        Boolean b = searchWithRecursion(root, val);
        TimerUtil.stop("searchWithRecursion");
        return b;
    }

    /**
     * 循环的方式搜索二叉树
     */
    private Boolean searchWithIterate(TreeNode root, Integer val) {
        TreeNode curNode = root;
        while (null != curNode) {
            if (val < curNode.getValue()) {
                curNode = curNode.getLeft();
            } else if (val > curNode.getValue()) {
                curNode = curNode.getRight();
            } else {
                break;
            }
        }
        return null != curNode;
    }

    /**
     * 递归查找
     */
    public Boolean searchWithRecursion(TreeNode root, Integer val) {
        if (ObjectUtil.isNull(root)) {
            return false;
        }
        if (val.equals(root.getValue())) {
            return true;
        }
        if (val < root.getValue()) {
            return searchWithRecursion(root.getLeft(), val);
        } else {
            return searchWithRecursion(root.getRight(), val);
        }
    }


    /**
     * 遍历
     */
    public List<Integer> order(){
        TimerUtil.start("order");
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        TimerUtil.stop("order");
        return result;
    }

    /**
     * 遍历
     */
    private void inOrder(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        inOrder(root.getLeft(), result);
        result.add(root.getValue());
        inOrder(root.getRight(), result);
    }


    /**
     * 移除节点
     */
    public void remove(Integer val){
        if (ObjectUtil.isNull(root)){
            //空树
            return;
        }
        TreeNode curNode = root,preNode = null;
        while (ObjectUtil.isNotNull(curNode)){
            if (val.equals(curNode.getValue())){
                //找到待删除节点
                break;
            }
            preNode = curNode;
            if (val < curNode.getValue()){
                //位于左子树中
                curNode = curNode.getLeft();
            }else {
                //位于右子树中
                curNode = curNode.getRight();
            }
        }
        if (ObjectUtil.isNull(curNode)){
            //树中没有指定节点
            return;
        }
        if (ObjectUtil.isNull(curNode.getLeft()) || ObjectUtil.isNull(curNode.getRight())){
            //只有一个子节点或者没有子节点
            TreeNode child = ObjectUtil.isNotNull(curNode.getLeft()) ? curNode.getLeft() : curNode.getRight();
            if (curNode != root){
                //删除子节点
                if (curNode == preNode.getLeft()){
                    preNode.setLeft(child);
                }else {
                    preNode.setRight(child);
                }
            }else {
                root = child;
            }
        }else {
            //子节点数量为2
            //获取中序遍历中当前节点的下一个节点
            TreeNode tmp = curNode.getRight();
            while (null != tmp.getLeft()){
                //递归删除节点
                remove(tmp.getValue());
                //替换当前节点的值
                curNode.setValue(tmp.getValue());
            }
        }
    }

}
