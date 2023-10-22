package com.profileForge.exception;

public class ImageBadApiRequest  extends RuntimeException {

    public ImageBadApiRequest(String message) {
        super(message);

    }
    public ImageBadApiRequest() {
        super("bad api request!!");

    }

}
