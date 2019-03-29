package com.cobranet.secrethitler.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ChatMessage {
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getWhen() {
        return when;
    }

    public String getFormatedWhen(){

        if(when != null){
            return DateTimeFormatter.ISO_LOCAL_TIME.format  (when.toLocalDateTime());
        }
        return "";
    }
    public void setWhen(Timestamp when) {
        this.when = when;
    }



    String from;
    String to;
    String message;
    Timestamp when;
}
