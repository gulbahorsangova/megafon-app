package tj.anor.myapplicatio.model;

 public class ServiceItem {
    private String title;
    private String description;
    private String content;
    private String connection;
    private String subscription;
    private String shutdown;

    public ServiceItem() {
    }

    public ServiceItem(String title, String description, String content, String connection,
                       String subscription, String shutdown ) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.connection = connection;
        this.subscription = subscription;
        this.shutdown = shutdown;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getConnection() {
        return connection;
    }

    public String getSubscription() {
        return subscription;
    }




    public String getShutdown() {
        return shutdown;
    }

}
