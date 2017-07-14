package main.model;

import java.sql.Timestamp;

/**
 * Created by video on 14.07.2017.
 */
public class Order {
    private long id;
    private long consumerId;
    private long productId;
    private Timestamp date;

    public Order(long id, long consumerId, long productId, Timestamp date) {
        this.id = id;
        this.consumerId = consumerId;
        this.productId = productId;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
