package config;

/**
 * Created by HP on 2016/3/2.
 */
public class ResultMessage {

    private String message;
    private boolean isPassed;

    public ResultMessage(String message, boolean isPassed) {
        this.message = message;
        this.isPassed = isPassed;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
