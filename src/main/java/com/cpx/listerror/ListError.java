package com.cpx.listerror;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author cpx
 * @Description
 * @date 2021/12/27
 */
@Slf4j
public class ListError {
    public void ArrayListExample() {
        int []arr = {1, 2, 3};
        List<int[]> list = Arrays.asList(arr);
        log.info("list:{} siz:{} class:{}", list, list.size(), list.get(0).getClass());
    }

}
