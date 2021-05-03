package com.epam.davydova.task1.interfaces;

import java.util.HashSet;
import java.util.UUID;

/**
 * This is an interface class that provides a method to fill up a HashSet with UUID
 */
public interface HashSetUuidFiller {
    HashSet<UUID> uuidHashSet = new HashSet<>();

    HashSet<UUID> fillHashSetWithUuids();
}
