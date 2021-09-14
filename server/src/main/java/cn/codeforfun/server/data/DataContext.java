package cn.codeforfun.server.data;

public class DataContext implements Runnable {

    private DataHandler dataHandler;

    private DataContext() {
    }

    public DataContext(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        run();
    }

    @Override
    public void run() {

    }
}
