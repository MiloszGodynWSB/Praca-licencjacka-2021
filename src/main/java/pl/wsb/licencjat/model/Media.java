package pl.wsb.licencjat.model;

import pl.wsb.licencjat.model.enumerations.MediaType;

public abstract class Media {

    protected MediaType mediaType;

    MediaType getMediaType() {
        return mediaType;
    };

}
