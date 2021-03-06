package com.comcast.pop.persistence.impl;

import com.comcast.pop.object.api.IdentifiedObject;
import com.comcast.pop.persistence.api.DataObjectFeed;
import com.comcast.pop.persistence.api.ObjectPersister;
import com.comcast.pop.persistence.api.query.Query;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryObjectPersister<T extends IdentifiedObject> implements ObjectPersister<T>
{
    private Map<String, T> objectPersistenceMap;

    public MemoryObjectPersister()
    {
        objectPersistenceMap = new HashMap<>();
    }

    @Override
    public DataObjectFeed<T> retrieve(List<Query> queries)
    {
        throw new NotImplementedException();
    }

    @Override
    public T retrieve(String identifier)
    {
        return objectPersistenceMap.get(identifier);
    }

    @Override
    public T persist(T object)
    {
        objectPersistenceMap.put(object.getId(), object);
        return object;
    }

    /**
     * Uses the persist method to simply overwrite the object.
     * @param object The object to update
     */
    @Override
    public T update(T object)
    {
        persist(object);
        return retrieve(object.getId());
    }

    @Override
    public void delete(String identifier)
    {
        objectPersistenceMap.remove(identifier);
    }
}
