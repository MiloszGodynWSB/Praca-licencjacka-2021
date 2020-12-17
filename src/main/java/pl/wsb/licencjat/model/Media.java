package pl.wsb.licencjat.model;

abstract class Media {

    protected MediaType mediaType;

    MediaType getMediaType() {
        return mediaType;
    };

    enum MediaType {
        MOVIE, SERIES;
    }

}
