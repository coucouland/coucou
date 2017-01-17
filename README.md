# Coucou

A communications platform for communities.

## Glossary

 * **Jot** - a topic-based thread, analagous to a forum discussion, a ticket, an event, or anything that typically includes a thread of communication
 * **Village** - a community of Jotters as configured by a member (e.g. work, family, friends, hobbies, etc.)
 * **Citizen** - a village member
 * **Group** - a grouping of members
 * **Mascot** - an avatar/logo assigned to members, groups and villages
 * **Visibility** - public/private permissions applied to Jots, Villages, Groups
 * **Foo** - the mascot for Coucou Land
 * **Badge** - awarded to users for activity, by association (i.e. group membership), etc.

## Platform Analogies

Coucou lends its concepts and model from a number of different sources, so it may be useful to describe these sources and their analagous counterparts.

| Coucou  | GitHub       | JIRA    | Mailing Lists | Forums | Reddit     | Twitter      | Facebook
| ------  | ------       | ----    | ------------- | ------ | ------     | -------      | --------
| Village | Organization | Project | List          | Group  | Sub-reddit | -            | -
| Jot     | Repository   | Issue   | Thread        | Topic  | Topic      | Conversation | Messages
| Citizen | Team Member  | User    | Subscriber    | Member | Member     | User         | User


## Anatomy of a URL

A coucou URL is primarily a pointer to a resource, be it a Jot, Citizen, Village, etc. A resource is identified by a unique hash and a resource type:

    http://coucou.land/jot/20ea15fb4a4715222f7cf635247ef0e8
    
    http://coucou.land/citizen/801c07d82d0bce07de9c57fedd87a46f
    
    http://coucou.land/village/813f8ce580f276558ce9e5093468b1ab

A URL may be further decorated for ease of categorisation/recognition by humans:

    http://coucou.land/fortuna/jot/20ea15fb4a4715222f7cf635247ef0e8
    
    http://coucou.land/australian/citizen/801c07d82d0bce07de9c57fedd87a46f
    
    http://coucou.land/technology/village/813f8ce580f276558ce9e5093468b1ab
