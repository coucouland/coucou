package org.mnode.coucou.schema.model;

import java.util.List;

public class Conversation {

    private String topic;

    private Village village;

    private List<Reference> references;

    private List<User> participants;

    private List<Comment> comments;
}
