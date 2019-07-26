package com.netposa.ips.ga1400.domain.kafka;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Data
public final class RawMessage {

    private String topic;

    private int partition;

    private long offset;

    private long timestamp;

    private byte[] key;

    private byte[] value;

    private long checksum;

    private RawMessage() {
    }

    public static RawMessage fromRecord(ConsumerRecord<byte[], byte[]> record) {
        RawMessage message = new RawMessage();
        message.setTopic(record.topic());
        message.setPartition(record.partition());
        message.setOffset(record.offset());
        message.setTimestamp(record.timestamp());
        message.setKey(record.key());
        message.setValue(record.value());
        message.setChecksum(record.checksum());
        return message;
    }
}
