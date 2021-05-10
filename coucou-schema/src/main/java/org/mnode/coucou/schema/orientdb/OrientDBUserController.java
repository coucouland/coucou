package org.mnode.coucou.schema.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import org.mnode.coucou.schema.NodeController;
import org.mnode.coucou.schema.model.User;
import org.mnode.coucou.schema.orientdb.command.OrientDBAddUser;

import java.util.List;

public class OrientDBUserController implements NodeController<User, String> {

    private final ODatabaseDocument session;

    public OrientDBUserController(ODatabaseDocument session) {
        this.session = session;
    }

    @Override
    public boolean add(User input) {
        return new OrientDBAddUser(session).add(input);
    }

    @Override
    public List<User> get(String query) {
        return null;
    }

    @Override
    public List<User> remove(String query) {
        return null;
    }

    @Override
    public boolean update(User input) {
        return false;
    }
}
