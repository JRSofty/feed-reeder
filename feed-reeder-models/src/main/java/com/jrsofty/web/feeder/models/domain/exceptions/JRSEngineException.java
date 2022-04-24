package com.jrsofty.web.feeder.models.domain.exceptions;

public class JRSEngineException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2287118629288042023L;

    public JRSEngineException(String message) {
        super(message);
    }

    public JRSEngineException(String message, Throwable e) {
        super(message, e);
    }

    public JRSEngineException(Throwable t) {
        super(t);
    }

}
