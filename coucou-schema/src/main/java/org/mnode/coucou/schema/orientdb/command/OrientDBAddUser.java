package org.mnode.coucou.schema.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.coucou.schema.AddCommand;
import org.mnode.coucou.schema.model.User;

import static org.mnode.coucou.schema.Property.Name;

public class OrientDBAddUser extends AbstractOrientDBCommand implements AddCommand<User> {

    public OrientDBAddUser(ODatabaseDocument session) {
        super(session);
    }

    @Override
    public boolean add(User input) {
        OVertex vertex = getSession().newVertex("User");
        vertex.setProperty(Name.toString(), input.getName());
        return getSession().save(vertex) != null;
    }
}
