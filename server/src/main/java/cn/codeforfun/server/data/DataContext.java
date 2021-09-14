package cn.codeforfun.server.data;

public class DataContext implements Runnable {

    private DataHandler dataHandler;

    @Override
    public void run() {

    }

    public DataContext(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        run();
    }

    private DataContext() {
    }
}
