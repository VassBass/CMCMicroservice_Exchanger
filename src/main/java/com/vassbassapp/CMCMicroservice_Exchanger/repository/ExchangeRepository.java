package com.vassbassapp.CMCMicroservice_Exchanger.repository;

import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Repository
public class ExchangeRepository {
    private final BlockingQueue<Integer> ids;
    private final ConcurrentMap<Integer, String> buffer;

    public ExchangeRepository() {
        buffer = new ConcurrentHashMap<>();
        ids = new LinkedBlockingQueue<>();
        updateIds();
    }

    private void updateIds() {
        if (ids.isEmpty()) {
            ids.addAll(new Random()
                    .ints(1_000, 10_000)
                    .limit(1_000)
                    .filter(i -> !buffer.containsKey(i))
                    .boxed()
                    .collect(Collectors.toSet()));
        }
    }

    public int addContent(String content) throws InterruptedException {
        int id = ids.take();
        buffer.put(id, content);
        updateIds();
        return id;
    }

    public boolean updateContent(int id, String content) {
        if (buffer.containsKey(id)) {
            return Objects.nonNull(buffer.put(id, content));
        } else return false;
    }

    public boolean removeContent(int id) {
        return Objects.nonNull(buffer.remove(id));
    }

    public String getContent(int id) {
        String content = buffer.get(id);
        return Objects.nonNull(content) ? content : "";
    }
}
