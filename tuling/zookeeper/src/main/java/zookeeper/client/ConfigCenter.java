package zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ConfigCenter {
    private final static String CONNECT_CENTER = "";
    private final static Integer TIME_OUT=30 *1000;

    private static ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException {
        zooKeeper = new ZooKeeper(CONNECT_CENTER,TIME_OUT,new Watcher(){

            public void process(WatchedEvent watchedEvent) {

            }
        });

        zooKeeper.create();

        zooKeeper.getData("",false,()-{},null);
    }
}
