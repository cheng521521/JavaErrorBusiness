package com.cpx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class JavaErrorBusinessApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaErrorBusinessApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int []arr1 = {1, 2, 3};
        List<Integer> list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        log.info("list:{} siz:{} class:{}", list1, list1.size(), list1.get(0).getClass());

        int []arr2 = {1, 2, 3};
        List<int[]> list2 = Arrays.asList(arr2);
        log.info("list:{} siz:{} class:{}", list2, list2.size(), list2.get(0).getClass());

        String []arr = {"1", "2", "3"};
        List<String> list = Arrays.asList(arr);
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("arr:{} list:{}", Arrays.toString(arr), list);
    }
}
