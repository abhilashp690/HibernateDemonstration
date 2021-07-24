package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "KafkaProps")
public class KafkaProperties {

    @Id
//    @GeneratedValue
    private int kafkaId;

    @Column(name = "BootStrapList")

    private String bootStrapServer;

    @Column(name = "MaxInSyncReplicaCount")
    private int maxinsyncreplicas;

    public int getKafkaId() {
        return kafkaId;
    }

    public void setKafkaId(int kafkaId) {
        this.kafkaId = kafkaId;
    }

    public String getBootStrapServer() {
        return bootStrapServer;
    }

    public void setBootStrapServer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
    }

    public int getMaxinsyncreplicas() {
        return maxinsyncreplicas;
    }

    public void setMaxinsyncreplicas(int maxinsyncreplicas) {
        this.maxinsyncreplicas = maxinsyncreplicas;
    }

    @Override
    public String toString() {
        return "KafkaProperties{" +
                "kafkaId=" + kafkaId +
                ", bootStrapServer='" + bootStrapServer + '\'' +
                ", maxinsyncreplicas=" + maxinsyncreplicas +
                '}';
    }
}
