package com.algo.tree;

import cn.hutool.core.util.RandomUtil;
import com.algo.util.TimerUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BinarySearchTreeTest {
    private final BinarySearchTree tree = new BinarySearchTree();

    @BeforeEach
    public void initTree() {
        int size = 2000;
        while (size >= 0) {
            tree.insert(size);
            size--;
        }
    }

    @Test
    public void testOrder() {
        List<Integer> order = tree.order();
        String res = order.stream().map(String::valueOf).collect(Collectors.joining(","));
        log.info("集合元素：\n{}", res);
    }


    @Test
    public void testSearch() {
        Boolean search = tree.search(2000);
        log.info("集合元素：\n{}", search);
    }

    @Test
    public void testRemove(){
        TimerUtil.start("remove");
        tree.remove(300);
        TimerUtil.stop("remove");
        List<Integer> order = tree.order();
        String collect = order.stream().map(String::valueOf).collect(Collectors.joining(","));
        log.info("移除节点后：\n{}",collect);
    }
}
